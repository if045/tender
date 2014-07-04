package com.softserveinc.tender.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.softserveinc.tender.util.Constants.DATE_PATTERN;

public class Util {
    @Autowired
    private static UtilMapper myModelMapper;

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

   /* public static List<?> mapObjects(List<?> source, Class destination) {
        List<Object> result = new ArrayList<>();

        for (Object obj : source) {
            result.add(myModelMapper.map(obj, destination));
        }
        return result;
    }

    public static List<?> mapObjects(List<?> source, String objectClass, Class destination) {
        List<Object> result = new ArrayList<>();
        Object odj;
        for (Object obj : source) {
            try {
                obj = myModelMapper.map((Class.forName(objectClass))obj, destination);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            result.add(obj);
        }
        return result;
    }
*/

}
