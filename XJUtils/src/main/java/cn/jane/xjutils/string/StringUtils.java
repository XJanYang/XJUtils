package cn.jane.xjutils.string;

import cn.jane.xjutils.empty.NullUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNotNull(String value){
        return NullUtils.isNotNull(value);
    }

    /**
     * 判断字符串是否为空
     *
     * @param value 字符串
     * @return boolean
     */
    public static boolean isNull(String value){
        return NullUtils.isNull(value);
    }

    /**
     * 判断两个字符串是否一样
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == "" && str2 == "") {
            return true;
        }
        if (isNull(str1) || isNull(str2)) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 计算长度
     * @param string
     * @return
     */
    public static int getLength(String string) {
        if (isNull(string)) {
            return 0;
        }
        int length = 0;
        StringBuffer stringBuffer = new StringBuffer(string);
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < string.length(); i++) {
            String temp = string.substring(i, i+1);
            if (temp.matches(chinese)) {
                // 中文长度+2
                length += 2;
            } else {
                // 其他符号长度+1
                length += 1;
            }
        }
        return length;
    }
    /**
     * 判断字符串是否大于4K
     * @param string
     * @return
     */
    public static boolean isLargerThan4K(String string){
        if (string.getBytes().length > 4*1024) {
            return true;
        }
        return false;
    }

    public static byte[] string2Byte(String string) {
        byte[] bytes = string.getBytes();
        return bytes;
    }
}
