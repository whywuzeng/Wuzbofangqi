package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.bangumi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter.SeasonNewBangumiAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonNewBangumi;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleProgressView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-28.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.bangumi
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SeasonBangumiActivity extends RxAppBasecompatActivity {

    private static final String KEY_LISTBEANLIST = "key_listbeanlist";
    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.circle_progress)
    CircleProgressView circleProgress;

    private ArrayList<SeasonNewBangumi.ListBean> mListBeanList;

    @Override
    public int getLayoutId() {

        return R.layout.activity_seasonbangumi;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent!=null)
        {
            mListBeanList= intent.getParcelableArrayListExtra(KEY_LISTBEANLIST);
        }
        recycle.setLayoutManager(new GridLayoutManager(SeasonBangumiActivity.this,3));
        SeasonNewBangumiAdapter seasonNewBangumiAdapter = new SeasonNewBangumiAdapter(recycle, this);
        seasonNewBangumiAdapter.setIsAllShow(true);
        recycle.setAdapter(seasonNewBangumiAdapter);
        seasonNewBangumiAdapter.SetAllData(mListBeanList);
    }

    public static void laucher(Activity activity,  ArrayList<SeasonNewBangumi.ListBean> listBeanList)
    {
        Intent mIntent=new Intent(activity,SeasonBangumiActivity.class);
        mIntent.putParcelableArrayListExtra(KEY_LISTBEANLIST,listBeanList);
        activity.startActivity(mIntent);
    }

    @Override
    public void initToolBar() {

        homeToolbar.setTitle("连载更新...");
        setSupportActionBar(homeToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null)
        {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
