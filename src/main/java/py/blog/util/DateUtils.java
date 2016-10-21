package py.blog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zczhao on 2015-11-17.
 */
public class DateUtils {

    public static final SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");

    public static Calendar daysAgo(int days) {
        return daysAgo(days, null);
    }

    public static Calendar daysAgo(int days, Calendar calendar) {
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }

        calendar.add(Calendar.DATE, -days);
        clearHMS(calendar);

        return calendar;
    }

    public static Calendar daysAfter(int days) {
        return daysAfter(days, null);
    }

    public static Calendar daysAfter(int days, Calendar calendar) {
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }

        calendar.add(Calendar.DATE, days);
        clearHMS(calendar);
        return calendar;
    }

    public static void clearHMS(Calendar calendar) {
        calendar.add(Calendar.HOUR_OF_DAY, -calendar.get(Calendar.HOUR_OF_DAY));
        calendar.add(Calendar.MINUTE, -calendar.get(Calendar.MINUTE));
        calendar.add(Calendar.SECOND, -calendar.get(Calendar.SECOND));
        calendar.add(Calendar.MILLISECOND, -calendar.get(Calendar.MILLISECOND));
    }

    public static Date clearHMS(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        clearHMS(calendar);

        return calendar.getTime();
    }

    public static Calendar monthsAgo(int months) {
        return monthsAgo(months, null);
    }

    public static Calendar monthsAgo(int months, Calendar calendar) {
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }

        calendar.add(Calendar.MONTH, -months);
        clearHMS(calendar);

        return calendar;
    }

    public static Calendar secondsAfter(int seconds) {
        return secondsAfter(seconds, null);
    }

    public static Calendar secondsAfter(int seconds, Calendar calendar) {
        if (null == calendar) {
            calendar = Calendar.getInstance();
        }

        calendar.add(Calendar.SECOND, seconds);

        return calendar;
    }

    public static Calendar firstDayOfMonth(){
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH, - dayOfMonth + 1);

        clearHMS(calendar);
        return calendar;
    }

    public static Calendar lastDayOfMonth(){
        Calendar calendar = firstDayOfMonth();
        calendar.add(Calendar.MONTH, 1);
        return calendar;
    }

    public static void main(String[] args) {
        Date date = new Date();
        Date date1 = DateUtils.clearHMS(date);

        System.out.printf(date1.toString());
    }

}
