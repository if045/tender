package com.softserveinc.tender.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public final static String DATE_PATTERN = "yyyy/MM/dd";

    public static Date formatDate(String dateValue) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        Date date = null;

        try {
            date = formatter.parse(dateValue);
        } catch (ParseException e) {
            e.getMessage();
        }
        return date;
    }
}
