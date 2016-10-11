package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.MainActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxLazeFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleImageView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.NoScrollViewPager;

import butterknife.Bind;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class homeFragment extends RxLazeFragment {

    @Bind(R.id.home_toolbar_itembtn)
    ImageView homeToolbarItembtn;
    @Bind(R.id.home_toolbar_user_avatar)
    CircleImageView homeToolbarUserAvatar;
    @Bind(R.id.home_toolbar_username)
    TextView homeToolbarUsername;
    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @Bind(R.id.view_page)
    NoScrollViewPager viewPage;
    @Bind(R.id.search_view)
    MaterialSearchView searchView;

    public static homeFragment newInstance() {

        Bundle args = new Bundle();

        homeFragment fragment = new homeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void OnViewCreateFinish(Bundle savedInstanceState) {
        initToolBar();
        initSearchView();
        initViewPager();
    }

    private void initViewPager() {


    }

    private void initSearchView() {

    }

    private void initToolBar() {
        homeToolbar.setTitle("");
        ((MainActivity)getActivity()).setSupportActionBar(homeToolbar);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.home_page_fragment;
    }


}
