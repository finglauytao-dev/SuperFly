package com.finglauytao.zlib.net;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;

/**
 * Create on 2018/7/14
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class ZGetRequestTools {

    private String mUrl;
    private Map<String, String> mParams;
    private Map<String, String> mHeaders;


    static ZGetRequestTools newBuilder(@NotNull String url) {
        return new ZGetRequestTools(url);
    }


    private ZGetRequestTools(String url) {
        mUrl = url;
    }

    ZGetRequestTools setParams(Map<String, String> params) {
        mParams = params;
        return this;
    }

    ZGetRequestTools setHeaders(Map<String, String> headers) {
        mHeaders = headers;
        return this;
    }

    Request build() {
        Request.Builder builder = new Request.Builder();
        if (mHeaders != null) {
            mHeaders = ZNetManager.getInstance().getHeaders();
        }
        Headers headers = ZNetUtils.addRequestHeader(mHeaders);
        if (headers != null) builder.headers(headers);
        if (mParams != null && !mParams.isEmpty()) {
            String url = ZNetUtils.makeGetParams(mUrl, mParams);
            return builder.get().url(url).build();
        } else {
            return builder.get().url(mUrl).build();
        }
    }

}
