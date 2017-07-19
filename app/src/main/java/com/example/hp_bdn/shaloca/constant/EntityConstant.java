package com.example.hp_bdn.shaloca.constant;

import java.util.regex.Pattern;

/**
 * Created by HP_BDN on 20-Jul-17.
 */

public class EntityConstant {
    public static  int  Min_Char_Password =  6 ;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
