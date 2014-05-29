package com.appsomehow.ramadan.utilities;

import android.content.Context;

import com.appsomehow.ramadan.model.Region;
import com.appsomehow.ramadan.model.TimeTable;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sharif on 5/28/2014.
 */
public class UIUtils {
    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }

    public static TimeTable compareCurrentDate(List<TimeTable> timeTables) {
        for (TimeTable timeTable : timeTables) {
            if (timeTable.getDate().equals(getCurrentDate())) {
                return timeTable;
            }
        }
        return null;
    }


    public static String dateToString(Date date) {
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat(Constants.DATE_FORMAT);
            return dateformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date stringToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Region getSelectedLocation(List<Region> regions, String selectedLocation) {
        for (Region region : regions) {
            if (region.getName().equals(selectedLocation)) {
                return region;
            }
        }
        return null;
    }

    public static StringBuilder getIftarTime(int interval, TimeTable timeTable, Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_HOUR_MINUTE);
        String dateTime = dateToString(timeTable.getDate()) + " " + timeTable.getIfterTime();
        try {
            Date date = simpleDateFormat.parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, date.getYear());
            calendar.set(Calendar.MONTH, date.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, date.getDay());
            calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
            calendar.set(Calendar.MINUTE, date.getMinutes() + interval);

            return Constants.banglaReplaceCharacter(context, calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
