package com.finglauytao.zlib.net;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;

/**
 * Create on 2018/7/14
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class ZNetUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private ZNetUtils() {
    }

    public static Headers addRequestHeader(Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return null;
        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        return headerBuilder.build();
    }

    public static String makeGetParams(String url, Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) return url;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('?');
        for (String name : paramsMap.keySet()) {
            stringBuffer.append(name).append('=').append(paramsMap.get(name)).append('&');
        }
        return url + stringBuffer.substring(0, stringBuffer.length() - 1);
    }


}
