package com.finglauytao.zlib.base;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class ZLifeActivity extends Activity implements LifecycleOwner, ZFullLifecycleObserver {

    private LifecycleRegistry mLifecycleRegistry;
    private ZFullLifecycleObserver mZFullLifecycleObserver;

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.addObserver(this);
    }

    public void setLifeCallBack(ZFullLifecycleObserver observer){
        mZFullLifecycleObserver = observer;
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        if(mZFullLifecycleObserver != null) mZFullLifecycleObserver.onCreate(owner);
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        if(mZFullLifecycleObserver != null) mZFullLifecycleObserver.onStart(owner);
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        if(mZFullLifecycleObserver != null) mZFullLifecycleObserver.onResume(owner);
    }

    @Override
    public void onPause(LifecycleOwner owner) {
        if(mZFullLifecycleObserver != null) mZFullLifecycleObserver.onPause(owner);
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        if(mZFullLifecycleObserver != null) mZFullLifecycleObserver.onStop(owner);
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        if(mZFullLifecycleObserver != null) mZFullLifecycleObserver.onDestroy(owner);
    }

}