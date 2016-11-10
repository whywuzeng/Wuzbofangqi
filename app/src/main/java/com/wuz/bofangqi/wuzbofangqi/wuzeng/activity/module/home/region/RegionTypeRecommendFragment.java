package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils.ToastUtil;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter.RegionRecommendItemSection;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter.RegionRecommendTypeSection;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter.RegionTypeRecommendSection;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionHomeInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionTypeRecommendinfo;
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

/**
 * Created by Administrator on 2016-11-10.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionTypeRecommendFragment extends RxLazeFragment {

    private static final String ARG_RID = "arg_rid";
    private static final String ARG_CHILDRENLIST = "arg_childrenlist";
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;
    private String mArg_rid;
    private List<RegionTypeRecommendinfo.DataBean.RecommendBean> mRecommendBeanList;
    private List<RegionTypeRecommendinfo.DataBean.RecommendBean> mNewXBeanList;
    private List<RegionTypeRecommendinfo.DataBean.RecommendBean> mDynamicList;
    private List<RegionTypeRecommendinfo.DataBean.BannerBean.TopBean> mTopBeanList;
    private boolean mIsRefresh;
    private List<BannerEntity> mBannerEntities=new ArrayList<>();

   private List<RegionHomeInfo.DataBean.ChildrenBean> mChildrenBeanList;

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments!=null)
        {
            mArg_rid = arguments.getString(ARG_RID);
            mChildrenBeanList=  arguments.getParcelableArrayList(ARG_CHILDRENLIST);
        }
        initRecyclerView();
        loadData();
    }

    private void loadData() {
        swipeRefresh.setRefreshing(true);
        //访问
        RetrofitHelper.getRegionTypeRecommendInfo(Integer.valueOf(mArg_rid))
                .compose(this.<RegionTypeRecommendinfo>bindToLifecycle())
                .subscribe(new Observer<RegionTypeRecommendinfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        swipeRefresh.setRefreshing(false);
                        ToastUtil.ShortToast("onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(RegionTypeRecommendinfo regionTypeRecommendinfo) {
                        mTopBeanList = regionTypeRecommendinfo.data.banner.top;
                        mRecommendBeanList = regionTypeRecommendinfo.data.recommend;
                        mNewXBeanList = regionTypeRecommendinfo.data.newX;
                        mDynamicList = regionTypeRecommendinfo.data.dynamic;

                        finishTask();
                    }
                });
    }

    /**
     * mRecommendBeanList;
      mNewXBeanList;
     mDynamicList;
     */


    private void finishTask() {
        swipeRefresh.setRefreshing(false);
        mIsRefresh=false;
        setmBannerEntities();

     mSectionedRecyclerViewAdapter.addSection(new RegionTypeRecommendSection(mBannerEntities));
        mSectionedRecyclerViewAdapter.addSection(new RegionRecommendTypeSection(getActivity(),mChildrenBeanList));
    /*    mRecommendBeanList;
        private List<RegionTypeRecommendinfo.DataBean.RecommendBean> mNewXBeanList;
        private List<RegionTypeRecommendinfo.DataBean.RecommendBean> mDynamicList;*/

         List<RegionTypeRecommendinfo.DataBean.RecommendBean> alldata=new ArrayList<>();

//        Observable.from()
        alldata.addAll(mRecommendBeanList);
        alldata.addAll(mNewXBeanList);
        alldata.addAll(mDynamicList);
        mSectionedRecyclerViewAdapter.addSection(new RegionRecommendItemSection(alldata,getActivity()));
        mSectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void setmBannerEntities() {
        Observable.from(mTopBeanList)
                .subscribe(new Action1<RegionTypeRecommendinfo.DataBean.BannerBean.TopBean>() {
                    @Override
                    public void call(RegionTypeRecommendinfo.DataBean.BannerBean.TopBean topBean) {
                        mBannerEntities.add(new BannerEntity(topBean.title,topBean.image,topBean.uri));
                    }
                });
    }

    private void initRecyclerView() {
        mIsRefresh=true;
        mSectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
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
        recyclerView.setAdapter(mSectionedRecyclerViewAdapter);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ClearData();
                loadData();
            }
        });
    }

    private void ClearData() {
        mTopBeanList.clear();
        mRecommendBeanList.clear();
        mNewXBeanList.clear();
        mDynamicList.clear();
        mBannerEntities.clear();
        mSectionedRecyclerViewAdapter.removeAllSections();
        mIsRefresh=true;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_region_type_recommend;
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

    public static RegionTypeRecommendFragment newInstance(String rid,List<RegionHomeInfo.DataBean.ChildrenBean> mChildrenBeanList) {
        Bundle args = new Bundle();
        args.putString(ARG_RID,rid);
        args.putParcelableArrayList(ARG_CHILDRENLIST, (ArrayList<? extends Parcelable>) mChildrenBeanList);
        RegionTypeRecommendFragment fragment = new RegionTypeRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
