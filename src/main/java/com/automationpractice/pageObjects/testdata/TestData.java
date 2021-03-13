package com.automationpractice.pageObjects.testdata;

import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

public class TestData {
    public static final String ACCT_EMAIL = "automationpractice@yopmail.com";
    public static final String ACCT_EMAIL_UPPER_CASE = ACCT_EMAIL.toUpperCase();
    public static final String ACCT_PASSWORD_VALID = "UnknownP@zzw0rd!";
    public static final String ACCT_PASSWORD_VALID_UPPER_CASE = ACCT_PASSWORD_VALID.toUpperCase(); // Invalid
    public static final String ACCT_EMAIL_INVALID = ACCT_EMAIL.replace(".com", "");
    public static final String ACCT_EMAIL_INVALID_EMPTY = "";
    public static final String ACCT_PASSWORD_INVALID = passwords().type(MEDIUM).get();
    public static final String ACCT_PASSWORD_INVALID_EMPTY = "";
    public static final String ACCT_PASSWORD_INVALID_LESS_THAN_MINIMUM_REQUIRED = ACCT_PASSWORD_VALID.substring(0, 3);
}
