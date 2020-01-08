package com.italker.yx;

import android.os.Bundle;
import android.widget.TextView;

import com.italker.yx.Base.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity {


    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        tv.setText("========");
    }

    @Override
    protected void initData() {

    }


}
