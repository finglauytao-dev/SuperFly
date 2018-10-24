package com.finglauytao.zlib.base.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public abstract class RCBaseAdapter<E extends RCBaseModel<E>, T extends RCBaseViewHolder> extends RecyclerView.Adapter<T> {

    public Context mContext = null;
    private E mOnlyModel = null;
    private List<E> mDataList = new ArrayList<>();
    private Map<Integer, Integer> mLayoutMap = new HashMap<>();

    public RCBaseAdapter(Context context, E onlyData) {
        mContext = context;
        mOnlyModel = onlyData;
    }

    public RCBaseAdapter(Context context, List<E> dataList) {
        mContext = context;
        mDataList = dataList;
        loadMultiTypeLayout(dataList);
    }

    abstract T makeViewHolder(View view);

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (mOnlyModel != null) {
            view = makeInflateView(parent, mOnlyModel.getLayoutId());
        } else {
            view = makeInflateView(parent, mLayoutMap.get(viewType));
        }
        return makeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mOnlyModel != null) return mOnlyModel.getDataList().size();
        return mLayoutMap.size();
    }

    private void loadMultiTypeLayout(List<E> dataList) {
        if (dataList == null || dataList.size() == 0) return;
        for (E e : dataList) {
            mLayoutMap.put(e.getType(), e.getLayoutId());
        }
    }

    private View makeInflateView(ViewGroup parent, @LayoutRes int layout) {
        return LayoutInflater.from(mContext).inflate(layout, parent, false);
    }

}