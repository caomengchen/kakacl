package cn.stylefeng.guns.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/14 14:00<br/>
 * ${TAGS}
 */
public class DateUtils {

    /**
     * 自然天数增加
     * @param dateTime
     * @param days
     * @return
     */
    public static Date addAndSubtractDaysByGetTime(Date dateTime, int days) {
        java.util.Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);
        calstart.add(java.util.Calendar.DAY_OF_WEEK, days);
        return calstart.getTime();
    }

    // 定义当年的节假日日期数组
    private static String[] holidays = { "2018-01-01" };

    // 调休上班的周末日期数组
    private static String[] workWeekends = { "2018-02-11" };

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 工作日计算
     * @param startDate
     * @param daysNum
     * @return
     */
    public static Date getWorkDate(Date startDate, int daysNum) {
        List<String> holidaysList = Arrays.asList(holidays);
        List<String> workWeekendsList = Arrays.asList(workWeekends);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int num = 0;
        while (num != daysNum) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            String dateStr = sdf.format(cal.getTime());
            if (holidaysList.contains(dateStr)) { // 是节假日
                continue; // 结束当前循环
            } else if (isWeekend(cal)) { // 是周末
                if (workWeekendsList.contains(dateStr)) { // 是补班的周末
                    num++;
                }
            } else {
                num++;
            }
        }
        // System.out.println("结果：" + sdf.format(cal.getTime()));
        return cal.getTime();
    }

    // 判断是不是周末
    public static boolean isWeekend(Calendar cal) {
        if (cal != null) {
            if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                return true;
            }
        }
        return false;
    }

    /*
     *
     * 获取当前的时间
     * @author wangwei
     * @date 2019/2/22
      * @param pattern 获取的格式，如：yyyy-MM-dd
     * @return java.lang.String
     */
    public static String getDate(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String result = df.format(new Date());
        return result;
    }

    /*
     *
     * 获取昨天的时间
     * @author wangwei
     * @date 2019/2/22
     * @param pattern 获取的格式，如：yyyy-MM-dd
     * @return java.lang.String
     */
    public static String getYesterdayDate(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time = cal.getTime();
        String result = df.format(time);
        return result;
    }

    /*
     *
     * 获取昨天的时间-返回秒
     * @author wangwei
     * @return java.lang.Long
     */
    public static long getYesterdayDateSecond() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return cal.getTimeInMillis() / 1000;
    }

    /*
     *
     * 获取现在的时间-返回秒
     * @author wangwei
     * @return java.lang.Long
     */
    public static long getCurrentDateSecond() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,0);
        return cal.getTimeInMillis() / 1000;
    }

    /*
     *
     * 将时间秒转换为指定格式
     * @author wangwei
     * @return java.lang.Long
     */
    public static String getSecondToString(long seconds, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(seconds * 1000);
        Date date = c.getTime();
        String result = df.format(date);
        return result;
    }

    /*
     *
     * 将yyyy-MM-dd格式的时间转换为秒
     * @author wangwei
     * @return java.lang.Long
     */
    public static String dateToSecond(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime() / 1000;
            res = String.valueOf(ts);
            return res;
        } catch (Exception e) {
            return null;
        }
    }
}
