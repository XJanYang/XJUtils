package cn.jane.xjutils.android.textview;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import cn.jane.xjutils.android.resource.ResourceUtils;
import cn.jane.xjutils.empty.NullUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class TextViewUtils {

    /**
     * 为TextView设置文字
     *
     * @param textView textview
     * @param value    value
     */
    public static void setText(@NonNull TextView textView, @NonNull String value) {
        if (NullUtils.isNotNull(value)) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
        textView.setVisibility(View.VISIBLE);
    }

    /**
     * 为TextView设置文字
     *
     * @param textView textview
     * @param value    value
     */
    public static void setText(@NonNull TextView textView, @StringRes int value) {
        if (NullUtils.isNotNull(ResourceUtils.getString(value))) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
        textView.setVisibility(View.VISIBLE);
    }

    /**
     * 为TextView设置文字
     *
     * @param textView   textview
     * @param value      value
     * @param isNullGone value为空时隐藏textview（true：隐藏；false：不隐藏）
     */
    public static void setText(@NonNull TextView textView, @NonNull String value, boolean isNullGone) {
        if (NullUtils.isNotNull(value)) {
            textView.setText(value);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setText("");
            if (isNullGone) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 为TextView设置文字
     *
     * @param textView   textview
     * @param value      value
     * @param isNullGone value为空时隐藏textview（true：隐藏；false：不隐藏）
     */
    public static void setText(@NonNull TextView textView, @StringRes int value, boolean isNullGone) {
        if (NullUtils.isNotNull(ResourceUtils.getString(value))) {
            textView.setText(value);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setText("");
            if (isNullGone) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 设置字号
     *
     * @param textView textview
     * @param textSize 字号
     */
    public static void setTextSize(@NonNull TextView textView, @DimenRes int textSize) {
        setTextSize(textView, textSize, TypedValue.COMPLEX_UNIT_PX);
    }

    /**
     * 设置字号
     *
     * @param textView textview
     * @param textSize 字号
     * @param unit     单位
     */
    public static void setTextSize(@NonNull TextView textView, @DimenRes int textSize, int unit) {
        textView.setTextSize(unit, textView.getContext().getResources().getDimensionPixelSize(textSize));
    }

    /**
     * 设置字体颜色
     *
     * @param textView  textview
     * @param textColor 颜色
     */
    public static void setTextColor(@NonNull TextView textView, @ColorRes int textColor) {
        textView.setTextColor(textView.getContext().getResources().getColor(textColor));
    }

    /**
     * 为TextView设置文字、字号
     *
     * @param textView textView
     * @param value    strValue
     * @param textSize textSize
     */
    public static void setTextWithSize(@NonNull TextView textView, @NonNull String value, @DimenRes int textSize) {
        setText(textView, value);
        setTextSize(textView, textSize);
    }

    /**
     * 为TextView设置文字、字号
     *
     * @param textView textView
     * @param value    strValue
     * @param textSize textSize
     */
    public static void setTextWithSize(@NonNull TextView textView, @StringRes int value, @DimenRes int textSize) {
        setText(textView, value);
        setTextSize(textView, textSize);
    }

    /**
     * 为TextView设置文字、字体颜色
     *
     * @param textView  textView
     * @param value     strValue
     * @param textColor textColor
     */
    public static void setTextWithColor(@NonNull TextView textView, @NonNull String value, @ColorRes int textColor) {
        setText(textView, value);
        setTextColor(textView, textColor);
    }

    /**
     * 为TextView设置文字、字体颜色
     *
     * @param textView  textView
     * @param value     strValue
     * @param textColor textColor
     */
    public static void setTextWithColor(@NonNull TextView textView, @StringRes int value, @ColorRes int textColor) {
        setText(textView, value);
        setTextColor(textView, textColor);
    }

    /**
     * 为TextView设置文字、字号、字体颜色
     *
     * @param textView  textView
     * @param value     strValue
     * @param textSize  textSize
     * @param textColor textColor
     */
    public static void setTextWithSizeAndColor(@NonNull TextView textView, @NonNull String value, @DimenRes int textSize, @ColorRes int textColor) {
        setText(textView, value);
        setTextSize(textView, textSize);
        setTextColor(textView, textColor);
    }

    /**
     * 为TextView设置文字、字号、字体颜色
     *
     * @param textView  textView
     * @param value     strValue
     * @param textSize  textSize
     * @param textColor textColor
     */
    public static void setTextWithSizeAndColor(@NonNull TextView textView, @StringRes int value, @DimenRes int textSize, @ColorRes int textColor) {
        setText(textView, value);
        setTextSize(textView, textSize);
        setTextColor(textView, textColor);
    }

    /**
     * TextView展示格式化HTML富文本
     *
     * @param textView textview
     * @param htmlStr html富文本
     */
    public static void setHtmlFormat(@NonNull TextView textView, @NonNull String htmlStr) {
        Spanned spanned;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_COMPACT);
        } else {
            spanned = Html.fromHtml(htmlStr);
        }
        textView.setText(spanned);
    }


}
