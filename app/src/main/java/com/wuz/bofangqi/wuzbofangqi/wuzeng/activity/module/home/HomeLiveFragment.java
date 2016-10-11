package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CustomEmptyView;

import butterknife.Bind;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class HomeLiveFragment extends RxLazeFragment {


    @Bind(R.id.recyle)
    RecyclerView recyle;
    @Bind(R.id.empty_layout)
    CustomEmptyView emptyLayout;
    @Bind(R.id.homelive_swipe_refresh_layout)
    SwipeRefreshLayout homeliveSwipeRefreshLayout;


    public static HomeLiveFragment newInstance() {

        Bundle args = new Bundle();
        HomeLiveFragment fragment = new HomeLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.homelive_fragment;
    }

    @Override
    protected void onlazyLoad() {

        showProgressBar();
        initRecyclerView();
    }

    private void initRecyclerView() {

    }

    private void showProgressBar() {
        homeliveSwipeRefreshLayout.setColorSchemeResources(R.color.theme_color_primary);

        homeliveSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                homeliveSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        homeliveSwipeRefreshLayout.setRefreshing(true);
                        getBiliBiliLive();
                    }
                });
            }
        });


    }

    public void getBiliBiliLive()
    {

    }

}
