package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter.LiveRecyclerViewAdpter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.LiveIndex;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CustomEmptyView;

import butterknife.Bind;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private LiveRecyclerViewAdpter mliveRecyclerViewAdpter;


    public static HomeLiveFragment newInstance() {

        Bundle args = new Bundle();
        HomeLiveFragment fragment = new HomeLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
        showProgressBar();
        initRecyclerView();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.homelive_fragment;
    }

    @Override
    protected void onlazyLoad() {


    }

    private void initRecyclerView() {
        mliveRecyclerViewAdpter = new LiveRecyclerViewAdpter(getActivity());
        recyle.setAdapter(mliveRecyclerViewAdpter);
        GridLayoutManager manager = new GridLayoutManager(this.getActivity(), 12);

        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return mliveRecyclerViewAdpter.getSpanSize(position);
            }
        });
        recyle.setLayoutManager(manager);
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
        RetrofitHelper.getBiliLiveServiceApi()
                .getLiveIndex()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .compose(this.bindToLifecycle())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.e("tag", "onCompleted");
                        homeliveSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag", "onError");
                        homeliveSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e("tag", "onCompleted");
                        finishTask(o);
                    }
                });
    }

    private void finishTask(Object o) {
        LiveIndex liveIndex = (LiveIndex) o;
        mliveRecyclerViewAdpter.setmLiveIndex(liveIndex);
    }


}
