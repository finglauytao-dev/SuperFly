package com.finglauytao.zlib.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public abstract class ZBaseFragmentActivity extends FragmentActivity implements LifecycleOwner {

    private int mLayoutId = -1;
    private LifecycleRegistry mLifecycleRegistry = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLayoutId = initLayout();
        setContentView(mLayoutId);
        initView();
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    public abstract int initLayout();

    public abstract void initView();
}