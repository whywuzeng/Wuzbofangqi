package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.facebook.stetho.common.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2016-10-19.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SearchPageAdapter extends FragmentPagerAdapter {

    private List<String> titles;
    private Fragment[] fragments;
    private boolean isClear=false;
    private FragmentManager fm;
    public SearchPageAdapter(FragmentManager fm,Fragment[] fragments,List<String> titles) {
        super(fm);
        this.fm=fm;
        this.titles=titles;
        this.fragments=fragments;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtil.i("instantiateItem: "+position);
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        if (isClear &&position==0)
        {
            LogUtil.i("update new fragment");

            String tag = fragment.getTag();

            FragmentTransaction ft = fm.beginTransaction();

            ft.remove(fragment);

            fragment=getItem(position);

            ft.add(container.getId(),fragment,tag);

            ft.attach(fragment);

            ft.commit();

            isClear=false;
        }
        return fragment;
    }

    public boolean ClearData()
    {
        isClear=true;
        return isClear;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
