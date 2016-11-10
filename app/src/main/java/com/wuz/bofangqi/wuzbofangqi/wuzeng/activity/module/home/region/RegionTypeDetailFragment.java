package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter.RegionDetailHotSection;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionChildInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleProgressView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.SectionedRecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016-11-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionTypeDetailFragment extends RxLazeFragment {

    private static final String ARD_ARGS = "ard_args";
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.circle_progress)
    CircleProgressView circleProgress;
    private String args_ard;
    private List<RegionChildInfo.DataBean.RecommendBean> recommendBeanList;
    private List<RegionChildInfo.DataBean.RecommendBean> newXBeansList;

    private SectionedRecyclerViewAdapter mSectionedRecyclerViewAdapter;

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments!=null)
        {
            args_ard = arguments.getString(ARD_ARGS);
        }
        showCricleProgress();
        initRecyclerView();
        LoadData();
    }

    private void initRecyclerView() {
        mSectionedRecyclerViewAdapter=new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mSectionedRecyclerViewAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 1;
                    default:
                        return 1;
                }
            }
        });

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mSectionedRecyclerViewAdapter);
    }

    private void LoadData() {
        RetrofitHelper.getRegionChildInfo(Integer.valueOf(args_ard))
                .compose(this.<RegionChildInfo>bindToLifecycle())
                .subscribe(new Observer<RegionChildInfo>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted..:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideCricleProgress();
                        LogUtil.i("onError..:"+e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(RegionChildInfo regionChildInfo) {
                        recommendBeanList = regionChildInfo.data.recommend;
                        newXBeansList = regionChildInfo.data.newX;
                        finishTask();
                    }
                });
    }

    private void finishTask() {
        //去掉 progress
        //安装 适配器
        hideCricleProgress();
        mSectionedRecyclerViewAdapter.addSection(new RegionDetailHotSection(getActivity(), newXBeansList));
        mSectionedRecyclerViewAdapter.notifyDataSetChanged();

//        recyclerView.setAdapter();
    }

    private void hideCricleProgress(){
        circleProgress.stopSpinning();
        circleProgress.setVisibility(View.GONE);
    }

    private void showCricleProgress() {
        //转动
        circleProgress.setVisibility(View.VISIBLE);
        circleProgress.spin();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_region_typedetail;
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

    public static RegionTypeDetailFragment newInstance(String rid) {

        Bundle args = new Bundle();
        args.putString(ARD_ARGS,rid);
        RegionTypeDetailFragment fragment = new RegionTypeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
