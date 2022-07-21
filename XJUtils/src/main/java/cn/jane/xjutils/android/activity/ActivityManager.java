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

    public static void add(Activity activity){
        activityStack.add(activity);
    }

    public static void remove(Activity activity){
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前Activity
     * 最后一个入栈的
     * @return
     */
    public static Activity getCurrent() {
        return activityStack.lastElement();
    }

    public static void finish(){
        finish(getCurrent());
    }

    public static void finish(Activity activity) {
        try {
        if (activity != null) {
            remove(activity);
            activity.finish();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Activity getActivity(String clsName) {
        try {
            for (Activity a : activityStack) {
                if (a.getClass().getSimpleName().equals(clsName)) {
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
