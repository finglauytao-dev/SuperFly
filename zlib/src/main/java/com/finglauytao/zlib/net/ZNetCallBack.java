package com.finglauytao.zlib.net;


import okhttp3.Response;

/**
 * Create on 2018/7/14
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public interface ZNetCallBack {

    void onSuccess(Response response, ResponseData responseData, String data);

    void onFailed(ResponseData responseData, String errMsg, Exception e);

}
