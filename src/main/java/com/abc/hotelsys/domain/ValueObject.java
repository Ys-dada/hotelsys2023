package com.abc.hotelsys.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class ValueObject {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
