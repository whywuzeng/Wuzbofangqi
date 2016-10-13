package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private View parentView;

    private Activity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      parentView=  inflater.inflate(getLayoutID(),container,false);

        ButterKnife.bind(this,parentView);
        return parentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
