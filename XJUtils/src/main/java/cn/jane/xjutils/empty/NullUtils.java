package cn.jane.xjutils.empty;

import java.util.Collection;
import java.util.Map;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class NullUtils {

    public NullUtils(){
        throw new UnsupportedOperationException("NullUtils can't be instantiated ...");
    }

    /**
     * 判断字符串是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(String value) {
        return !isNull(value);
    }

    /**
     * 判断字符串是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(String value) {
        if (value == null) {
            return true;
        }
        if (value.length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断整形值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Integer value) {
        return !isNull(value);
    }

    /**
     * 判断整形值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Integer value) {
        if (value == null) {
            return true;
        }
        return false;
    }


    /**
     * 判断长整形值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Long value) {
        return !isNull(value);
    }

    /**
     * 判断长整形值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Long value) {
        if (value == null) {
            return true;
        }
        return false;
    }


    /**
     * 判断Double值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Double value) {
        return !isNull(value);
    }

    /**
     * 判断Double值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Double value) {
        if (value == null) {
            return true;
        }
        return false;
    }


    /**
     * 判断浮点形值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Float value) {
        return !isNull(value);
    }

    /**
     * 判断浮点形值是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Float value) {
        if (value == null) {
            return true;
        }
        return false;
    }


    /**
     * 判断Object是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }

    /**
     * 判断Object是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Object value) {
        if (value == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Collection value) {
        return !isNull(value);
    }

    /**
     * 判断集合是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Collection value) {
        if (value == null) {
            return true;
        }
        if (value.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * 判断Map是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(Map value) {
        return !isNull(value);
    }

    /**
     * 判断Map是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(Map value) {
        if (value == null) {
            return true;
        }
        if (value.isEmpty()) {
            return true;
        }
        return false;
    }
}
