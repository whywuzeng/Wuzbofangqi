package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.Live;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleImageView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Administrator on 2016-10-13.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.Live
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class LivePlayActivity extends RxAppBasecompatActivity {


    private static final String CID = "cid";
    private static final String TITLE = "title";
    private static final String IMAGE_URL = "image_url";
    private static final String NAME = "name";
    private static final String LIVE_COUNT = "live_count";
    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.video_view)
    SurfaceView videoView;

    @Bind(R.id.bili_anim)
    ImageView biliAnim;
    @Bind(R.id.bili_anim_loadtv)
    TextView biliAnimLoadtv;
    @Bind(R.id.circle_avator_img)
    CircleImageView circleAvatorImg;
    @Bind(R.id.tv_livesdetails_username)
    TextView tvLivesdetailsUsername;
    @Bind(R.id.tv_live_count)
    TextView tvLiveCount;

    private SurfaceHolder holder;
    private IjkMediaPlayer ijkMediaPlayer;
    private String mCid;
    private AnimationDrawable biliAnimBackground;
    private String mTitle;
    private String mImage_url;
    private String mName;
    private int mLive_count;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lives_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        mCid = intent.getIntExtra(CID, -1) + "";
        mTitle = intent.getStringExtra(TITLE);
        mImage_url = intent.getStringExtra(IMAGE_URL);
        mName = intent.getStringExtra(NAME);
        mLive_count = intent.getIntExtra(LIVE_COUNT, -1);
        initVideo();
        startAnim();
        initUserInfo();
    }

    private void initUserInfo() {
        Glide.with(this).load(mImage_url).into(circleAvatorImg);
                tvLivesdetailsUsername.setText(mName);
        tvLiveCount.setText(mLive_count + "");
    }

    private void startAnim() {
        biliAnimBackground = (AnimationDrawable) biliAnim.getBackground();
        biliAnimBackground.start();
    }

    private void initVideo() {
        holder = videoView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();
        getLiveUrl();
    }

    private void getLiveUrl() {
        RetrofitHelper.getBiliLiveUrlApi()
                .getLiveUrl(Integer.valueOf(mCid))

                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {

                        try {
                            String res = responseBody.string();
                            String substring = res.substring(res.lastIndexOf("[") + 1, res.lastIndexOf("]") - 1);
                            return substring;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Object, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Object o) {
//                        (String)o
                        playVideo((String) o);

                        return Observable.timer(2, TimeUnit.SECONDS);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        //强行关闭也会调用这里
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("直播地址获取失败" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtil.i(aLong + "");
                        videoView.setVisibility(View.VISIBLE);
                        stopAnim();
                    }
                });

    }

    private void stopAnim() {
        biliAnimBackground.stop();
        biliAnim.setVisibility(View.GONE);
        biliAnimLoadtv.setVisibility(View.GONE);
    }

    private void playVideo(String url) {

        try {
            ijkMediaPlayer.setDataSource(this, Uri.parse(url));
            ijkMediaPlayer.setDisplay(holder);
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    ijkMediaPlayer.setDisplay(holder);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });

            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ijkMediaPlayer.setKeepInBackground(false);
    }

    @Override
    public void initToolBar() {
        homeToolbar.setTitle(mTitle);
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

    public static void Launcher(Activity activity, int cid, String title, String image_url
            , String name, int live_count
    ) {
        Intent mIntent = new Intent(activity, LivePlayActivity.class);
        mIntent.putExtra(CID, cid);
        mIntent.putExtra(TITLE, title);
        mIntent.putExtra(IMAGE_URL, image_url);
        mIntent.putExtra(NAME, name);
        mIntent.putExtra(LIVE_COUNT, live_count);

        activity.startActivity(mIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaPlayer.release();
    }
}
