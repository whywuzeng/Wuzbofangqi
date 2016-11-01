package com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils;

/**
 * Created by Administrator on 2016-11-01.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class converWeekDay  {

    public static String converweekday(int weekday)
    {
        String day="";
        switch (weekday)
        {
            case 0:
                day="日";
                break;
            case 1:
                day="一";
                break;
            case 2:
                day="二";
                break;
            case 3:
                day="三";
                break;
            case 4:
                day="四";
                break;
            case 5:
                day="五";
                break;
            case 6:
                day="六";
                break;
        }

        return day;
    }
}
