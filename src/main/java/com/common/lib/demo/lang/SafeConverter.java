/*
 * SHANGHAI SUNNY EDUCATION, INC. CONFIDENTIAL
 *
 * Copyright 2011-2016 Shanghai Sunny Education, Inc. All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of
 * Shanghai Sunny Education, Inc. and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Shanghai Sunny
 * Education, Inc. and its suppliers and may be covered by patents, patents
 * in process, and are protected by trade secret or copyright law. Dissemination
 * of this information or reproduction of this material is strictly forbidden
 * unless prior written permission is obtained from Shanghai Sunny Education, Inc.
 */

package com.common.lib.demo.lang;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * Safe converter implementation.
 * The safe converter introduced here is to replace weird spring conversion
 * service framework. Yes, spring really did something you really don't
 * know, that's not our expects. What we need is everything under control.
 *
 * @author Xiaohai Zhang
 * @since Apr 25, 2015
 */
abstract public class SafeConverter {

    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static int toInt(Object obj, int defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Long) {
            // 这里可能会丢失精度
            return ((Long) obj).intValue();
        }
        String str = obj.toString();
        str = StringUtils.trim(str);
        return NumberUtils.toInt(str, defaultValue);
    }

    public static long toLong(Object obj) {
        return toLong(obj, 0);
    }

    public static long toLong(Object obj, long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        }
        if (obj instanceof Date) {
            return ((Date) obj).getTime();
        }
        if (obj instanceof Instant) {
            return ((Instant) obj).toEpochMilli();
        }
        String str = obj.toString();
        str = StringUtils.trim(str);
        return NumberUtils.toLong(str, defaultValue);
    }

    public static double toDouble(Object obj) {
        return toDouble(obj, 0);
    }

    public static double toDouble(Object obj, double defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        String str = obj.toString();
        str = StringUtils.trim(str);
        return NumberUtils.toDouble(str, defaultValue);
    }

    public static float toFloat(Object obj) {
        return toFloat(obj, 0);
    }

    public static float toFloat(Object obj, float defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Float) {
            return (Float) obj;
        }
        String str = obj.toString();
        str = StringUtils.trim(str);
        return NumberUtils.toFloat(str, defaultValue);
    }

    /**
     * Safe convert specified object to boolean value with default value false.
     * If specified object is null, return false.
     * If specified object is Boolean, return directly.
     * Otherwise, convert the object to string and trim, then compare
     * the string value with "true".
     *
     * @param obj the object to be converted
     * @return boolean value
     * @since 1.17.4
     */
    public static boolean toBoolean(Object obj) {
        return toBoolean(obj, false);
    }

    /**
     * Safe convert specified object to boolean value.
     * If specified object is null, return default value.
     * If specified object is Boolean, return directly.
     * Otherwise, convert the object to string and trim, then compare
     * the string value with "true".
     *
     * @param obj          the object to be converted
     * @param defaultValue the default value
     * @return boolean value
     * @since 1.17.4
     */
    public static boolean toBoolean(Object obj, boolean defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        String str = obj.toString();
        str = StringUtils.trim(str);
        return StringUtils.equals(str, "true");
    }

    public static String toString(Object obj) {
        return toString(obj, null);
    }

    /**
     * Convert specified object to string. Every object can to converted to string
     * except null. Only return default value in case of passed in object is null.
     * null -&gt; default value
     * String -&gt; return directly
     * enum -&gt; enum's name
     * Date -&gt; format the Date to string with pattern yyyy-MM-dd HH:mm:ss.SSS
     * Calender -&gt; format the Calender to string with pattern yyyy-MM-dd HH:mm:ss.SSS
     * others -&gt; invoke toString directly
     *
     * @param obj          the object to be converted to string
     * @param defaultValue the default value in case of null object passed in
     * @return the result string, return default value only in case of null passed in
     */
    public static String toString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        // every non-null object can to converted to string by invoking toString method
        // but pay attention sometimes toString return meaningless string for some objects
        if (obj.getClass().isEnum()) {
            return ((Enum) obj).name();
        }
        if (obj instanceof Date) {
            String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
            return FastDateFormat.getInstance(pattern).format((Date) obj);
        }
        if (obj instanceof Calendar) {
            String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
            return FastDateFormat.getInstance(pattern).format(((Calendar) obj).getTime());
        }
        // invoke object to string directly
        return obj.toString();
    }


}
