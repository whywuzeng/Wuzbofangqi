package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public abstract class RxLazeFragment extends RxFragment {

    protected View parentView;

    private Activity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (parentView != null) {
            ViewGroup parent = (ViewGroup) parentView.getParent();
            if (parent != null) {
                parent.removeView(parentView);
            }
        }
        try {
            parentView=  inflater.inflate(getLayoutID(),container,false);
        } catch (InflateException e) {

        }
//        isParentView();
        return parentView;
    }

    protected void isParentView() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        OnViewCreateFinish(savedInstanceState);

    }

    protected abstract void OnViewCreateFinish(Bundle savedInstanceState);


    protected abstract int getLayoutID();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
        {
            onlazyLoad();
        }
    }

    protected abstract void onlazyLoad();
}
