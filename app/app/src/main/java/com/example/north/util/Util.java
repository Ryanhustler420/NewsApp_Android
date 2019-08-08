package com.example.north.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static synchronized String dateFormetted(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        Date date = null;

        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
        return dateFormat.format(date);
    }
}
