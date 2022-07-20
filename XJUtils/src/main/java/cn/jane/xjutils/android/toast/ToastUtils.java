package cn.jane.xjutils.android.toast;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.lang.ref.WeakReference;

import cn.jane.xjutils.R;
import cn.jane.xjutils.android.app.AppUtils;
import cn.jane.xjutils.android.resource.ResourceUtils;
import cn.jane.xjutils.empty.NullUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/13
 * @describe 描述：desc
 */
public class ToastUtils {

    public ToastUtils() {
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    /**
     * 字符长度超过该数字，自动变成longToast
     */
    private static int TOAST_MAX_LINE_LENGTH = 11;

    private static WeakReference<Toast> softReference;
    private static int TOAST_GRAVITY = Gravity.CENTER;

    private static int IC_SUCCESS = R.mipmap.ic_toast_success;
    private static int IC_ERROR = R.mipmap.ic_toast_error;
    private static int IC_WARN = R.mipmap.ic_toast_warn;

    /**
     * 设置单行最大字符数
     *
     * @param maxLineLength
     */
    public static void setMaxLineLength(int maxLineLength) {
        TOAST_MAX_LINE_LENGTH = maxLineLength;
    }

    public static void setSucIcon(int sucIcon) {
        IC_SUCCESS = sucIcon;
    }

    public static void setErrorIcon(int errorIcon) {
        IC_ERROR = errorIcon;
    }

    public static void setWarnIcon(int warnIcon) {
        IC_WARN = warnIcon;
    }

    /**
     * 设置吐司位置
     *
     * @param gravity 位置
     */
    public static void setGravity(int gravity) {
        TOAST_GRAVITY = gravity;
    }

    /**
     * 显示短时吐司
     *
     * @param message 消息
     */
    public static void show(@StringRes int message) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (message == 0) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toast(message);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> toast(message));
        }
    }

    /**
     * 显示短时吐司
     *
     * @param message 消息
     */
    public static void show(String message) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        final String msg = message.replace("<br>", "").replace("</br>", "");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toast(msg);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> toast(msg));
        }
    }

    /**
     * 成功
     *
     * @param message
     */
    public static void showSuc(String message) {
        if (message.length() > TOAST_MAX_LINE_LENGTH) {
            show(message);
        } else {
            showWithImg(message, IC_SUCCESS);
        }
    }

    /**
     * 成功
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showSuc(String message, boolean showIcon) {
        if (showIcon) {
            showSuc(message);
        } else {
            if (message.length() > TOAST_MAX_LINE_LENGTH) {
                showLong(message);
            } else {
                show(message);
            }
        }
    }

    /**
     * 成功
     *
     * @param message
     */
    public static void showSuc(@StringRes int message) {
        String msg = ResourceUtils.getString(message);
        if (msg.length() > TOAST_MAX_LINE_LENGTH) {
            show(message);
        } else {
            showWithImg(message, IC_SUCCESS);
        }
    }

    /**
     * 成功
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showSuc(@StringRes int message, boolean showIcon) {
        String msg = ResourceUtils.getString(message);
        if (showIcon) {
            showSuc(message);
        } else {
            if (msg.length() > TOAST_MAX_LINE_LENGTH) {
                showLong(message);
            } else {
                show(message);
            }
        }
    }

    /**
     * 错误
     *
     * @param message
     */
    public static void showError(String message) {
        if (message.length() > TOAST_MAX_LINE_LENGTH) {
            show(message);
        } else {
            showWithImg(message, IC_ERROR);
        }
    }

    /**
     * 错误
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showError(String message, boolean showIcon) {
        if (showIcon) {
            showError(message);
        } else {
            if (message.length() > TOAST_MAX_LINE_LENGTH) {
                showLong(message);
            } else {
                show(message);
            }
        }
    }

    /**
     * 错误
     *
     * @param message
     */
    public static void showError(@StringRes int message) {
        String msg = ResourceUtils.getString(message);
        if (msg.length() > TOAST_MAX_LINE_LENGTH) {
            show(message);
        } else {
            showWithImg(message, IC_ERROR);
        }
    }

    /**
     * 错误
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showError(@StringRes int message, boolean showIcon) {
        String msg = ResourceUtils.getString(message);
        if (showIcon) {
            showError(msg);
        } else {
            if (msg.length() > TOAST_MAX_LINE_LENGTH) {
                showLong(message);
            } else {
                show(message);
            }
        }
    }

    /**
     * 警告
     *
     * @param message
     */
    public static void showWarn(String message) {
        if (message.length() > TOAST_MAX_LINE_LENGTH) {
            show(message);
        } else {
            showWithImg(message, IC_WARN);
        }
    }

    /**
     * 警告
     *
     * @param message
     */
    public static void showWarn(int message) {
        String msg = ResourceUtils.getString(message);
        if (msg.length() > TOAST_MAX_LINE_LENGTH) {
            show(message);
        } else {
            showWithImg(message, IC_WARN);
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(String message, @DrawableRes int imgId) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        final String msg = message.replace("<br>", "").replace("</br>", "");
        if (NullUtils.isNull(msg)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toastWithImg(msg, imgId);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                toastWithImg(msg, imgId);
            });
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(@StringRes int message, @DrawableRes int imgId) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (0 == message) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toastWithImg(message, imgId);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                toastWithImg(message, imgId);
            });
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(@NonNull String message, @DrawableRes int imgId, boolean isLong) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toastWithImg(message, imgId, isLong);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                toastWithImg(message, imgId, isLong);
            });
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(@StringRes int message, @DrawableRes int imgId, boolean isLong) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (0 == message) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toastWithImg(message, imgId, isLong);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                toastWithImg(message, imgId, isLong);
            });
        }
    }

    /**
     * 显示自定义时间吐司
     *
     * @param message     消息
     * @param isLongToast 是否长提醒
     */
    public static void show(@NonNull String message, boolean isLongToast) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        final String msg = message.replace("<br>", "").replace("</br>", "");
        if (NullUtils.isNull(msg)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            toast(msg, isLongToast);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                toast(msg, isLongToast);
            });
        }
    }

    /**
     * 显示长时吐司
     *
     * @param message 消息
     */
    public static void showLong(@NonNull String message) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        final String msg = message.replace("<br>", "").replace("</br>", "");
        if (NullUtils.isNull(msg)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            longToast(msg);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                longToast(msg);
            });
        }
    }

    /**
     * 显示长时吐司
     *
     * @param message 消息
     */
    public static void showLong(@StringRes int message) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (message == 0) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            longToast(message);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                longToast(message);
            });
        }
    }

    //---------------------------------------toast-----------------------------------------------------

    public static void toast(@StringRes int message) {
        toast(R.layout.layout_tost, R.drawable.shape_bg_toast, message, false);
    }

    public static void toast(@NonNull String message) {
        toast(R.layout.layout_tost, R.drawable.shape_bg_toast, message, false);
    }

    public static void longToast(@StringRes int message) {
        toast(R.layout.layout_tost, R.drawable.shape_bg_toast, message, true);
    }

    public static void longToast(@NonNull String message) {
        toast(R.layout.layout_tost, R.drawable.shape_bg_toast, message, true);
    }

    public static void toast(@StringRes int message, boolean isLongToast) {
        toast(R.layout.layout_tost, R.drawable.shape_bg_toast, message, isLongToast);
    }

    public static void toast(@NonNull String message, boolean isLongToast) {
        toast(R.layout.layout_tost, R.drawable.shape_bg_toast, message, isLongToast);
    }

    public static void toast(@LayoutRes int layout, @DrawableRes int toastBg, @StringRes int message, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, 0, false);
    }

    public static void toast(@LayoutRes int layout, @DrawableRes int toastBg, @NonNull String message, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, 0, false);
    }


    //---------------------------------------toastWithImg-----------------------------------------------------

    public static void toastWithImg(@NonNull String message, @DrawableRes int imgId) {
        toastWithImg(R.layout.layout_tost, R.drawable.shape_bg_toast, message, imgId, false);
    }

    public static void toastWithImg(@StringRes int message, @DrawableRes int imgId) {
        toastWithImg(R.layout.layout_tost, R.drawable.shape_bg_toast, message, imgId, false);
    }

    public static void longToastWithImg(@NonNull String message, @DrawableRes int imgId) {
        toastWithImg(R.layout.layout_tost, R.drawable.shape_bg_toast, message, imgId, true);
    }

    public static void longToastWithImg(@StringRes int message, @DrawableRes int imgId) {
        toastWithImg(R.layout.layout_tost, R.drawable.shape_bg_toast, message, imgId, true);
    }

    public static void toastWithImg(@NonNull String message, @DrawableRes int imgId, boolean isLong) {
        toastWithImg(R.layout.layout_tost, R.drawable.shape_bg_toast, message, imgId, isLong);
    }

    public static void toastWithImg(@StringRes int message, @DrawableRes int imgId, boolean isLong) {
        toastWithImg(R.layout.layout_tost, R.drawable.shape_bg_toast, message, imgId, isLong);
    }

    public static void toastWithImg(@LayoutRes int layout, @DrawableRes int toastBg, @StringRes int message, @DrawableRes int imgId, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, imgId, true);
    }

    public static void toastWithImg(@LayoutRes int layout, @DrawableRes int toastBg, @NonNull String message, @DrawableRes int imgId, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, imgId, true);
    }

    /**
     * 展示toast
     *
     * @param layout      xml布局
     * @param toastBg     背景
     * @param message     展示文案
     * @param isLongToast 展示时间（long/short）
     * @param imgId       图标
     * @param withImg     是否展示图标
     */
    public static void newInstance(@LayoutRes int layout, @DrawableRes int toastBg, @StringRes int message, boolean isLongToast, @DrawableRes int imgId, boolean withImg) {
        cancelTosat();
        if (message == 0) {
            return;
        }
        if (softReference == null) {
            softReference = new WeakReference<>(Toast.makeText(AppUtils.getContext(), message, isLongToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT));
        }
        LinearLayout view = (LinearLayout) LayoutInflater.from(AppUtils.getContext()).inflate(layout, null);
        view.setBackgroundResource(toastBg);
        TextView textView = view.findViewById(R.id.tv_message);
        textView.setText(message);
        if (withImg) {
            ImageView img = new ImageView(view.getContext());
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams((int) view.getContext().getResources().getDimension(R.dimen.dp_30), (int) view.getContext().getResources().getDimension(R.dimen.dp_30));
            ll.setMargins(0, 0, 0, (int) view.getContext().getResources().getDimension(R.dimen.dp_10));
            img.setLayoutParams(ll);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setBackgroundResource(imgId);
            view.addView(img, 0);
        }
        softReference.get().setGravity(TOAST_GRAVITY, 0, 0);
        softReference.get().setView(view);
        softReference.get().show();
    }

    /**
     * 展示toast
     *
     * @param layout      xml布局
     * @param toastBg     背景
     * @param message     展示文案
     * @param isLongToast 展示时间（long/short）
     * @param imgId       图标
     * @param withImg     是否展示图标
     */
    public static void newInstance(@LayoutRes int layout, @DrawableRes int toastBg, @NonNull String message, boolean isLongToast, @DrawableRes int imgId, boolean withImg) {
        cancelTosat();
        if (NullUtils.isNull(message)) {
            return;
        }
        if (softReference == null) {
            softReference = new WeakReference<>(Toast.makeText(AppUtils.getContext(), message, isLongToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT));
        }
        LinearLayout view = (LinearLayout) LayoutInflater.from(AppUtils.getContext()).inflate(layout, null);
        view.setBackgroundResource(toastBg);
        TextView textView = view.findViewById(R.id.tv_message);
        textView.setText(message);
        if (withImg) {
            ImageView img = new ImageView(view.getContext());
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams((int) view.getContext().getResources().getDimension(R.dimen.dp_30), (int) view.getContext().getResources().getDimension(R.dimen.dp_30));
            ll.setMargins(0, 0, 0, (int) view.getContext().getResources().getDimension(R.dimen.dp_10));
            img.setLayoutParams(ll);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setBackgroundResource(imgId);
            view.addView(img, 0);
        }
        softReference.get().setGravity(TOAST_GRAVITY, 0, 0);
        softReference.get().setView(view);
        softReference.get().show();
    }

    /**
     * 取消吐司显示
     */
    public static void cancelTosat() {
        if (softReference != null) {
            if (softReference.get() != null) {
                softReference.get().cancel();
            }
            softReference = null;
        }
    }

}
