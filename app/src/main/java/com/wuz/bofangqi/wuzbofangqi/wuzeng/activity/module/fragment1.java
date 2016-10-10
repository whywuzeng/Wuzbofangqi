package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.wuz.bofangqi.wuzbofangqi.R;

/**
 * Created by Administrator on 2016-10-10.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class fragment1  extends RxFragment {

    private String number;
    private TextView tv_middle_context;
    private View parentView;

    public static final String KEY_NUMBER="KEY_NUMBER";

    private FragmentActivity activity;

    public static fragment1 newInstance(String number) {
        Bundle args = new Bundle();
        args.putString(KEY_NUMBER,number);
        fragment1 fragment = new fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.number=arguments.getString(KEY_NUMBER,null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home_1, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        tv_middle_context=(TextView)inflate.findViewById(R.id.tv_middle_context);
        tv_middle_context.setText(number);
    }

}
