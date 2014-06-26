package com.softserveinc.tender.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.softserveinc.tender.util.Constants.DATE_PATTERN;

public class Util {

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

    public static Timestamp setCurrentTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }

    public static String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
