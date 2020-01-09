package com.italker.common.widget.Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.italker.common.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Xia_焱 on  2020/1/8.
 * 邮箱：XiahaotianV@163.com
 * 适配器
 */
@SuppressWarnings("unused")
public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>> implements View.OnClickListener, View.OnLongClickListener, AdapterCallback<Data> {
    private List<Data> mDataList = new ArrayList<Data>();
    private AdapterListener<Data> mListener;


    protected abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);

    /**
     * 构造函数模块
     */

    public RecyclerAdapter() {
        this(null);
    }

    public RecyclerAdapter(AdapterListener<Data> listener) {
        this(new ArrayList<Data>(), listener);
    }


    public RecyclerAdapter(List<Data> dataList, AdapterListener<Data> listener) {
        this.mDataList = dataList;
        this.mListener = listener;
    }

    /**
     * 复写默认布局返回类型
     * 坐标 / 类型
     */
    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));

    }

    /**
     * 得到布局类型，返回的是XML
     */
    @LayoutRes
    protected abstract int getItemViewType(int position, Data data);


    @NonNull
    @Override
    public ViewHolder<Data> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(viewType, parent, false);
        ViewHolder<Data> holder = onCreateViewHolder(root, viewType);

        //设置View的Tag 为ViewHolder，进行双向绑定
        root.setTag(R.id.tag_recycler_hold, holder);

        //设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        //绑定Callback
        holder.callback = this;

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder<Data> holder, int position) {
        //得到绑定的数据
        Data data = mDataList.get(position);
        //触发 Holder的绑定方法
        holder.onBind(data);

    }


    /**
     * 得到当前集合的数据量
     */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 插入一条数据并更新
     */
    public void add(Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);
    }

    /**
     * 插入一堆数据并更新
     */
    public void add(Data... dataList) {
        if (dataList != null && dataList.length > 0) {
            int startPos = mDataList.size();
            Collections.addAll(mDataList, dataList);
            notifyItemRangeInserted(startPos, dataList.length);
        }
    }


    /**
     * 插入一堆数据并更新
     */
    public void add(Collection<Data> dataList) {
        if (dataList != null && dataList.size() > 0) {
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos, dataList.size());
        }
    }

    /**
     * 删除
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换一个新的集合
     */
    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        mDataList.addAll(dataList);
        notifyDataSetChanged();

    }


    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {

        protected Data mData;
        private AdapterCallback<Data> callback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        /**
         * 用于绑定数据的触发
         */
        void bind(Data data) {
            this.mData = data;
            onBind(data);
        }


        /**
         * 当触发绑定数据回调的时候，必须复写
         */
        protected abstract void onBind(Data data);

        /**
         * Holder 自己对自己对应的Data进行更新操作
         */
        public void updateData(Data data) {
            if (this.callback != null) {
                this.callback.update(data, this);
            }
        }
    }


    @Override
    public void onClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.tag_recycler_hold);
        if (this.mListener != null) {
            //得到ViewHolder 当前对应的适配器中的坐标
            int pos = viewHolder.getAdapterPosition();
            // 回调
            this.mListener.onItemClick(viewHolder, mDataList.get(pos));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.tag_recycler_hold);

        if (this.mListener != null) {
            //得到ViewHolder 当前对应的适配器中的坐标
            int pos = viewHolder.getAdapterPosition();
            // 回调
            this.mListener.onItemLongClick(viewHolder, mDataList.get(pos));
            return true;
        }
        return false;
    }

    /**
     * 设置适配器监听
     */
    public void setListener(AdapterListener<Data> adapterListener) {
        this.mListener = adapterListener;
    }


    public interface AdapterListener<Data> {

        void onItemClick(RecyclerAdapter.ViewHolder holder, Data data);
        void onItemLongClick(RecyclerAdapter.ViewHolder holder, Data data);
    }
}
