package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.RegionTypeDetailFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.RegionTypeRecommendFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionHomeInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016-11-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionTypeDetailPageAdapter extends FragmentPagerAdapter {

    private String[] titlestring={"推荐"};

    private List<String> mTitles;

    private List<RegionHomeInfo.DataBean.ChildrenBean> mChildrenBeanList;

    private int rid;

    public RegionTypeDetailPageAdapter(FragmentManager fm,@NonNull int rid,@NonNull List<String> titles, @NonNull List<RegionHomeInfo.DataBean.ChildrenBean> ChildrenBeanList) {
        super(fm);
        this.rid=rid;
        mTitles=titles;
        mChildrenBeanList=ChildrenBeanList;
    }

    @Override
    public Fragment getItem(int position) {
        final List<Fragment> fragmentList=new ArrayList<>();

        fragmentList.add( RegionTypeRecommendFragment.newInstance(rid+"",mChildrenBeanList));

        Observable.from(mChildrenBeanList)
                .subscribe(new Action1<RegionHomeInfo.DataBean.ChildrenBean>() {
                    @Override
                    public void call(RegionHomeInfo.DataBean.ChildrenBean childrenBean) {
                        fragmentList.add(RegionTypeDetailFragment.newInstance(childrenBean.tid + ""));

                    }
                });

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
