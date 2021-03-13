package com.automationpractice.pageObjects.testdata;

import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

public class TestData {
    public static final String ACCT_EMAIL = "automationpractice@yopmail.com";
    public static final String ACCT_EMAIL_INVALID = ACCT_EMAIL.replace(".com", "");
    public static final String ACCT_PASSWORD_VALID = "UnknownP@zzw0rd!";
    public static final String ACCT_PASSWORD_INVALID = passwords().type(MEDIUM).get();
}
