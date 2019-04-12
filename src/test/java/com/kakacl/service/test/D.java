package com.kakacl.service.test;

import java.text.*;
import java.util.*;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/14 14:52<br/>
 * ${TAGS}
 */
public class D {

    // 定义当年的节假日日期数组
    private static String[] holidays = { "2018-01-01" };

    // 调休上班的周末日期数组
    private static String[] workWeekends = { "2018-02-11" };

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Date par = new Date();
        System.out.println(sdf.format(getAddDate(par, 15)));;
    }

    public static Date getAddDate(Date startDate, int daysNum) {
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

}
