package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.EndlessRecyclerOnScrollListener;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.HeaderViewRecyclerAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter.VideoCommentAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoComment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observer;

/**
 * Created by Administrator on 2016-10-24.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoCommentFragment extends RxLazeFragment implements View.OnClickListener{

    private static final String AID = "aid";
    @Bind(R.id.recyle_video_comment)
    RecyclerView recyle;
    @Bind(R.id.btn_access_net)
    Button btnAccessNet;
    private int mAnInt;
    private int PageNum = 1;
    private int PageSize = 20;
    private List<VideoComment.ListBean> mListBean=new ArrayList<>();
    private VideoCommentAdapter mVideoCommentAdapter;
    private View mLoadlayout;
    private HeaderViewRecyclerAdapter headerCommentViewRecyclerAdapter;

    public static VideoCommentFragment newInstance(int aid) {
        Bundle args = new Bundle();
        args.putInt(AID, aid);
        VideoCommentFragment fragment = new VideoCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void isParentView() {
        if (parentView.getParent()!=null)
        {
            ViewGroup parent =(ViewGroup) parentView.getParent();
            parent.removeView(parentView);
        }
    }

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
//        if (parentView.getParent()!=null)
//        {
//            ViewGroup parent =(ViewGroup) parentView.getParent();
//            parent.removeView(parentView);
//        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            mAnInt = arguments.getInt(AID);
        }

        initRecyclerView();
        btnAccessNet.setOnClickListener(this);
        initVideoInfo();

    }

    private void initVideoInfo() {
        int ver = 3;
        RetrofitHelper.getVideoComment(mAnInt, PageNum, PageSize, ver)
                .compose(this.<VideoComment>bindToLifecycle())
                .subscribe(new Observer<VideoComment>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                        LogUtil.i("onError" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(VideoComment videoComment) {

                        if (videoComment.list.size() > 0) {
                            mListBean.addAll(videoComment.list);
                            finishTask();
                        }
                    }
                });

    }

    private void finishTask() {
        if (mListBean.size() > 0) {
            if (mVideoCommentAdapter != null) {
                mVideoCommentAdapter.setAllData(mListBean);
                mLoadlayout.setVisibility(View.GONE);
            }
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyle.setLayoutManager(linearLayoutManager);
        mVideoCommentAdapter = new VideoCommentAdapter(getActivity(),recyle);
        mVideoCommentAdapter.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            protected void onLoadMore(int currentPage) {
                PageNum++;
                initVideoInfo();
                mLoadlayout.setVisibility(View.VISIBLE);
            }
        });
        headerCommentViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mVideoCommentAdapter);
        recyle.setAdapter(headerCommentViewRecyclerAdapter);
        createLoadMoreView();
    }

    private void createLoadMoreView() {
        mLoadlayout = LayoutInflater.from(getActivity()).inflate(R.layout.layout_load_more, recyle, false);
        headerCommentViewRecyclerAdapter.addFooterView(mLoadlayout);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_video_comment;
    }

    @Override
    protected void onlazyLoad() {

    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_access_net)
        {
            PageNum++;
            initVideoInfo();
        }
    }
}
