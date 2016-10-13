package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.fragment1;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.HomeLiveFragment;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleImageView;

/**
 * Created by Administrator on 2016-10-09.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity
 * Author:Administrator
 * Fuction: 主界面
 * UpdateUser:
 * UpdateDate:
 */
public class MainActivity extends RxAppBasecompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment[] fragments;
    private NavigationView navigation_view;
    private DrawerLayout drawerlayout;
    private int index;
    private int CurrentTabIndex=0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //初始化Fragment
        initFragments();
        //初始化侧滑菜单
        initNavigationView();
    }

    private void initNavigationView() {
        navigation_view=(NavigationView)findViewById(R.id.navigation_view);
        drawerlayout=(DrawerLayout)findViewById(R.id.drawerlayout);

        navigation_view.setNavigationItemSelectedListener(this);
        View headerView = navigation_view.getHeaderView(0);
//        user_avatar_view
//                tv_user_male_border
//        tv_user_round_level
//                tv_user_context
        CircleImageView user_avatar_view = (CircleImageView)headerView.findViewById(R.id.user_avatar_view);
        TextView tv_user_context = (TextView)headerView.findViewById(R.id.tv_user_context);
        tv_user_context.setText("硬币:47");

    }

    private void initFragments() {
        fragment1 fragment1 = com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.fragment1.newInstance(1 + "");
        HomeLiveFragment homeLiveFragment = HomeLiveFragment.newInstance();
        fragment1 fragment3 = com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.fragment1.newInstance(3 + "");
        fragment1 fragment4 = com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.fragment1.newInstance(4 + "");

        fragments=new Fragment[]{
            fragment1,homeLiveFragment,fragment3,fragment4
        };

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contain_rl,fragment1)
                .show(fragment1).commit();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
    /*    android:id="@+id/item_home"
        android:icon="@drawable/ic_home_black_24dp"
        android:title="主页"
        android:checked="true"/>
        <item
        android:id="@+id/item_cache"*/
        switch (item.getItemId())
        {
            case R.id.item_home:
            changeFragmentIndex(item,0);
                toggleDrawer();
                break;
            case R.id.item_cache:
                changeFragmentIndex(item,1);
                toggleDrawer();
                break;
        }
        return false;
    }

    private void changeFragmentIndex(MenuItem item, int CurrentIndex) {
        index=CurrentIndex;
        SwitchFragment();
        item.setChecked(true);
    }

    /**
     * fragment 切换 多练练
     */
    private void SwitchFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.hide(fragments[CurrentTabIndex]);
        if (!fragments[index].isAdded())
        {
            ft.add(R.id.contain_rl,fragments[index]);
        }
        ft.show(fragments[index]).commit();
        CurrentTabIndex=index;
    }

    public void toggleDrawer()
    {
        if (drawerlayout.isDrawerOpen(GravityCompat.START))
        {
            drawerlayout.closeDrawer(GravityCompat.START);
        }else
        {
            drawerlayout.openDrawer(GravityCompat.START);
        }
    }
}
