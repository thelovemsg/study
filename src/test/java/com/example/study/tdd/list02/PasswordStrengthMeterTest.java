package com.example.study.tdd.list02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        Assertions.assertEquals(expStr, result);
    }

    @Test
    void meetsAlLCriteria_Then_Strong() {
        assertStrength("ab12!@AB",PasswordStrength.STRONG);
        assertStrength("abc1!Add",PasswordStrength.STRONG);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A",PasswordStrength.NORMAL);
        assertStrength("Ab12!c",PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null ,PasswordStrength.INVAlID);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Week() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUpperCriteria_Then_Week() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
