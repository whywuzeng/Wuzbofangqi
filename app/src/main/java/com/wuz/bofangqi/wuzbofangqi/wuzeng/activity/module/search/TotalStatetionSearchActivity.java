package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.stetho.common.LogUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils.KeyBoardUtil;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search.adpter.SearchPageAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SearchResult;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016-10-19.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class TotalStatetionSearchActivity extends RxAppBasecompatActivity implements View.OnClickListener {

    @Bind(R.id.img_card_back)
    ImageView imgCardBack;
    @Bind(R.id.edit_query)
    EditText editQuery;
    @Bind(R.id.img_over_write)
    ImageView imgOverWrite;
    @Bind(R.id.img_search)
    ImageView imgSearch;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.center_loading_img)
    ImageView centerLoadingImg;
    @Bind(R.id.ll_search_layout)
    LinearLayout llSearchLayout;
    private AnimationDrawable mAnimationDrawable;
    private SearchResult.PageinfoBean pageinfoBean;
    private SearchResult.ResultBean resultBean;

    private Fragment[] fragments=new Fragment[1];

    private List<String> titles=new ArrayList<>();

    @Override
    public int getLayoutId() {

        return R.layout.search_activity;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        imgCardBack.setOnClickListener(this);
        Drawable drawable = getDrawable(R.drawable.search_loading_anim);
        centerLoadingImg.setImageDrawable(drawable);
        mAnimationDrawable = (AnimationDrawable) centerLoadingImg.getDrawable();
        getSearchData();
        search();
    }

    private void getSearchData() {
        RxTextView.textChanges(editQuery)
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {

                        return charSequence.toString();
                    }
                }).compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            imgOverWrite.setVisibility(View.VISIBLE);
                        }
                        else {
                            imgOverWrite.setVisibility(View.GONE);
                        }
                    }
                });

        RxView.clicks(imgOverWrite)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        editQuery.setText("");
                    }
                });

        RxTextView.editorActions(editQuery)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return !TextUtils.isEmpty(editQuery.getText().toString().trim());
                    }
                }).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer == EditorInfo.IME_ACTION_SEARCH;
            }
        }).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {

                return Observable.just(editQuery.getText().toString().trim());
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        KeyBoardUtil.closeKeyboard(editQuery, TotalStatetionSearchActivity.this);
                        //打开下载的动画
                        ShowLoadAnim();
                        //去数据搜索
                        SearchData(s);
                        //吧edittext的数据 clear掉
                    }
                });

    }

    private void search()
    {
        RxView.clicks(imgSearch)
                .throttleFirst(2, TimeUnit.SECONDS)
                .map(new Func1<Void, String>() {
                    @Override
                    public String call(Void aVoid) {
                        return  editQuery.getText().toString().trim();
                    }
                }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return !TextUtils.isEmpty(s);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        KeyBoardUtil.closeKeyboard(editQuery,
                                TotalStatetionSearchActivity.this);
                        ShowLoadAnim();
                        SearchData(s);
                    }
                });
    }


    /**
     * 得到数据，封装数据去net搜索
     *
     * @param data
     */
    private void SearchData(String data) {
        int page=1;
        int count=10;
        RetrofitHelper.getSearchResult(data, page, count)
                .compose(this.<SearchResult>bindToLifecycle())
                .subscribe(new Observer<SearchResult>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.i("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("onError"+e.getLocalizedMessage());
                        setEmptyLayout();
                    }

                    @Override
                    public void onNext(SearchResult searchResult) {
                        pageinfoBean = searchResult.pageinfo;
                        resultBean = searchResult.result;
                        finishTask();
                    }
                });
    }

    private void setEmptyLayout() {
        centerLoadingImg.setVisibility(View.GONE);
        llSearchLayout.setVisibility(View.GONE);
        centerLoadingImg.setImageResource(R.drawable.search_failed);
    }

    private void finishTask() {
        HideLayoutAnim();
        titles.add("综合");
//        titles.add("番剧"+"("+pageinfoBean.bangumi.numResults+")");
//        titles.add("话题"+"("+pageinfoBean.topic.numResults+")");

        ComprehensiveResultFragment comprehensiveResultFragment =  ComprehensiveResultFragment.newInstance(resultBean);
        fragments[0]=comprehensiveResultFragment;

        SearchPageAdapter searchPageAdapter = new SearchPageAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(searchPageAdapter);
        slidingTabs.setViewPager(viewPager);
        searchPageAdapter.notifyDataSetChanged();
    }

    private void HideLayoutAnim() {
        centerLoadingImg.setVisibility(View.GONE);
        llSearchLayout.setVisibility(View.VISIBLE);
        mAnimationDrawable.stop();
    }

    public static void launch(Activity activity)
    {
        Intent mIntent=new Intent(activity,TotalStatetionSearchActivity.class);
        activity.startActivity(mIntent);
    }

    /**
     * 下载的动画
     */
    private void ShowLoadAnim() {

        centerLoadingImg.setVisibility(View.VISIBLE);
        llSearchLayout.setVisibility(View.GONE);
        mAnimationDrawable.start();

    }

    @Override
    public void initToolBar() {

    }

    //按回退键
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_card_back:
                onBackPressed();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAnimationDrawable!=null) {
            mAnimationDrawable.stop();
            mAnimationDrawable=null;
        }
    }
}
