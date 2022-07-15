package cn.jane.xjutils.android.version;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

import cn.jane.xjutils.android.app.AppUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/13
 * @describe 描述：desc
 */
public class VersionUtils {

    public static String getVersionName() {
        try {
            PackageManager pm = AppUtils.getApplication().getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(AppUtils.getContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "v1.0";
    }

    public static int getVersionCode() {
        try {
            PackageManager pm = AppUtils.getApplication().getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(AppUtils.getContext().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
