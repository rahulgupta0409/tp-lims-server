package com.tilakpathology.application.Tilak.Pathology.App.constants;

import java.util.regex.Pattern;

public class RegexpConstants {

    private RegexpConstants(){

    }

    public static final Pattern EMAIL_REGEXP = Pattern.compile("\\S*@[0-9a-zA-Z.]*[0-9a-zA-Z]\\.[0-9a-zA-Z]{2,}");

    public static final Pattern PHONE_NUMBER_REGEXP = Pattern.compile("^\\\\+?[1-9][0-9]{7,14}$");
    public static final String DATE_MONTH_PATTERN = "dd-MM-yyyy hh:mm:ss";

    public static final Integer OTP_MIN_VALUE = 11111;

    public static final Integer OTP_MAX_VALUE = 99999;



}
