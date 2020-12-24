package com.wdk.component_base.network.jsonconverter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.wdk.component_base.network.error.NullResponseException;
import com.wdk.component_base.network.error.ResponseDataFormatException;
import com.wdk.component_base.network.error.ResponseDataParseException;
import com.wdk.component_base.utils.JsonUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/23 3:13 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/23 3:13 PM
 * @LastCheckBy: wdk
 */
public class NetGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "NetGsonResponseBodyConv";

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    NetGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();

        Log.e(TAG,""+json);

        //服务器返回的json为空
        if (TextUtils.isEmpty(json)) {
            throw new NullResponseException().setErrorMsg("服务器返回的json为空");
        }

        //服务器返回的json格式不对
        if (!json.startsWith("{") && !json.startsWith("[")) {
            throw new ResponseDataFormatException().setErrorMsg("服务器返回的json格式不正确");
        }

        JSONObject jsonObject = JsonUtil.getRootJsonObject(json);

        //服务器返回的json无法解析
        if (jsonObject == null) {
            throw new ResponseDataParseException().setErrorMsg("服务器返回的json无法解析");
        }

        try {
            return adapter.read(gson.newJsonReader(new StringReader(json)));
        } finally {
            value.close();
        }
    }
}
