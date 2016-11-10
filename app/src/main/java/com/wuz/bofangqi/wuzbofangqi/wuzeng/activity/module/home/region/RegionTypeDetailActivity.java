package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter.RegionTypeDetailPageAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionHomeInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016-11-08.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionTypeDetailActivity extends RxAppBasecompatActivity {

    private static final String DATABEAN = "databean";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @Bind(R.id.view_pager)
    NoScrollViewPager viewPager;

    private String toolbarTitle = "toolbartitle";

    private RegionHomeInfo.DataBean dataBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_region_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

//        viewPager.setAdapter();
//        slidingTabs.setViewPager();
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = intent.getParcelableExtra(DATABEAN);
        }

        initSlideTab();
    }

    private void initSlideTab() {

        final List<String> titles=new ArrayList<>();
        int tid = dataBean.tid;
        Observable.from(dataBean.childlist)
                .compose(this.<RegionHomeInfo.DataBean.ChildrenBean>bindToLifecycle())
                .subscribe(new Action1<RegionHomeInfo.DataBean.ChildrenBean>() {
                    @Override
                    public void call(RegionHomeInfo.DataBean.ChildrenBean childrenBean) {
                        titles.add(childrenBean.name);
                    }
                });

//        dataBean.childlist

        titles.add("推荐");
        RegionTypeDetailPageAdapter regionTypeDetailPageAdapter = new RegionTypeDetailPageAdapter(getSupportFragmentManager(), tid,titles,dataBean.childlist);
        viewPager.setAdapter(regionTypeDetailPageAdapter);
        slidingTabs.setViewPager(viewPager);

    }

    @Override
    public void initToolBar() {
        toolbar.setTitle(dataBean.name);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public static void launch(Activity activity, RegionHomeInfo.DataBean dataBean) {
        Intent mIntent = new Intent(activity, RegionTypeDetailActivity.class);
        mIntent.putExtra(DATABEAN, dataBean);
        activity.startActivity(mIntent);
    }
}
