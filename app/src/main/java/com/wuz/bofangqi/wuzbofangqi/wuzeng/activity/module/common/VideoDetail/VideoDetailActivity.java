package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.stetho.common.LogUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.VideoDetail;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observer;

/**
 * Created by Administrator on 2016-10-21.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoDetailActivity extends RxAppBasecompatActivity {

    private static final String EXTRA_AID = "EXTRA_AID";
    private static final String EXTRA_URLIMG = "EXTRA_URLIMG";
    @Bind(R.id.video_imgview)
    ImageView videoImgview;
    @Bind(R.id.toolbar_tv)
    TextView toolbarTv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.coll_toolbarlayout)
    CollapsingToolbarLayout collToolbarlayout;
    @Bind(R.id.tablayout)
    SlidingTabLayout tablayout;
    @Bind(R.id.view_pager_detail)
    ViewPager viewPager;
    @Bind(R.id.app_bar_layout_detail)
    AppBarLayout appBarLayout;
    @Bind(R.id.coordlayout_mian_content)
    CoordinatorLayout coordlayoutMianContent;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private int mIntExtra;
    private String mStringExtra;
    private VideoDetail mVideoDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_videodetail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent!=null)
        {
            mIntExtra = intent.getIntExtra(EXTRA_AID, -1);
            mStringExtra = intent.getStringExtra(EXTRA_URLIMG);
        }

        Glide.with(VideoDetailActivity.this)
                .load(mStringExtra)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(videoImgview);

        getVideoInfo();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到play
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                setViewsTranslation(verticalOffset);

            }
        });

    }

    private void getVideoInfo() {
        RetrofitHelper.getVideoDetail(mIntExtra)
                .compose(this.<VideoDetail>bindToLifecycle())
                .subscribe(new Observer<VideoDetail>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                        fab.setClickable(false);
                        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_20)));
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(VideoDetail videoDetail) {
                        mVideoDetail = videoDetail;
                        finishGetTask();
                    }
                });
    }

    private void finishGetTask() {

//        theme_color_primary
        fab.setClickable(true);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.theme_color_primary)));

        List<String> titles=new ArrayList<>();
        titles.add("简介");
        titles.add("评论"+"("+mVideoDetail.videoReview+")");
        //创建两个fragment

        IntroductionFragment mIntroductionFragment = IntroductionFragment.newInstance(mVideoDetail, mIntExtra);
        VideoCommentFragment videoCommentFragment = VideoCommentFragment.newInstance(mIntExtra);

        List<Fragment> fragments=new ArrayList<>();
        fragments.add(mIntroductionFragment);
        fragments.add(videoCommentFragment);

        VideoDetailPageAdapter videoDetailPageAdapter = new VideoDetailPageAdapter(getSupportFragmentManager(), titles, fragments);

        viewPager.setAdapter(videoDetailPageAdapter);
        tablayout.setViewPager(viewPager);

    }

    private void setViewsTranslation(int verticalOffset) {
        fab.setTranslationY(verticalOffset);
        if (verticalOffset==0)
        {
            showfab();
        }else if (verticalOffset<0)
        {
            hidefab();
        }
    }

    private void showfab() {
        fab.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .start();
    }

    private void hidefab() {
        fab.animate()
                .scaleX(0)
                .scaleY(0)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    @Override
    public void initToolBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null)
        {
            setSupportActionBar(toolbar);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        //舒展开的字体的颜色
        collToolbarlayout.setExpandedTitleColor(Color.TRANSPARENT);
        //收缩起来的字体的颜色
        collToolbarlayout.setCollapsedTitleTextColor(Color.WHITE);

        //这里可以设置透明
    }

    public static void launch(Activity activity,int aid,String urlimg) {
        Intent mIntent = new Intent(activity, VideoDetailActivity.class);
        mIntent.putExtra(EXTRA_AID,aid);
        mIntent.putExtra(EXTRA_URLIMG, urlimg);
        activity.startActivity(mIntent);
    }


    public class VideoDetailPageAdapter extends FragmentPagerAdapter
    {

        private List<String> titles=new ArrayList<>();
        private List<Fragment> fragments;
        public VideoDetailPageAdapter(FragmentManager fm,List<String> titles,List<Fragment> fragments) {
            super(fm);
            this.titles=titles;
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
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
}
