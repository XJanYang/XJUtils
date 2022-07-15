package cn.jane.xjutils.android.textview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

import cn.jane.xjutils.android.app.AppUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/14
 * @describe 描述：desc
 */
public class EditUtils {

    /**
     * 复制文字到剪贴板
     *
     * @param string
     */
    public static void copy(String string) {
        ClipboardManager clipboardManager = (ClipboardManager) AppUtils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", string);
        clipboardManager.setPrimaryClip(clipData);
    }

    /**
     * 获取剪贴板里内容
     *
     * @return
     */
    public static String getTextFromClip(){
        ClipboardManager clipboardManager = (ClipboardManager) AppUtils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip()) {
            ClipData clipData = clipboardManager.getPrimaryClip();
            return clipboardManager.getPrimaryClipDescription().getLabel().toString();
//            return clipData.getItemAt(0).getText().toString();
        }
        return "";
    }

    /**
     * 清空剪贴板内容
     */
    public static void clearClip() {
        ClipboardManager clipboardManager = (ClipboardManager) AppUtils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            clipboardManager.clearPrimaryClip();
        }
    }
}
