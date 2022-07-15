package cn.jane.xjutils.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jane.xjutils.string.StringUtils;

/**
 * @author 创建人： Jane
 * @createTime 创建时间： 2022/7/13
 * @describe 描述：desc
 */
public class JsonUtils {

    public static boolean isJson(String jsonStr) {
        if (StringUtils.isNull(jsonStr)) {
            return false;
        }
        if (!(jsonStr.startsWith("{") && jsonStr.endsWith("}")) &&
                !(jsonStr.startsWith("[") && jsonStr.endsWith("]"))) {
            return false;
        }
        try {
            if (jsonStr.startsWith("{") && jsonStr.endsWith("}")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
            } else {
                JSONArray jsonArray = new JSONArray(jsonStr);
            }
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

}
