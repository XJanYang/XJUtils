package cn.jane.xjutils.android.bar;

import android.content.res.Resources;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/15
 * @describe 描述：desc
 */
public class BarUtils {

    public static int getStatuesBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    private static int getActionBarHeight() {

        return 0;
    }
}
