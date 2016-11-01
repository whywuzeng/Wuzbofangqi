package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils.converWeekDay;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter.BangumiDetailRecommendAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter.BangumiSelectionAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.BangumiDetailRecommend;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SeasonBangumiSerial;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import rx.Observer;

/**
 * Created by Administrator on 2016-10-31.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class BangumiDetailActivity extends RxAppBasecompatActivity {

    private List<String> tags= Arrays.asList("日常","搞笑","漫改","职场","魔法","致郁","百合", "战斗", "原创", "奇幻", "热血");


    private static final String BANGUMIDETAILE = "bangumidetaile";
    @Bind(R.id.bangumi_bg)
    ImageView bangumiBg;
    @Bind(R.id.bangumi_pic)
    ImageView bangumiPic;
    @Bind(R.id.bangumi_title)
    TextView bangumiTitle;
    @Bind(R.id.bangumi_update)
    TextView bangumiUpdate;
    @Bind(R.id.bangumi_play)
    TextView bangumiPlay;
    @Bind(R.id.recycle_bangumi_selection)
    RecyclerView recycleBangumiSelection;
    @Bind(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @Bind(R.id.bangumi_recommend_recycler)
    RecyclerView bangumiRecommendRecycler;
    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.tv_jianjie_content)
    TextView tvJianjieContent;

    private SeasonBangumiSerial.ListBean mListBean;

   private List<BangumiDetailRecommend.ResultBean> mBangumiDetailRecommendList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bangumi_detail;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        if (getIntent() != null) {
            Intent intent = getIntent();
            mListBean = intent.getParcelableExtra(BANGUMIDETAILE);
        }

        RetrofitHelper.getBangumiDetailRecommend()
                .compose(this.<BangumiDetailRecommend>bindToLifecycle())
                .subscribe(new Observer<BangumiDetailRecommend>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(BangumiDetailRecommend bangumiDetailRecommend) {
                        mBangumiDetailRecommendList = bangumiDetailRecommend.result;
                        getBangumiDetails();
                    }
                });

    }

    private void getBangumiDetails() {

        Glide.with(this)
                .load(mListBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .placeholder(R.drawable.bili_default_image_tv)
                .into(bangumiPic);
        Glide.with(this)
                .load(mListBean.cover)
                .bitmapTransform(new BlurTransformation(this))
                .into(bangumiBg);


        bangumiTitle.setText(mListBean.title);

        //设置番剧的更新日期
        bangumiUpdate.setText("连载中,每周" + converWeekDay.converweekday(mListBean.weekday) + "更新");

        //设置番剧的播放和追番数量

        bangumiPlay.setText("播放:" + mListBean.playCount + "  追番:" + mListBean.favorites);

        tvJianjieContent.setText(mListBean.title);

        Random random=new Random();
        List<String> strings = tags.subList(0, random.nextInt(10));
        tagsLayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {

                TextView tags = (TextView) LayoutInflater.from(BangumiDetailActivity.this).inflate(R.layout.tags_item_layout, parent, false);
                tags.setText(s);
                return tags;
            }
        });

        initSelectionRecycler();
        initRecommendRecycler();
    }

    //推荐的列表
    private void initRecommendRecycler() {

        bangumiRecommendRecycler.setHasFixedSize(false);
        bangumiRecommendRecycler.setNestedScrollingEnabled(false);
        bangumiRecommendRecycler.setLayoutManager(new LinearLayoutManager(this));
        BangumiDetailRecommendAdapter bangumiDetailRecommendAdapter = new BangumiDetailRecommendAdapter(bangumiRecommendRecycler, this);
        bangumiRecommendRecycler.setAdapter(bangumiDetailRecommendAdapter);
        bangumiDetailRecommendAdapter.SetAllData(mBangumiDetailRecommendList);
    }

    //选择的selection
    private void initSelectionRecycler() {
        recycleBangumiSelection.setNestedScrollingEnabled(false);
        recycleBangumiSelection.setHasFixedSize(false);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        recycleBangumiSelection.setLayoutManager(manager);
        final BangumiSelectionAdapter bangumiSelectionAdapter = new BangumiSelectionAdapter(recycleBangumiSelection, this);
        recycleBangumiSelection.setAdapter(bangumiSelectionAdapter);
        bangumiSelectionAdapter.SetAllData(mListBean.bgmcount);
        bangumiSelectionAdapter.setmOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                bangumiSelectionAdapter.nowItemClick(holder.getAdapterPosition());
                //逻辑判断  跳转

            }
        });
    }

    @Override
    public void initToolBar() {
        homeToolbar.setTitle("番剧详情");
        setSupportActionBar(homeToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null)
        {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launcher(Activity activity, SeasonBangumiSerial.ListBean mListBean) {
        Intent mIntent = new Intent(activity, BangumiDetailActivity.class);
        mIntent.putExtra(BANGUMIDETAILE, mListBean);
        activity.startActivity(mIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
