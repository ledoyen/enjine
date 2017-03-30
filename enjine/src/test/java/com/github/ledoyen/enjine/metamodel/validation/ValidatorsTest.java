package com.github.ledoyen.enjine.metamodel.validation;

import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.ledoyen.enjine.metamodel.MetaModel;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ValidatorsTest {

    private static Validator OK = m -> Collections.emptySet();
    private static Validator KO = m -> Collections.singleton(new SimpleViolation("test"));

    @Test
    @Parameters
    @TestCaseName("{0}")
    public void validator_behave_correctly(String title, Class classToValidate, Validator validator, String... errors) throws InstantiationException, IllegalAccessException {
        Set<Violation> violations = validator.validate(MetaModel.from(classToValidate));
        assertThat(violations)
                .as("violations")
                .hasSize(errors.length)
                .extracting(Violation::getReason)
                .containsOnly(errors);
    }

    public Object parametersForValidator_behave_correctly() {
        return new Object[]{
                new Object[] {"Validator composition: ok && ok", Object.class, OK.and(OK), new String[0]},
                new Object[] {"Validator composition: ok && ko", Object.class, OK.and(KO), "test"},
                new Object[] {"Validator composition: ko && ok", Object.class, KO.and(OK), "test"},
                new Object[] {"Validator composition: ko && ko", Object.class, KO.and(KO), "test"},
                new Object[] {"noStaticMethods: success", NoStaticMethods.class, Validators.noStaticMethods(), new String[0] },
                new Object[] {"noStaticMethods: fails", StaticMethods.class, Validators.noStaticMethods(),
                        "StaticMethods#method2 should not be static", "StaticMethods#method3 should not be static"},
                new Object[] {"onlyPrivateFields: success", PrivateFields.class, Validators.onlyPrivateFields(), new String[0] },
                new Object[] {"onlyPrivateFields: fails", NotOnlyPrivateFields.class, Validators.onlyPrivateFields(),
                        "NotOnlyPrivateFields#field2 should be private", "NotOnlyPrivateFields#field3 should be private"},
                new Object[] {"publicNoArgsConstructor: success", NoStaticMethods.class, Validators.publicNoArgsConstructor(), new String[0] },
                new Object[] {"publicNoArgsConstructor: fails (private)", PrivateConstructor.class, Validators.publicNoArgsConstructor(),
                        "Missing no args public constructor" },
                new Object[] {"publicNoArgsConstructor: fails (package)", PackageConstructor.class, Validators.publicNoArgsConstructor(),
                        "Missing no args public constructor" },
                new Object[] {"publicNoArgsConstructor: fails (public with args)", PublicWithArgsConstructor.class, Validators.publicNoArgsConstructor(),
                        "Missing no args public constructor" },
        };
    }

    public static class NoStaticMethods {
        private void method1() {}
        public void method2() {}
    }
    public static class StaticMethods {
        public void method1() {}
        public static void method2() {}
        private static void method3() {}
    }
    public static class PrivateFields {
        private String field1;
        private static String field2;
    }
    public static class NotOnlyPrivateFields {
        private String field1;
        int field2;
        public Object field3;
    }
    public static class PrivateConstructor {
        private PrivateConstructor() {}
        private PrivateConstructor(String arg0) {}
    }
    public static class PackageConstructor {
        PackageConstructor() {}
        PackageConstructor(String arg0) {}
    }
    public static class PublicWithArgsConstructor {
        public PublicWithArgsConstructor(String arg0) {}
    }
}
