package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.discover;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search.TotalStatetionSearchActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.hotTagsSearch;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016-10-18.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.discover
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class HomeDiscoverFragment extends RxLazeFragment implements View.OnClickListener {

    @Bind(R.id.search_edit)
    TextView searchEdit;
    @Bind(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @Bind(R.id.hide_tags_layout)
    TagFlowLayout hideTagsLayout;
    @Bind(R.id.tv_load_more)
    TextView tvLoadMore;
    @Bind(R.id.rl_interest)
    RelativeLayout rlInterest;
    @Bind(R.id.rl_topic)
    RelativeLayout rlTopic;
    @Bind(R.id.rl_activity_center)
    RelativeLayout rlActivityCenter;
    @Bind(R.id.rl_original)
    RelativeLayout rlOriginal;
    @Bind(R.id.rl_region_list)
    RelativeLayout rlRegionList;
    @Bind(R.id.rl_game_center)
    RelativeLayout rlGameCenter;
    @Bind(R.id.hide_scroll_view)
    NestedScrollView hideScrollView;
    @Bind(R.id.more_layout)
    LinearLayout moreLayout;

    private List<hotTagsSearch.ListEntity> list_hottags = new ArrayList<>();
    private boolean isMore = true;

    public static HomeDiscoverFragment newInstance() {
        Bundle args = new Bundle();

        HomeDiscoverFragment fragment = new HomeDiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {

        initEvent();
        hideScrollView.setNestedScrollingEnabled(true);
        tvLoadMore.setOnClickListener(this);
        getTags();
    }

    private void getTags() {
        //从网络得到热搜字段
        RetrofitHelper.getHotTagsSearch()
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        list_hottags.addAll(((hotTagsSearch) o).list);
                        initTagSLayout(list_hottags);
                    }
                });
    }


    private void initTagSLayout(List<hotTagsSearch.ListEntity> list_hottags) {
        final List<hotTagsSearch.ListEntity> listEntities = list_hottags.subList(0, 7);

        tagsLayout.setAdapter(new TagAdapter<hotTagsSearch.ListEntity>(listEntities) {

            @Override
            public View getView(FlowLayout parent, int position, hotTagsSearch.ListEntity listEntity) {
                TextView mTag = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tags_item_layout, parent, false);
                mTag.setText(listEntity.keyword);
                //这里写响应事件

                return mTag;
            }
        });

        hideTagsLayout.setAdapter(new TagAdapter<hotTagsSearch.ListEntity>(list_hottags) {

            @Override
            public View getView(FlowLayout parent, int position, hotTagsSearch.ListEntity listEntity) {
                TextView mTag = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tags_item_layout, parent, false);
                mTag.setText(listEntity.keyword);
                return mTag;
            }
        });
    }

    private void initEvent() {
        searchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //前往搜索界面
                TotalStatetionSearchActivity.launch(getActivity());
            }
        });

//        tagsLayout
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_discover;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_load_more:

                if (isMore) {
                    isMore = false;
                    tvLoadMore.setText("收起");
                    tagsLayout.setVisibility(View.GONE);
                    hideScrollView.setVisibility(View.VISIBLE);
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_up_gray_round);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvLoadMore.setCompoundDrawables(drawable, null, null, null);
                }
                else {
                    isMore = true;
                    tvLoadMore.setText("加载更多");
                    tagsLayout.setVisibility(View.VISIBLE);
                    hideScrollView.setVisibility(View.GONE);
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_down_gray_round);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tvLoadMore.setCompoundDrawables(drawable, null, null, null);
                }
                break;
        }
    }
}
