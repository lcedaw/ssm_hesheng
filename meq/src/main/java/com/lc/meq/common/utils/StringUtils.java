package com.lc.meq.common.utils;

/**
 * 字符串处理工具类
 * @author ljz
 * @since 4.1.0
 */
public class StringUtils {

	public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
