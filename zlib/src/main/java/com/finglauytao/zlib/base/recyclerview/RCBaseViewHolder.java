package com.finglauytao.zlib.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public abstract class RCBaseViewHolder extends RecyclerView.ViewHolder {

    public RCBaseViewHolder(View itemView) {
        super(itemView);
        initItemView(itemView);
    }

    abstract void initItemView(View itemView);

    View getItemView()

    {
        return itemView;
    }

}