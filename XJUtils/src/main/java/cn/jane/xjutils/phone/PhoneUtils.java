package cn.jane.xjutils.phone;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/13
 * @describe 描述：desc
 */
public class PhoneUtils {

    public boolean isPhoneNum(String value) {
        value = value.trim();
        value = value.replace("+86", "");
        if (value.length() != 11) {
            return false;
        }
        if (value.matches("1+[0-9]")){

        }
        return true;
    }


}
