package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonBangumiSerial;

/**
 * Created by Administrator on 2016-10-31.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class BangumiDetailActivity extends RxAppBasecompatActivity {


    private static final String BANGUMIDETAILE = "bangumidetaile";

    private SeasonBangumiSerial.ListBean mListBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bangumi_detail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        if (getIntent()!=null)
        {
            Intent intent = getIntent();
            mListBean= intent.getParcelableExtra(BANGUMIDETAILE);
        }

        getBangumiDetails();
    }

    private void getBangumiDetails() {

        Glide.with(this)
                .load(mListBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(R.drawable.bili_default_image_tv)
                .into()
    }

    @Override
    public void initToolBar() {

    }

    public static void launcher(Activity activity,SeasonBangumiSerial.ListBean mListBean)
    {
        Intent mIntent=new Intent(activity,BangumiDetailActivity.class);
        mIntent.putExtra(BANGUMIDETAILE,mListBean);
        activity.startActivity(mIntent);
    }
}
