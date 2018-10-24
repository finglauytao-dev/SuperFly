package com.finglauytao.zlib.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public abstract class ZBaseFragment extends Fragment implements LifecycleOwner {

    private int mLayoutId = -1;
    private LifecycleRegistry mLifecycleRegistry = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLayoutId = initLayout();
        View view = inflater.inflate(mLayoutId, container, false);
        initView(view);
        return view;
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    public abstract int initLayout();

    public abstract void initView(View view);
}