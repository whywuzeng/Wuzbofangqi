package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.bangumi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.HeaderViewRecyclerAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter.BangumiRecommedAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonBangumiSerial;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonNewBangumi;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.bangumiBannerAndRecy;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CustomEmptyView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerEntity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

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
    private List<bangumiBannerAndRecy.BannersBean> bannersBeanList;
    private List<bangumiBannerAndRecy.RecommendsBean> recommendsBeanList;
    private List<SeasonNewBangumi.ListBean> listBeanList;
    private List<SeasonBangumiSerial.ListBean> mListSeriallist=new ArrayList<>();
    private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;
    private BangumiRecommedAdapter bangumiRecommedAdapter;
    private View mLayout_Banner;
    private View mlayout_Header_bangumi;
    private View mlayout_head_recommed_bangumi;

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
//        recyle
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyle.setLayoutManager(linearLayoutManager);
        recyle.setNestedScrollingEnabled(true);
        bangumiRecommedAdapter = new BangumiRecommedAdapter(recyle, getActivity());
        mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(bangumiRecommedAdapter);
        bangumiRecommedAdapter.setmOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                LogUtil.i("头晕待会再实现");
            }
        });

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
        RetrofitHelper.getBangumiBannerAndRecy()
                .compose(this.<bangumiBannerAndRecy>bindToLifecycle())
                .flatMap(new Func1<bangumiBannerAndRecy, Observable<SeasonNewBangumi>>() {
                    @Override
                    public Observable<SeasonNewBangumi> call(bangumiBannerAndRecy bangumiBannerAndRecy) {
                        if (bangumiBannerAndRecy != null) {
                            bannersBeanList = bangumiBannerAndRecy.banners;
                            recommendsBeanList = bangumiBannerAndRecy.recommends;
                        }
                        return RetrofitHelper.getSeasonNewBangumi();
                    }
                }).compose(this.<SeasonNewBangumi>bindToLifecycle())
                .flatMap(new Func1<SeasonNewBangumi, Observable<SeasonBangumiSerial>>() {
                    @Override
                    public Observable<SeasonBangumiSerial> call(SeasonNewBangumi seasonNewBangumi) {
                        if (seasonNewBangumi!=null) {
                            listBeanList = seasonNewBangumi.list;
                        }
                        return RetrofitHelper.getSeasonBangumiSerial();
                    }
                })
                .compose(this.<SeasonBangumiSerial>bindToLifecycle())
                .subscribe(new Observer<SeasonBangumiSerial>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(SeasonBangumiSerial seasonBangumiSerial) {
                        if (seasonBangumiSerial.list != null && seasonBangumiSerial.list.size() > 0) {
                            mListSeriallist.addAll(seasonBangumiSerial.list);
                        }
                        finishTask();
                    }
                });

    }

    private void finishTask() {
        swipeRefreshBangumi.setRefreshing(false);
        mIsRefreshing=false;
        //填充recycle View
        recyle.setAdapter(mHeaderViewRecyclerAdapter);
        bangumiRecommedAdapter.SetAllData(recommendsBeanList);
        createHead();
    }



    private void createHead() {
        mLayout_Banner = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner, recyle, false);
        mlayout_Header_bangumi = LayoutInflater.from(getActivity()).inflate(R.layout.layout_head_bangumi, recyle, false);
        mlayout_head_recommed_bangumi = LayoutInflater.from(getActivity()).inflate(R.layout.layout_header_recommed_bangumi, recyle, false);

    //下面处理布局数据

        BannerView bannerView = (BannerView) mLayout_Banner.findViewById(R.id.layout_banner);
        if (bannersBeanList!=null&&bannersBeanList.size()>0)
        {
            List<BannerEntity> bannerEntities=new ArrayList<>();
            for (int i=0;i<bannersBeanList.size();i++)
            {
                BannerEntity bannerEntity = new BannerEntity();
                bannerEntity.title=bannersBeanList.get(i).title;
                bannerEntity.img=bannersBeanList.get(i).img;
                bannerEntities.add(bannerEntity);
            }

            bannerView.delayTime(5).build(bannerEntities);
            mHeaderViewRecyclerAdapter.addHeaderView(bannerView);
        }

        TextView index_tv = (TextView) mlayout_Header_bangumi.findViewById(R.id.tv_bangumi_index);
        index_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView mtv_bangumi_more = (TextView) mlayout_Header_bangumi.findViewById(R.id.tv_bangumi_more);
        mtv_bangumi_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mHeaderViewRecyclerAdapter.addHeaderView(mlayout_Header_bangumi);


            //设置分季新番
        RecyclerView recy_season_list = (RecyclerView) mlayout_head_recommed_bangumi.findViewById(R.id.head_season_list);
        recy_season_list.setHasFixedSize(false);
        recy_season_list.setNestedScrollingEnabled(false);
        recy_season_list.setLayoutManager(new GridLayoutManager(getActivity(),3));



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
