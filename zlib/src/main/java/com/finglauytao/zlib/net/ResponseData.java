package com.finglauytao.zlib.net;

/**
 * Create on 2018/7/25
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class ResponseData {


    //请求回调Key
    private String requestKey = "";

    //响应数据
    private String responseData = "";

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

}
