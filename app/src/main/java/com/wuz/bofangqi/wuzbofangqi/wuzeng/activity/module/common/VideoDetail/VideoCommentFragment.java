package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter.VideoCommentAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoComment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016-10-24.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoCommentFragment extends RxLazeFragment {

    private static final String AID = "aid";
    @Bind(R.id.recyle)
    RecyclerView recyle;
    private int mAnInt;
    private int PageNum=1;
    private int PageSize=20;
    private List<VideoComment.ListBean> mListBean;
    private VideoCommentAdapter mVideoCommentAdapter;

    public static VideoCommentFragment newInstance(int aid) {
        Bundle args = new Bundle();
        args.putInt(AID, aid);
        VideoCommentFragment fragment = new VideoCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    
    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments!=null)
        {
            mAnInt = arguments.getInt(AID);
        }

        initRecyclerView();
        initVideoInfo();

    }

    private void initVideoInfo() {
        int ver=3;
        RetrofitHelper.getVideoComment(mAnInt,PageNum,PageSize,ver)
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

                        mListBean = videoComment.list;
                        finishTask();
                    }
                });

    }

    private void finishTask() {
        if (mListBean.size()>0)
        {
            if (mVideoCommentAdapter!=null)
            {
                mVideoCommentAdapter.setAllData(mListBean);
            }
        }
    }

    private void initRecyclerView() {
        recyle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVideoCommentAdapter = new VideoCommentAdapter(getActivity());
        recyle.setAdapter(mVideoCommentAdapter);
        mVideoCommentAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_video_comment;
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
