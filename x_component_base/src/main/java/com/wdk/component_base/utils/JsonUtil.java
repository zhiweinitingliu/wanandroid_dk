package com.wdk.component_base.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/14 3:40 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/14 3:40 PM
 * @LastCheckBy: wdk
 */
public class JsonUtil {

    /**
     * 将返回的额json转换为JsonObject
     *
     * @param json json
     * @return JsonObject
     */
    public static JSONObject getRootJsonObject(String json) {

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将返回的额json转换为JsonObject
     *
     * @param json json
     * @return JsonObject
     */
    public static JSONArray getRootJsonArray(String json) {

        if (TextUtils.isEmpty(json)) {
            return new JSONArray();
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }

    /**
     * 使用安卓自带的json解析工具得到jsonObject
     * 1、处理解析到为空的情况
     * 2、处理对象没有值的情况{}
     * 3、将以上结果处理为null，有值返回jsonObject
     */
    public static JSONObject getOwnJsonObject(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return null;
        }

        if (jsonObject.has(key)) {
            Object obj = jsonObject.opt(key);
            if (obj instanceof JSONObject) {//如果是JsonObject 类型
                JSONObject jsonObj = (JSONObject) obj;
                if (jsonObj.length() > 0) {//jsonObj有值
                    return jsonObj;
                }
            }
        }
        return null;
    }

    /**
     * 使用安卓自带的json解析工具得到jsonArray
     * 1、处理解析到为空的情况
     * 2、有值返回jsonArray
     */
    public static JSONArray getOwnJsonArray(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return null;
        }

        if (jsonObject.has(key)) {
            Object obj = jsonObject.opt(key);
            if (obj != null) {
                if (obj instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) obj;
                    return jsonArray;
                }
            }
        }
        return null;
    }


    /**
     * 获得字符串的 json字段
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static String getJsonStrValue(JSONObject jsonObject, String key) {
        return getJsonStrValue(jsonObject, key, "");
    }

    public static String getJsonStrValue(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null) {
            return defaultValue;
        }

        if (jsonObject.isNull(key)) {
            return defaultValue;
        } else {
            return jsonObject.optString(key, defaultValue);
        }
    }

    /**
     * 获得int类型的字段值
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static long getJsonLongValue(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return -1;
        }

        if (jsonObject.isNull(key))
            return -1;
        else
            return jsonObject.optLong(key, -1);
    }

    /**
     * 获得int类型的字段值
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static int getJsonIntValue(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return -1;
        }

        if (jsonObject.isNull(key))
            return -1;
        else
            return jsonObject.optInt(key, 0);
    }

    public static int getJsonIntValue(JSONObject jsonObject, String key, int defaultInt) {
        if (jsonObject == null) {
            return defaultInt;
        }

        if (jsonObject.isNull(key))
            return defaultInt;
        else
            return jsonObject.optInt(key, defaultInt);
    }

    /**
     * 获得int类型的字段值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getStringValue(JSONObject json, String key) {
        return getJsonStrValue(json, key);
    }

    /**
     * 获得int类型的字段值
     *
     * @param json
     * @param key
     * @return
     */
    public static int getIntValue(JSONObject json, String key) {
        return getJsonIntValue(json, key);
    }

    /**
     * 获得int类型的字段值
     *
     * @param json
     * @param key
     * @return
     */
    public static long getLongValue(JSONObject json, String key) {
        if (!json.has(key))
            return -1;
        else
            try {
                return json.getLong(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return -1;
    }

    public static boolean getJsonBooleanValue(JSONObject jsonObject, String key, boolean defaultBoole) {
        if (jsonObject == null) {
            return defaultBoole;
        }

        if (jsonObject.isNull(key))
            return defaultBoole;
        else
            return jsonObject.optBoolean(key, defaultBoole);

    }


    public static String beanToJson(Object object) {
        return JSON.toJSONString(object);
    }
}
