package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.ViewGroup;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoDetail;

/**
 * Created by Administrator on 2016-10-21.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail
 * Author:Administrator
 * Fuction:简介的fragment的界面
 * UpdateUser:
 * UpdateDate:
 */
public class IntroductionFragment extends RxLazeFragment {
    private static final String AID = "aid";
    private static final String VIDEODETAIL = "videodetail";
    private int mAid;
    private Parcelable mVideodetail;

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
//        if (parentView.getParent()!=null)
//        {
//            ViewGroup parent =(ViewGroup) parentView.getParent();
//            parent.removeView(parentView);
//        }
        Bundle arguments = getArguments();
        if (arguments!=null)
        {
            mAid = arguments.getInt(AID);
            mVideodetail = arguments.getParcelable(VIDEODETAIL);
        }
        setVideoInfo();
    }

    private void setVideoInfo() {

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
    protected int getLayoutID() {
        return R.layout.fragment_introduction;
    }

    @Override
    protected void onlazyLoad() {

    }

    public static IntroductionFragment newInstance(VideoDetail mVideoDetail,int aid) {
        Bundle args = new Bundle();
        args.putInt(AID,aid);
        args.putParcelable(VIDEODETAIL,mVideoDetail);
        IntroductionFragment fragment = new IntroductionFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
