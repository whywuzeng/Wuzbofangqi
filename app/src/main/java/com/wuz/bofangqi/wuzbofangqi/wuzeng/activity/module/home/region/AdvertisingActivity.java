package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.App.ConstantUtils;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter.section.RegionRecommendBannerSection;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionRecommendInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerEntity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016-11-08.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class AdvertisingActivity extends RxAppBasecompatActivity {

    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_advertising)
    SwipeRefreshLayout swipeRefreshAdvertising;
    private boolean mIsRefreshing;
    private List<RegionRecommendInfo.DataBean.BannerBean.TopBean> topBeans=new ArrayList<>();
    private List<RegionRecommendInfo.DataBean.RecommendBean> recommendBeans=new ArrayList<>();
    private List<RegionRecommendInfo.DataBean.RecommendBean> mBeanListnewX=new ArrayList<>();
    private List<RegionRecommendInfo.DataBean.RecommendBean> mBeanListdynamic=new ArrayList<>();
    private  List<BannerEntity> regionBannerInfos=new ArrayList<>();
    private SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_advertising;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        initRrefreshView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedRecyclerViewAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:

                        return 2;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mSectionedRecyclerViewAdapter);

        //recycleview 能不能滑动  如果正在刷新的 时候 是不能滑动的
        // 子类 能不能滑动  在adpter里有 看能不能滑动 控制
        setRecyclerViewScrol();


    }

    private void setRecyclerViewScrol() {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mIsRefreshing;
            }
        });
    }

    private void initRrefreshView() {
        //那个圈圈的颜色？还是？整个的颜色？
        swipeRefreshAdvertising.setColorSchemeResources(R.color.yellow_30);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {

                swipeRefreshAdvertising.setRefreshing(true);
                mIsRefreshing = true;
                loadData();
            }
        });

        swipeRefreshAdvertising.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearData();
                loadData();
            }
        });
    }

    private void clearData() {

    }

    private void loadData() {
        RetrofitHelper.getRegionRecommend(Integer.valueOf(ConstantUtils.ADVERTISING_RID))
                .compose(this.<RegionRecommendInfo>bindToLifecycle())
                .map(new Func1<RegionRecommendInfo, RegionRecommendInfo.DataBean>() {
                    @Override
                    public RegionRecommendInfo.DataBean call(RegionRecommendInfo regionRecommendInfo) {
                        return   regionRecommendInfo.data;
                    }
                }).subscribe(new Observer<RegionRecommendInfo.DataBean>() {
            @Override
            public void onCompleted() {
                LogUtil.i("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("onError" + e.getLocalizedMessage());
                swipeRefreshAdvertising.setRefreshing(false);

            }

            @Override
            public void onNext(RegionRecommendInfo.DataBean dataBean) {

                topBeans.addAll(dataBean.banner.top);
                recommendBeans.addAll(dataBean.recommend);
                mBeanListnewX.addAll(dataBean.newX);
                mBeanListdynamic.addAll(dataBean.dynamic);
                finishTask();

            }
        });
    }

    private void finishTask() {
        //给Banner的值重新赋值
        coverBanner();

        //新型的adpter 适配器
        mSectionedRecyclerViewAdapter.addSection(new RegionRecommendBannerSection(regionBannerInfos));


    }

    private void coverBanner() {
        Observable.from(topBeans)
                .compose(this.<RegionRecommendInfo.DataBean.BannerBean.TopBean>bindToLifecycle())
                .forEach(new Action1<RegionRecommendInfo.DataBean.BannerBean.TopBean>() {
                    @Override
                    public void call(RegionRecommendInfo.DataBean.BannerBean.TopBean topBean) {
                        regionBannerInfos.add(new BannerEntity(topBean.title,topBean.image,topBean.uri));
                    }
                });
    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
