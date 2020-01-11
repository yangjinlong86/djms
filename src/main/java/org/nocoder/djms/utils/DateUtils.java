package org.nocoder.djms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pufangfei@163.com
 * @ClassName: DateUtils
 * @Description: TODO
 * @date 2017年11月12日 下午5:13:04
 */

public class DateUtils {

    public static String date2Str(Date date, String format) {
        String dateStr = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateStr = sdf.format(date);
        }
        return dateStr;
    }
}
