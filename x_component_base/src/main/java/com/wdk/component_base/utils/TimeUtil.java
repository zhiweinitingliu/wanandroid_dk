package com.wdk.component_base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/31 4:08 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/31 4:08 PM
 * @LastCheckBy: wdk
 */
public class TimeUtil {
    /**
     * 根据传递的类型格式化时间
     *
     * @param time
     * @param type 例如：yy-MM-dd  HH:mm
     * @return
     */
    public static String getDateTimeByMillisecond(long time, String type) {
        String timeStr="";
        try {
            Date date = new Date(time);
            SimpleDateFormat format = new SimpleDateFormat(type);
            timeStr = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeStr;
    }
}
