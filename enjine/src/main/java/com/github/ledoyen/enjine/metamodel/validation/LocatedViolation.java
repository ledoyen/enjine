package com.github.ledoyen.enjine.metamodel.validation;

import java.lang.reflect.Member;

public class LocatedViolation extends SimpleViolation {
    private final Member _member;

    LocatedViolation(Member member, String reason) {
        super(reason);
        this._member = member;
    }

    public String getReason() {
        return _member.getDeclaringClass().getSimpleName() + "#" + _member.getName() + " " + super.getReason();
    }
}
