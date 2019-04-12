package cn.stylefeng.guns.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wangwei<br/>
 * @Description: <br/>
 * @date 2018/12/14 13:23<br/>
 * ${TAGS}
 */
public class HolidayUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        //查询数据库中holiday,遍历存入list（表中每条记录存放的是假期的起止日期,遍历每条结果,并将其中的每一天都存入holiday的list中），以下为模拟假期
        List holidayList = new ArrayList();
//        holidayList.add("2018-08-29");
//        holidayList.add("2018-08-30");
//        holidayList.add("2018-10-01");
//        holidayList.add("2018-10-02");
//        holidayList.add("2018-10-03");
//        holidayList.add("2018-10-04");
//        holidayList.add("2018-10-05");
//        holidayList.add("2018-10-06");
        holidayList.add(new Date());
        Date date = new Date();

        //获取计划激活日期
        Date scheduleActiveDate = getScheduleActiveDate(holidayList, date, 10);
        System.out.println("10个工作日后,即计划激活日期为::" + sdf.format(scheduleActiveDate));
    }

    //获取计划激活日期
    public static Date getScheduleActiveDate(List<String> list, Date paramsDate, int days) throws ParseException {
//        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());//获取当前日期2018-08-26
        Date today = paramsDate;//Mon Aug 27 00:09:29 CST 2018
        Date tomorrow = null;
        int delay = 1;
        int num = days;//根据需要设置,这个值就是业务需要的n个工作日
        while(delay <= num){
            tomorrow = getTomorrow(today);
            //当前日期+1即tomorrow,判断是否是节假日,同时要判断是否是周末,都不是则将scheduleActiveDate日期+1,直到循环num次即可
            if(!isWeekend(sdf.format(tomorrow)) && !isHoliday(sdf.format(tomorrow),list)){
                delay++;
                today = tomorrow;
            } else if (isWeekend(sdf.format(tomorrow))){
//                tomorrow = getTomorrow(tomorrow);
                today = tomorrow;
                //System.out.println(sdf.format(tomorrow) + "::是周末");
            } else if (isHoliday(sdf.format(tomorrow),list)){
//                tomorrow = getTomorrow(tomorrow);
                today = tomorrow;
                //System.out.println(sdf.format(tomorrow) + "::是节假日");
            }
        }
        //System.out.println("n个工作日后,即计划激活日期为::" + sdf.format(today));
        return today;
    }

    /**
     * 获取明天的日期
     *
     * @param date
     * @return
     */
    public static Date getTomorrow(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 判断是否是weekend
     *
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(String sdate) throws ParseException {
        Date date = sdf.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        } else{
            return false;
        }

    }

    /**
     * 判断是否是holiday
     *
     * @param sdate
     * @param list
     * @return
     * @throws ParseException
     */
    public static boolean isHoliday(String sdate, List<String> list) throws ParseException {
        if(list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                if(sdate.equals(list.get(i))){
                    return true;
                }
            }
        }
        return false;
    }
}
