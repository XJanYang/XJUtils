package cn.jane.xjutils.android.app;

import android.app.Application;
import android.content.Context;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class AppUtils {

    public static Application mApplication;

    public AppUtils() {
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    public static void init(Application application) {
        if (application != null) {
            mApplication = application;
        }
    }

    public static Application getApplication() {
        if (mApplication != null) {
            return mApplication;
        }
        throw new NullPointerException("AppUtils:u should init before use");
    }

    public static Context getContext() {
        if (mApplication != null) {
            return mApplication.getApplicationContext();
        }
        throw new NullPointerException("AppUtils:u should init before use");
    }

    public static void destory(){
        mApplication = null;
    }

    public static void clear(){
        mApplication = null;
    }
}
