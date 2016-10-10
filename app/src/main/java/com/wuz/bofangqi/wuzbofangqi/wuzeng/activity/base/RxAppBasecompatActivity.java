package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base;

import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public abstract class RxAppBasecompatActivity extends RxAppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initViews(savedInstanceState);
        initToolBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public abstract int getLayoutId();
    public abstract void initViews(Bundle savedInstanceState);
    public abstract void initToolBar();


}
