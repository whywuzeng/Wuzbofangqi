package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.Live.LivePlayActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter.HomeRegionFragmentAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionHomeInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016-11-08.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class HomeRegionFragment extends RxLazeFragment implements AbsRecyclerViewAdapter.OnItemClickListener{

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<RegionHomeInfo.DataBean> dataBeanList=new ArrayList<>();


    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
        loadData();
        initRecycler();
    }

    private void loadData() {
        RetrofitHelper.getRegionHomeInfo()
                .compose(this.<RegionHomeInfo>bindToLifecycle())
                .subscribe(new Observer<RegionHomeInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegionHomeInfo regionHomeInfo) {
                        dataBeanList.addAll(regionHomeInfo.data);
                    }
                });
    }

    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager);
        HomeRegionFragmentAdapter homeRegionFragmentAdapter = new HomeRegionFragmentAdapter(recyclerView, getActivity());
        recyclerView.setAdapter(homeRegionFragmentAdapter);

        homeRegionFragmentAdapter.setmOnItemClickListener(this);
    }

    @Override
    protected int getLayoutID() {

        return R.layout.fragment_homeregion;
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

    public static HomeRegionFragment newInstance() {

        Bundle args = new Bundle();

        HomeRegionFragment fragment = new HomeRegionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int postion, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
        switch (postion){
            case 0:
                startActivity(new Intent(getActivity(), LivePlayActivity.class));
                break;
            case 1:
                RegionHomeInfo.DataBean dataBean = dataBeanList.get(1);
                if (dataBean!=null)
                {
                    RegionTypeDetailActivity.launch(getActivity(),dataBean);
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;

        }
    }
}
