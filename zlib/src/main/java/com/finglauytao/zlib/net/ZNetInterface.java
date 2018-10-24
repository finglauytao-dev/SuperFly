package com.finglauytao.zlib.net;

import android.arch.lifecycle.Lifecycle;

import java.io.IOException;
import java.util.Map;

/**
 * Create on 2018/7/14
 *
 * @author finglauytao
 * @version 1.0.0
 **/
interface ZNetInterface {

    /**
     * GET-异步请求
     *
     * @param url 请求地址
     */
    void getEnqueueRequest(String url, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * GET-异步请求
     *
     * @param url     请求地址
     * @param headers 自定义请求头
     */
    void getEnqueueRequest(String url, Map<String, String> headers, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * GET-异步请求
     *
     * @param url     请求地址
     * @param headers 自定义请求头
     * @param params  请求参数
     */
    void getEnqueueRequest(String url, Map<String, String> headers, Map<String, String> params, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * GET-同步请求
     *
     * @param url 请求地址
     */
    void getExecuteRequest(String url, Lifecycle lifecycle, ZNetCallBack callBack) throws IOException;

    /**
     * GET-同步请求
     *
     * @param url     请求地址
     * @param headers 自定义请求头
     */
    void getExecuteRequest(String url, Map<String, String> headers, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * POST-异步请求
     *
     * @param url      请求地址
     * @param jsonData 请求参数（JSON-DATA）
     */
    void postEnqueueRequest(String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * POST-异步请求
     *
     * @param requestKey 请求观察Key
     * @param url        请求地址
     * @param jsonData   请求参数（JSON-DATA）
     */
    void postEnqueueRequest(String requestKey, String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * POST-同步请求
     *
     * @param url      请求地址
     * @param jsonData 请求参数（JSON-DATA）
     */
    void postExecuteRequest(String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack);

    /**
     * POST-同步请求
     *
     * @param requestKey 请求观察Key
     * @param url        请求地址
     * @param jsonData   请求参数（JSON-DATA）
     */
    void postExecuteRequest(String requestKey, String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack);


}
