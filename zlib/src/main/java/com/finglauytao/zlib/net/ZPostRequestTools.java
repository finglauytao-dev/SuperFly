package com.finglauytao.zlib.net;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Create on 2018/7/14
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class ZPostRequestTools {

    private String mUrl;
    private String mParams;
    private Map<String, String> mHeaders;


    public static ZPostRequestTools newBuilder(@NotNull String url) {
        return new ZPostRequestTools(url);
    }


    private ZPostRequestTools(String url) {
        mUrl = url;
    }

    public ZPostRequestTools setParams(@NotNull String jsonParams) {
        mParams = jsonParams;
        return this;
    }

    public ZPostRequestTools setHeaders(Map<String, String> headers) {
        mHeaders = headers;
        return this;
    }

    public Request build() {
        Request.Builder builder = new Request.Builder();
        if (mHeaders != null) {
            mHeaders = ZNetManager.getInstance().getHeaders();
        }
        Headers headers = ZNetUtils.addRequestHeader(mHeaders);
        if (headers != null) builder.headers(headers);
        RequestBody body = FormBody.create(ZNetUtils.JSON, mParams);
        return new Request.Builder()
                .url(mUrl)
                .post(body)
                .build();

    }

}
