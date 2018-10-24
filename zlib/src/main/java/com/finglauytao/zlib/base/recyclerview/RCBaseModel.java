package com.finglauytao.zlib.base.recyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class RCBaseModel<E> {

    private int layoutId = 0;                               //Layout Id
    private List<E> dataList  = new ArrayList<>();            //数据集合
    private int  type  = 0;                              //展示类型

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
