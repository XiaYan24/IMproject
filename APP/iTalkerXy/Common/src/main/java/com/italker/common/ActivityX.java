package com.italker.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;



/**
 * Created by Xia_焱 on  2020/1/7.
 * 邮箱：XiahaotianV@163.com
 */
public abstract class ActivityX extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWidows();

        if (initArgs(getIntent().getExtras())) {

            setContentView(getContentLayoutId());

            initWidget();
            initData();
        } else {
            finish();
        }

    }


    /**
     * 初始化窗口数据
     */
    protected void initWidows() {

    }

    /**
     * 初始化是否成功
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 初始化布局
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    @Override
    public boolean onSupportNavigateUp() {
        //当页面返回时 finish 当前页面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        List<androidx.fragment.app.Fragment> fragment = getSupportFragmentManager().getFragments();
        if (fragment!=null&&fragment.size()>0){
            for (Fragment frg:fragment){
                if (frg instanceof FragmentX){
                    if (((FragmentX) frg).onBackPressed()){
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
