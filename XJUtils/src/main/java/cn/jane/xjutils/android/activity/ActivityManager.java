package cn.jane.xjutils.android.activity;

import android.app.Activity;

import java.util.Stack;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/13
 * @describe 描述：desc
 */
public class ActivityManager {

    static Stack<Activity> activityStack;
    static ActivityManager mManager;

    public static ActivityManager getInstance(){
        if (mManager == null) {
            mManager = new ActivityManager();
        }
        return mManager;
    }

    public ActivityManager(){
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public static void addActivity(Activity activity){
        activityStack.add(activity);
    }
}
