package cn.jane.xjutils;

import android.app.Application;

import cn.jane.xjutils.android.app.AppUtils;
import cn.jane.xjutils.android.version.VersionUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class XJUtils {

    public XJUtils() {
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    public static void init(Application context) {
        AppUtils.init(context);
    }

    public static void destory() {
        AppUtils.destory();
    }

    public static void clear() {
        AppUtils.clear();
    }
}
