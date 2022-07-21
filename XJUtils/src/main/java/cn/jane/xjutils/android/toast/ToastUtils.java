package cn.jane.xjutils.android.toast;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DimenRes;
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

    private static WeakReference<Toast> softReference;

    /**
     * 图标展示限制长度：字符长度超过该数字，不再展示图标
     */
    private static int TOAST_IC_LIMIT_LENGTH = 4;

    /**
     * 短文本展示时间限制长度：字符长度超过该数字，短吐司自动变为长吐司
     */
    private static int TOAST_TEXT_LIMIT_LENGTH = 11;

    /**
     * 忽略图标展示限制长度
     */
    private static boolean IGNORE_IC_LIMIT = false;

    /**
     * 忽略短文本展示时间限制长度
     */
    private static boolean IGNORE_TEXT_LIMIT = false;

    /**
     * 吐司展示位置
     */
    private static int TOAST_GRAVITY = Gravity.CENTER;

    private static int IC_SUCCESS = R.mipmap.ic_toast_success;
    private static int IC_ERROR = R.mipmap.ic_toast_error;
    private static int IC_WARN = R.mipmap.ic_toast_warn;

    private static int TOAST_BG = R.drawable.shape_bg_toast;

    private static int TOAST_LAYOUT = R.layout.layout_tost;

    private static int TOAST_ORIENTATION = LinearLayout.VERTICAL;

    /**
     * 图标的宽度
     */
    private static int IC_WIDTH = ResourceUtils.getDimension(R.dimen.dp_30);

    /**
     * 图标的高度
     */
    private static int IC_HEIGHT = ResourceUtils.getDimension(R.dimen.dp_30);

    /**
     * 图标和文字之间的间距
     */
    private static int DISTANCE = ResourceUtils.getDimension(R.dimen.dp_10);

    public ToastUtils() {
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    /**
     * 设置图标限制最大字符数
     * 字符长度超过此数字的toast，不再展示图标
     *
     * @param icLimitLength
     */
    public static void setIcLimitLength(int icLimitLength) {
        TOAST_IC_LIMIT_LENGTH = icLimitLength;
    }

    /**
     * 设置短文本展示时间限制长度
     * 字符长度超过该数字，短吐司自动变为长吐司
     *
     * @param textLimitLength
     */
    public static void setTextLimitLength(int textLimitLength) {
        TOAST_TEXT_LIMIT_LENGTH = textLimitLength;
    }

    /**
     * 忽略图标限制
     *
     * @param ignoreIcLimit
     */
    public static void ignoreIcLimit(boolean ignoreIcLimit) {
        IGNORE_IC_LIMIT = ignoreIcLimit;
    }

    /**
     * 忽略短文本展示时间限制
     *
     * @param ignoreTextLimit
     */
    public static void ignoreTextLimit(boolean ignoreTextLimit) {
        IGNORE_TEXT_LIMIT = ignoreTextLimit;
    }

    /**
     * 设置成功提示的图标
     *
     * @param sucIcon
     */
    public static void setSucIcon(@DrawableRes int sucIcon) {
        IC_SUCCESS = sucIcon;
    }

    /**
     * 设置失败提示的图标
     *
     * @param errorIcon
     */
    public static void setErrorIcon(@DrawableRes int errorIcon) {
        IC_ERROR = errorIcon;
    }

    /**
     * 设置警告提示的图标
     *
     * @param warnIcon
     */
    public static void setWarnIcon(@DrawableRes int warnIcon) {
        IC_WARN = warnIcon;
    }

    /**
     * 设置吐司layout
     *
     * @param toastLayout
     */
    public static void setToastLayout(int toastLayout) {
        TOAST_LAYOUT = toastLayout;
    }

    /**
     * 设置吐司的背景
     *
     * @param toastBg
     */
    public static void setBackground(@DrawableRes int toastBg) {
        TOAST_BG = toastBg;
    }

    /**
     * 设置布局方向
     *
     * @param orientation 取值范围 (1:{@link LinearLayout.VERTICAL},0:{@link LinearLayout.HORIZONTAL})
     */
    public static void setOrientation(int orientation) {
        TOAST_ORIENTATION = orientation;
    }

    /**
     * 设置图标宽度
     *
     * @param icWidth
     */
    public static void setIcWidth(@DimenRes int icWidth) {
        IC_WIDTH = ResourceUtils.getDimension(icWidth);
    }

    /**
     * 设置图标高度
     *
     * @param icHeight
     */
    public static void setIcHeight(@DimenRes int icHeight) {
        IC_HEIGHT = ResourceUtils.getDimension(icHeight);
    }

    /**
     * 设置图标宽高
     *
     * @param icWidth
     * @param icHeight
     */
    public static void setIcWidthAndHeight(@DimenRes int icWidth, @DimenRes int icHeight) {
        setIcWidth(icWidth);
        setIcHeight(icHeight);
    }

    /**
     * 设置吐司位置
     *
     * @param gravity 位置
     */
    public static void setGravity(int gravity) {
        TOAST_GRAVITY = gravity;
    }

    public static void setDistance(int distance) {
        DISTANCE = distance;
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
            if (IGNORE_TEXT_LIMIT || ResourceUtils.getString(message).length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toast(message);
            } else {
                longToast(message);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (IGNORE_TEXT_LIMIT || ResourceUtils.getString(message).length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toast(message);
                } else {
                    longToast(message);
                }
            });
        }
    }

    /**
     * 显示短时吐司
     *
     * @param message 消息
     */
    public static void show(@NonNull String message) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        final String msg = message.replace("<br>", "").replace("</br>", "");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (IGNORE_TEXT_LIMIT || msg.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toast(msg);
            } else {
                longToast(msg);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (IGNORE_TEXT_LIMIT || msg.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toast(msg);
                } else {
                    longToast(msg);
                }
            });
        }
    }

    /**
     * 成功
     *
     * @param message 消息
     */
    public static void showSuc(@NonNull String message) {
        if (IGNORE_IC_LIMIT || message.length() <= TOAST_IC_LIMIT_LENGTH) {
            showWithImg(message, IC_SUCCESS);
        } else {
            show(message);
        }
    }

    /**
     * 成功
     *
     * @param message  消息
     * @param showIcon 是否展示图标
     */
    public static void showSuc(@NonNull String message, boolean showIcon) {
        if (showIcon) {
            showSuc(message);
        } else {
            show(message);
        }
    }

    /**
     * 成功
     *
     * @param message 消息
     */
    public static void showSuc(@StringRes int message) {
        if (IGNORE_IC_LIMIT || ResourceUtils.getString(message).length() <= TOAST_IC_LIMIT_LENGTH) {
            showWithImg(message, IC_SUCCESS);
        } else {
            show(message);
        }
    }

    /**
     * 成功
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showSuc(@StringRes int message, boolean showIcon) {
        if (showIcon) {
            showSuc(message);
        } else {
            show(message);
        }
    }

    /**
     * 错误
     *
     * @param message
     */
    public static void showError(@NonNull String message) {
        if (IGNORE_IC_LIMIT || message.length() <= TOAST_IC_LIMIT_LENGTH) {
            showWithImg(message, IC_ERROR);
        } else {
            show(message);
        }
    }

    /**
     * 错误
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showError(@NonNull String message, boolean showIcon) {
        if (showIcon) {
            showError(message);
        } else {
            show(message);
        }
    }

    /**
     * 错误
     *
     * @param message
     */
    public static void showError(@StringRes int message) {
        if (IGNORE_IC_LIMIT || ResourceUtils.getString(message).length() <= TOAST_IC_LIMIT_LENGTH) {
            showWithImg(message, IC_ERROR);
        } else {
            show(message);
        }
    }

    /**
     * 错误
     *
     * @param message
     * @param showIcon 是否展示图标
     */
    public static void showError(@StringRes int message, boolean showIcon) {
        if (showIcon) {
            showError(message);
        } else {
            show(message);
        }
    }

    /**
     * 警告
     *
     * @param message
     */
    public static void showWarn(@NonNull String message) {
        if (IGNORE_IC_LIMIT || message.length() <= TOAST_IC_LIMIT_LENGTH) {
            showWithImg(message, IC_WARN);
        } else {
            show(message);
        }
    }

    /**
     * 警告
     *
     * @param message  消息
     * @param showIcon 是否展示图标
     */
    public static void showWarn(@NonNull String message, boolean showIcon) {
        if (showIcon) {
            showWarn(message);
        } else {
            show(message);
        }
    }

    /**
     * 警告
     *
     * @param message
     */
    public static void showWarn(@StringRes int message) {
        if (IGNORE_IC_LIMIT || ResourceUtils.getString(message).length() <= TOAST_IC_LIMIT_LENGTH) {
            showWithImg(message, IC_WARN);
        } else {
            show(message);
        }
    }

    /**
     * 警告
     *
     * @param message  消息
     * @param showIcon 是否展示图标
     */
    public static void showWarn(@StringRes int message, boolean showIcon) {
        if (showIcon) {
            showWarn(message);
        } else {
            show(message);
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(@NonNull String message, @DrawableRes int imgId) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (NullUtils.isNull(message)) {
            return;
        }
        final String msg = message.replace("<br>", "").replace("</br>", "");
        if (NullUtils.isNull(msg)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (IGNORE_TEXT_LIMIT || msg.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toastWithImg(msg, imgId);
            } else {
                longToastWithImg(msg, imgId);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (IGNORE_TEXT_LIMIT || msg.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toastWithImg(msg, imgId);
                } else {
                    longToastWithImg(msg, imgId);
                }
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
            if (IGNORE_TEXT_LIMIT || ResourceUtils.getString(message).length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toastWithImg(message, imgId);
            } else {
                longToastWithImg(message, imgId);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (IGNORE_TEXT_LIMIT || ResourceUtils.getString(message).length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toastWithImg(message, imgId);
                } else {
                    longToastWithImg(message, imgId);
                }
            });
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(@NonNull String message, @DrawableRes int imgId, boolean isLongToast) {
        if (NullUtils.isNull(message)) {
            return;
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (isLongToast) {
                longToastWithImg(message, imgId);
            } else if (IGNORE_TEXT_LIMIT || message.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toastWithImg(message, imgId);
            } else {
                longToastWithImg(message, imgId);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (isLongToast) {
                    longToastWithImg(message, imgId);
                } else if (IGNORE_TEXT_LIMIT || message.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toastWithImg(message, imgId);
                } else {
                    longToastWithImg(message, imgId);
                }
            });
        }
    }

    /**
     * 显示带图片的吐司
     *
     * @param message 消息
     * @param imgId   图片id
     */
    public static void showWithImg(@StringRes int message, @DrawableRes int imgId, boolean isLongToast) {
        //处理存管含有br标签的消息，产品暂定app忽略br标签
        if (0 == message) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (isLongToast) {
                longToastWithImg(message, imgId);
            } else if (IGNORE_TEXT_LIMIT || ResourceUtils.getString(message).length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toastWithImg(message, imgId);
            } else {
                longToastWithImg(message, imgId);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (isLongToast) {
                    longToastWithImg(message, imgId);
                } else if (IGNORE_TEXT_LIMIT || ResourceUtils.getString(message).length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toastWithImg(message, imgId);
                } else {
                    longToastWithImg(message, imgId);
                }
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
            if (isLongToast) {
                longToast(message);
            } else if (IGNORE_TEXT_LIMIT || msg.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                toast(message);
            } else {
                longToast(message);
            }
        } else {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (isLongToast) {
                    longToast(message);
                } else if (IGNORE_TEXT_LIMIT || msg.length() <= TOAST_TEXT_LIMIT_LENGTH) {
                    toast(message);
                } else {
                    longToast(message);
                }
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
        toast(message, false);
    }

    public static void toast(@NonNull String message) {
        toast(message, false);
    }

    public static void longToast(@StringRes int message) {
        toast(message, true);
    }

    public static void longToast(@NonNull String message) {
        toast(message, true);
    }

    public static void toast(@StringRes int message, boolean isLongToast) {
        toast(TOAST_LAYOUT, TOAST_BG, message, isLongToast);
    }

    public static void toast(@NonNull String message, boolean isLongToast) {
        toast(TOAST_LAYOUT, TOAST_BG, message, isLongToast);
    }

    public static void toast(@LayoutRes int layout, @DrawableRes int toastBg, @StringRes int message, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, 0, false);
    }

    public static void toast(@LayoutRes int layout, @DrawableRes int toastBg, @NonNull String message, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, 0, false);
    }


    //---------------------------------------toastWithImg-----------------------------------------------------

    public static void toastWithImg(@NonNull String message, @DrawableRes int imgId) {
        toastWithImg(message, imgId, false);
    }

    public static void toastWithImg(@StringRes int message, @DrawableRes int imgId) {
        toastWithImg(message, imgId, false);
    }

    public static void longToastWithImg(@NonNull String message, @DrawableRes int imgId) {
        toastWithImg(message, imgId, true);
    }

    public static void longToastWithImg(@StringRes int message, @DrawableRes int imgId) {
        toastWithImg(message, imgId, true);
    }

    public static void toastWithImg(@NonNull String message, @DrawableRes int imgId, boolean isLongToast) {
        toastWithImg(TOAST_LAYOUT, TOAST_BG, message, imgId, isLongToast);
    }

    public static void toastWithImg(@StringRes int message, @DrawableRes int imgId, boolean isLongToast) {
        toastWithImg(TOAST_LAYOUT, TOAST_BG, message, imgId, isLongToast);
    }

    public static void toastWithImg(@LayoutRes int layout, @DrawableRes int toastBg, @NonNull String message, @DrawableRes int imgId, boolean isLongToast) {
        newInstance(layout, toastBg, message, isLongToast, imgId, true);
    }

    public static void toastWithImg(@LayoutRes int layout, @DrawableRes int toastBg, @StringRes int message, @DrawableRes int imgId, boolean isLongToast) {
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
        if (textView != null) {
            textView.setText(message);
        }
        showToast(view, withImg, imgId);
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
        if (textView != null) {
            textView.setText(message);
        }
        showToast(view, withImg, imgId);
    }

    private static void showToast(LinearLayout view, boolean withImg, int imgId) {
        if (withImg) {
            ImageView img = new ImageView(view.getContext());
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(IC_WIDTH, IC_HEIGHT);
            if (TOAST_ORIENTATION == LinearLayout.HORIZONTAL) {
                ll.setMargins(0, 0, DISTANCE, 0);
            } else {
                ll.setMargins(0, 0, 0, DISTANCE);
            }
            img.setLayoutParams(ll);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setBackgroundResource(imgId);
            view.addView(img, 0);
            view.setOrientation(TOAST_ORIENTATION);
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
