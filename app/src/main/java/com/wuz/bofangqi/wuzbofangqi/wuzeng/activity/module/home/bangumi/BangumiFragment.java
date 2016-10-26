package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.bangumi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CustomEmptyView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-26.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.bangumi
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class BangumiFragment extends RxLazeFragment {


    @Bind(R.id.recyle)
    RecyclerView recyle;
    @Bind(R.id.empty_layout)
    CustomEmptyView emptyLayout;
    @Bind(R.id.swipe_refresh_bangumi)
    SwipeRefreshLayout swipeRefreshBangumi;
    private boolean mIsRefreshing=false;

    public static BangumiFragment newInstance() {
        Bundle args = new Bundle();
        BangumiFragment fragment = new BangumiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
        showProgressBar();
        initRecyclerView();
    }

    private void initRecyclerView() {

    }

    private void showProgressBar() {
        swipeRefreshBangumi.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshBangumi.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshBangumi.setRefreshing(true);
                mIsRefreshing = true;
                getBangumiRecommends();
            }
        });

        swipeRefreshBangumi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //清理数据

                //再一次访问
            }
        });

    }

    private void getBangumiRecommends() {
//        RetrofitHelper.get
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home_bangumi;
    }

    @Override
    protected void onlazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
