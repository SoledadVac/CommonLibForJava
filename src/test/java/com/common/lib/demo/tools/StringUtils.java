package com.common.lib.demo.tools;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/3/11
 * \* Time: 3:13 PM
 * \* Description:
 * \
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }
}
