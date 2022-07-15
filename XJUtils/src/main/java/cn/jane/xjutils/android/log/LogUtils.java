package cn.jane.xjutils.android.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jane.xjutils.BuildConfig;
import cn.jane.xjutils.json.JsonUtils;
import cn.jane.xjutils.string.StringUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/12
 * @describe 描述：desc
 */
public class LogUtils {

    private static boolean showLog = BuildConfig.DEBUG;
    private static String tag = "";

    public static final String LOG_START = "╔══════START═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    public static final String LOG_CENTER = "║ ";
    public static final String LONG_END = "╚══════END═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";

    public LogUtils() {
        throw new UnsupportedOperationException(getClass().getName() + ":I can't be instantiated ...");
    }

    public static void v(String string) {
        v(string, "");
    }

    public static void v(String string, String head) {
        print(LogType.LOG_V, string, head);
    }

    public static void d(String string) {
        d(string, "");
    }

    public static void d(String string, String head) {
        print(LogType.LOG_D, string, head);
    }

    public static void i(String string) {
        i(string, "");
    }

    public static void i(String string, String head) {
        print(LogType.LOG_I, string, head);
    }

    public static void w(String string) {
        w(string, "");
    }

    public static void w(String string, String head) {
        print(LogType.LOG_W, string, head);
    }

    public static void e(String string) {
        e(string, "");
    }

    public static void e(String string, String head) {
        print(LogType.LOG_E, string, head);
    }

    /**
     * 打印
     * json判断逻辑
     *
     * @param logType
     * @param string
     * @param head
     */
    public static void print(LogType logType, String string, String head) {
        if (showLog) {
            if (JsonUtils.isJson(string)) {
                try {
                    String logResult = "";
                    if (string.startsWith("{")) {
                        JSONObject jsonObject = new JSONObject(string);
                        logResult = " " + "\n" + LOG_START +
                                (StringUtils.isNull(head) ? "" : ("\n" + head)) +
                                "\n" + jsonObject.toString(1)
                                + "\n" + LONG_END;
                    } else {
                        JSONArray jsonArray = new JSONArray(string);
                        logResult = " " + "\n" + LOG_START +
                                (StringUtils.isNull(head) ? "" : ("\n" + head)) +
                                "\n" + jsonArray.toString(1)
                                + "\n" + LONG_END;
                    }
                    log(logType, logResult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                log(logType, LOG_START);
                if (StringUtils.isNotNull(head)) {
                    log(logType, head);
                }
                log(logType, LOG_CENTER + string);
                log(logType, LONG_END);
            }
        }
    }

    /**
     * log输出
     *
     * @param logType
     * @param string
     */
    private static void log(LogType logType, String string) {
        switch (logType) {
            case LOG_V:
                Log.v(tag, string);
                break;
            case LOG_D:
                Log.d(tag, string);
                break;
            case LOG_I:
                Log.i(tag, string);
                break;
            case LOG_W:
                Log.w(tag, string);
                break;
            case LOG_E:
                Log.e(tag, string);
                break;
            default:
                break;
        }
    }

    public enum LogType {
        LOG_V,
        LOG_D,
        LOG_I,
        LOG_W,
        LOG_E;
    }
}
