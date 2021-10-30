package com.pm.homework.beans;

public class ObjectUtil {
    private ObjectUtil() {
    }

    public static <T> T defaultOnNull(T valueToSet, T valueToDefault) {
        return valueToSet == null ? valueToDefault : valueToSet;
    }
}
