package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.HomeLiveFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.bangumi.BangumiFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.discover.HomeDiscoverFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.HomeRegionFragment;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class homepageAdapter extends FragmentPagerAdapter {

    private final String[] Title;

    private Fragment[] fragments;


    public homepageAdapter(FragmentManager fm,Context mContext) {

        super(fm);
        Title = mContext.getResources().getStringArray(R.array.sections);
        fragments=new Fragment[Title.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position]==null)
        {
            switch (position)
            {
                case 0:
                    fragments[position]= HomeLiveFragment.newInstance();
                    break;
                case 1:
                    fragments[position]= BangumiFragment.newInstance();
                    break;
                case 2:
                    fragments[position]= HomeDiscoverFragment.newInstance();
                    break;
                case 3:
                    fragments[position]= HomeRegionFragment.newInstance();
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return Title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Title[position];
    }
}
