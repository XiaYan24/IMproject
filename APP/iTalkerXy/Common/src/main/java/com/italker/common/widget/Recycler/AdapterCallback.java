package com.italker.common.widget.Recycler;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Xia_焱 on  2020/1/8.
 * 邮箱：XiahaotianV@163.com
 */
public interface AdapterCallback<Data> {

    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
