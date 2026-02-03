package com.threewd.soft.util;

import com.threewd.soft.model.Supplier;

public class SupplierUtil {

    public static boolean hasAnyEmptyField(Supplier s) {
        if (s == null) return true;

        return isEmpty(s.getSupplierName())
            || isEmpty(s.getEmail())
            || isEmpty(s.getContactNo());
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}

