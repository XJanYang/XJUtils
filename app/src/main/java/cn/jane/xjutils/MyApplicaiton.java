package cn.jane.xjutils;

import android.app.Application;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class MyApplicaiton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XJUtils.init(this);
    }
}
