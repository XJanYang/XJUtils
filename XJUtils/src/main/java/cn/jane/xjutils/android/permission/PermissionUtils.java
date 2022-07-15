package cn.jane.xjutils.android.permission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.jane.xjutils.android.app.AppUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/15
 * @describe 描述：desc
 */
public class PermissionUtils {

    public PermissionUtils (){
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    /**
     * 获取应用权限
     * Return the permissions used in application.
     *
     * @param packageName The name of the package.
     * @return the permissions used in application
     */
    public static List<String> getPermissions(final String packageName) {
        PackageManager pm = AppUtils.getContext().getPackageManager();
        try {
            return Arrays.asList(
                    pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
                            .requestedPermissions
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static boolean isGranted(final String... permissions){
        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGranted(final String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PackageManager.PERMISSION_GRANTED
                == ContextCompat.checkSelfPermission(AppUtils.getContext(), permission);
    }

    public static void requestPermission(Activity activity, final String[] permissions){
        ActivityCompat.requestPermissions(activity, permissions, 0);
    }

    /**
     * 打开应用系统设置
     */
    public static void gotoAppSystemSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + AppUtils.getApplication().getPackageName()));
        AppUtils.getApplication().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
