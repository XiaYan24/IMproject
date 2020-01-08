package com.italker.yx.Base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xia_焱 on  2020/1/7.
 * 邮箱：XiahaotianV@163.com
 */
public abstract class BaseFragment extends androidx.fragment.app.Fragment {

    protected View mRoot;
    protected Unbinder mRootUnBinder;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //初始化参数
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot == null) {
            int layId = getContentLayoutId();
            //初始化当前布局，但是不在创建时就添加到container
            View root = inflater.inflate(layId, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                //把当前root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View 创建完成 初始化数据
        initData();
    }


    /**
     * 初始化是否成功
     */
    protected void initArgs(Bundle bundle) {

    }

    /**
     * 初始化布局
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化窗口数据
     */
    protected void initWidows() {

    }

    /**
     * 初始化控件
     *
     * @param root
     */
    protected void initWidget(View root) {
        mRootUnBinder =  ButterKnife.bind(this,root);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 返回按键时调用，返回true代表已经处理返回逻辑，不用finish
     * 如果返回false 代表没有处理
     */
    public boolean onBackPressed() {
        return false;
    }
}
