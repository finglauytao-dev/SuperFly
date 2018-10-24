package com.finglauytao.zlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public abstract class ZBaseActivity extends ZLifeActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = iniLayout();
        setContentView(layoutId);
        initView();
    }

    public abstract int iniLayout();

    public abstract void initView();

}