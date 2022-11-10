package com.example.covid_19alertapp.extras;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeHandler {

    public static String DateToday()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String today_date = dateFormat.format(cal.getTime()) + " " + monthFormat.format(cal.getTime());
        return today_date;
    }

    public static String TimeNow()
    {
        Calendar cal = Calendar.getInstance();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        String time = timeFormat.format(cal.getTime());
        return  time;
    }

    public static long dayInterval(String firstDate)
    {
        firstDate = firstDate.replace(' ','/');
        String secondDate = DateToday().replace(' ','/');
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM");
        Date first = new Date();
        Date second = new Date();


        try {
            first = sdf.parse(firstDate);
            second = sdf.parse(secondDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diffMillies = Math.abs(second.getTime()-first.getTime());
        long diffDay = TimeUnit.DAYS.convert(diffMillies,TimeUnit.MILLISECONDS);

        return  diffDay;
    }

}
