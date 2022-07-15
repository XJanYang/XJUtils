package cn.jane.xjutils.android.resource;

import android.content.Context;

import androidx.annotation.DimenRes;
import androidx.annotation.StringRes;

import cn.jane.xjutils.android.app.AppUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class ResourceUtils {

    public ResourceUtils(){
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    public static String getString(@StringRes int strId) {
        return getString(AppUtils.getContext(), strId);
    }
    public static String getString(Context context, @StringRes int strId) {
        return context.getResources().getString(strId);
    }



    public static int getDimension(@DimenRes int dimenId) {
        return getDimension(AppUtils.getContext(), dimenId);
    }
    public static int getDimension(Context context, @DimenRes int dimenId) {
        return (int) context.getResources().getDimension(dimenId);
    }
}
