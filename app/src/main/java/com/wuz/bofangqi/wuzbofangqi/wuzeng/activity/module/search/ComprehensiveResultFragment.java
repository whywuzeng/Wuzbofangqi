package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search.adpter.ComprehensiveAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SearchArchiveInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SearchResult;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-20.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class ComprehensiveResultFragment extends RxLazeFragment {


    private static final String EXTRA_DATA = "extra_data";
    private static final String EXTRA_ARCHIVE = "extra_archive";
    @Bind(R.id.loading_faile)
    ImageView loadingFaile;
    @Bind(R.id.recyle)
    RecyclerView recyle;
    private SearchResult.ResultBean mResultBean;
    private SearchArchiveInfo.DataBean mArchiveList;

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
        mResultBean=getArguments().getParcelable(EXTRA_DATA);
        mArchiveList = getArguments().getParcelable(EXTRA_ARCHIVE);
        if (mArchiveList!=null) {
            initview();
        }else{
            loadingFaile.setVisibility(View.VISIBLE);
            recyle.setVisibility(View.GONE);
        }
    }

    private void initview() {
        recyle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ComprehensiveAdapter comprehensiveAdapter = new ComprehensiveAdapter(getActivity());
        recyle.setAdapter(comprehensiveAdapter);
        comprehensiveAdapter.setAllData(mResultBean,mArchiveList);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_comprehensive;
    }

    //在page中才会触发
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

    public static ComprehensiveResultFragment newInstance(SearchResult.ResultBean result,SearchArchiveInfo.DataBean dataBean) {

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_DATA,result);
        args.putParcelable(EXTRA_ARCHIVE,dataBean);
        ComprehensiveResultFragment fragment = new ComprehensiveResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
