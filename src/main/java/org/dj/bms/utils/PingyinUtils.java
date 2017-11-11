package org.dj.bms.utils;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * @ClassName: PingyinUtils
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 下午2:46:57
 */
public class PingyinUtils {

	public static String convert2jp(String hz) {
		try {
			return PinyinHelper.getShortPinyin(hz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String convert2qp(String hz) {
		try {
			return PinyinHelper.convertToPinyinString(hz, "", PinyinFormat.WITHOUT_TONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
