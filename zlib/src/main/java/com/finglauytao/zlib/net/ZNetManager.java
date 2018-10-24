package com.finglauytao.zlib.net;

import android.arch.lifecycle.Lifecycle;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create on 2018/7/13
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class ZNetManager implements ZNetInterface {

    private static final int CONNECT_TIME_OUT = 10;      //连接超时时间10s
    private static final int READ_TIME_OUT = 30;         //读取超时时间30s
    private static final int WRITE_TIME_OUT = 30;        //写入超时时间30s

    private Map<String, String> mHeaders;


    private OkHttpClient mOkHttpClient;
    private static ZNetManager sInstance;


    private ZNetManager() {
        //            mOkHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }


    public static ZNetManager getInstance() {
        if (sInstance == null) {
            sInstance = new ZNetManager();
        }
        return sInstance;
    }

    /**
     * add this in your application
     *
     * @param headers
     */
    public void initHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }


    @Override
    public void getEnqueueRequest(String url, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZGetRequestTools.newBuilder(url).build();
        enqueueRequest(request, callBack, lifecycle);
    }

    @Override
    public void getEnqueueRequest(String url, Map<String, String> headers, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZGetRequestTools.newBuilder(url)
                .setHeaders(headers)
                .build();
        enqueueRequest(request, callBack, lifecycle);
    }


    @Override
    public void getEnqueueRequest(String url, Map<String, String> headers, Map<String, String> params, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZGetRequestTools.newBuilder(url)
                .setHeaders(headers)
                .setParams(params)
                .build();
        enqueueRequest(request, callBack, lifecycle);
    }

    @Override
    public void getExecuteRequest(String url, Lifecycle lifecycle, ZNetCallBack callBack) throws IOException {
        Request request = ZGetRequestTools.newBuilder(url)
                .build();
        executeRequest(request, callBack, lifecycle);
    }

    @Override
    public void getExecuteRequest(String url, Map<String, String> headers, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZGetRequestTools.newBuilder(url)
                .setHeaders(headers)
                .build();
        executeRequest(request, callBack, lifecycle);

    }

    @Override
    public void postEnqueueRequest(String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZPostRequestTools.newBuilder(url)
                .setParams(jsonData)
                .build();
        enqueueRequest(request, callBack, lifecycle);
    }

    @Override
    public void postEnqueueRequest(String requestKey, String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZPostRequestTools.newBuilder(url)
                .setParams(jsonData)
                .build();
        enqueueRequest(requestKey, request, callBack, lifecycle);
    }

    @Override
    public void postExecuteRequest(String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZPostRequestTools.newBuilder(url)
                .setParams(jsonData)
                .build();
        executeRequest(request, callBack, lifecycle);
    }

    @Override
    public void postExecuteRequest(String requestKey, String url, String jsonData, Lifecycle lifecycle, ZNetCallBack callBack) {
        Request request = ZPostRequestTools.newBuilder(url)
                .setParams(jsonData)
                .build();
        executeRequest(requestKey, request, callBack, lifecycle);
    }

    private void enqueueRequest(Request request, final ZNetCallBack callBack, final Lifecycle lifecycle) {
        enqueueRequest("", request, callBack, lifecycle);
    }

    private void enqueueRequest(final String requestKey, Request request, final ZNetCallBack callBack, final Lifecycle lifecycle) {
        Call newCall = mOkHttpClient.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (lifecycle != null && lifecycle.getCurrentState() == Lifecycle.State.DESTROYED)
                    return;
                ResponseData responseData = new ResponseData();
                responseData.setRequestKey(requestKey);
                callBack.onFailed(responseData, e.toString(), e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (lifecycle != null && lifecycle.getCurrentState() == Lifecycle.State.DESTROYED)
                    return;
                String tempResponse = "response.body() is null !!!";
                if (response.body() != null) tempResponse = response.body().string();
                ResponseData responseData = new ResponseData();
                responseData.setRequestKey(requestKey);
                responseData.setResponseData(tempResponse);
                callBack.onSuccess(response, responseData, tempResponse);
            }
        });
    }

    private void executeRequest(Request request, final ZNetCallBack callBack, final Lifecycle lifecycle) {
        executeRequest("", request, callBack, lifecycle);
    }

    private void executeRequest(String requestKey, Request request, final ZNetCallBack callBack, final Lifecycle lifecycle) {
        Call newCall = mOkHttpClient.newCall(request);
        try {
            Response response = newCall.execute();
            if (lifecycle != null && lifecycle.getCurrentState() == Lifecycle.State.DESTROYED)
                return;
            String responseDataStr = response.body().string();
            ResponseData responseData = new ResponseData();
            responseData.setRequestKey(requestKey);
            responseData.setResponseData(responseDataStr);
            callBack.onSuccess(response, responseData, responseDataStr);
        } catch (IOException e) {
//            e.printStackTrace();
            if (lifecycle != null && lifecycle.getCurrentState() == Lifecycle.State.DESTROYED)
                return;
            ResponseData responseData = new ResponseData();
            responseData.setRequestKey(requestKey);
            callBack.onFailed(responseData, e.toString(), e);
        }
    }


}
