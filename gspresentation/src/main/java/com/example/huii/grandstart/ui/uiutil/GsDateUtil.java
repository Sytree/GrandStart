package com.example.huii.grandstart.ui.uiutil;

import java.util.Calendar;

/**
 * Created by huii on 17/1/10.
 */
public class GsDateUtil {
    public static String getYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return Integer.toString(year);
    }

    public static String getMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return Integer.toString(month);
    }

    public static String getDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(day);
    }
}
