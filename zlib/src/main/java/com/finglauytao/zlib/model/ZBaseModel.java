package com.finglauytao.zlib.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.finglauytao.zlib.model.ZBaseLiveData;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class ZBaseModel extends AndroidViewModel {

    private ZBaseLiveData zBaseLiveData;

    public ZBaseModel(@NonNull Application application) {
        super(application);
    }

    public ZBaseLiveData getzBaseLiveData() {
        return zBaseLiveData;
    }

    public void setzBaseLiveData(ZBaseLiveData zBaseLiveData) {
        this.zBaseLiveData = zBaseLiveData;
    }


}
