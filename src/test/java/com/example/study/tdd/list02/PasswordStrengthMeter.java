package com.example.study.tdd.list02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVAlID;

        int metCounts = getMetCtriteriaCounts(s);

        if(metCounts <= 1) return  PasswordStrength.WEAK;
        if(metCounts == 2) return  PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private int getMetCtriteriaCounts(String s) {
        int metCounts = 0;
        if(s.length() >= 8) metCounts++;
        if(containsUpp(s)) metCounts++;
        if(meetsContainingNumberCriteria(s)) metCounts++;
        return metCounts;
    }

    private boolean containsUpp(String s) {
        for (char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
