package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.Live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2016-10-13.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.Live
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class LivePlayActivity extends RxAppBasecompatActivity {


    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lives_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {
        setSupportActionBar(homeToolbar);
        homeToolbar.setTitle("直播详情");
    }

    public static void Launcher(Activity activity) {
        Intent mIntent = new Intent(activity, LivePlayActivity.class);
        activity.startActivity(mIntent);
    }

}
