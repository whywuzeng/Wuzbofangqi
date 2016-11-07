package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.HDVideo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.media.DanmakuDownloadUtil;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.media.MediaController;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.media.VideoPlayerView;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.media.callback.DanmakuSwitchListener;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.media.callback.VideoBackListener;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.network.RetrofitHelper;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleProgressView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2016-11-01.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class VideoDetailPlayActivity extends RxAppBasecompatActivity implements DanmakuSwitchListener, VideoBackListener {


    private static final String EXTRA_CID = "extra_cid";
    private static final String EXTRA_TITLE = "extra_title";
    @Bind(R.id.palyerView)
    VideoPlayerView palyerView;

    @Bind(R.id.sv_danmaku)
    DanmakuView svDanmaku;

    @Bind(R.id.video_start_info)
    TextView videoStartInfo;

    @Bind(R.id.video_start)
    RelativeLayout videoStart;
    @Bind(R.id.video_loading_progress)
    CircleProgressView videoLoadingProgress;
    @Bind(R.id.tv_video_loading_text)
    TextView tvVideoLoadingText;
    @Bind(R.id.buffering_indicator)
    RelativeLayout bufferingIndicator;
    @Bind(R.id.bili_anim)
    ImageView biliAnim;
    @Bind(R.id.fl_framelayout)
    FrameLayout flFramelayout;
    private String CidIntExtra;
    private String TitlestringExtra;
    private String startText="初始化播放器...";
    private AnimationDrawable biliAnimBackground;
    private DanmakuContext danmakuContext;
    private int LastPosition=1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent mintent = getIntent();
        if (mintent!=null)
        {
            CidIntExtra = mintent.getStringExtra(EXTRA_CID);
            TitlestringExtra = mintent.getStringExtra(EXTRA_TITLE);
        }
        initAnimation();
        initMediaPlayer();
    }

    @SuppressLint("UseSparseArrays")
    private void initMediaPlayer() {
        //配置播放器
        MediaController mMediaController =new MediaController(this);
        mMediaController.setTitle(TitlestringExtra);
        palyerView.setMediaController(mMediaController);
        //这个干嘛？可以自动取消View的么
        palyerView.setMediaBufferingIndicator(bufferingIndicator);
        palyerView.requestFocus();
        palyerView.setOnInfoListener(onInfoListener);
        palyerView.setOnSeekCompleteListener(onSeekCompleteListener);
        palyerView.setOnCompletionListener(onCompletionListener);
        palyerView.setOnControllerEventsListener(controllerEventsListener);
        //设置弹幕开关
        mMediaController.setDanmakuSwitchListener(this);
        // 设置返回键监听
        mMediaController.setVideoBackEvent(this);

        //配置弹幕库
        svDanmaku.enableDanmakuDrawingCache(true);

        //设置最大显示行数
        HashMap<Integer,Integer> maxLinesPair=new HashMap<>();

        //滚动弹幕最大显示5行
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_LR,5);

        //设置是否禁止重叠
        HashMap<Integer,Boolean> overlappingEnablePair=new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR,true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP,true);
        //设置弹幕样式
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN,3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(0.8f)
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
        loadData();

    }

    //http://bilibili-service.daoapp.io/video/11166282?quailty=4&type=mp4 fate
    private void loadData() {
        RetrofitHelper.getHDVideoApi(Integer.valueOf(CidIntExtra),4,"mp4")
                .compose(this.<HDVideo>bindToLifecycle())
                .map(new Func1<HDVideo, Uri>() {
                    @Override
                    public Uri call(HDVideo hdVideo) {
                        return  Uri.parse(hdVideo.durl.get(0).url);
                    }
                }).flatMap(new Func1<Uri, Observable<BaseDanmakuParser>>() {
            @Override
            public Observable<BaseDanmakuParser> call(Uri uri) {

                palyerView.setVideoURI(uri);
                palyerView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(IMediaPlayer iMediaPlayer) {
                        biliAnimBackground.stop();
                        startText=startText+"[完成]\n视频缓冲中...";
                        videoStartInfo.setText(startText);
                        videoStart.setVisibility(View.GONE);

                    }
                });
                String url = "http://comment.bilibili.com/" + CidIntExtra + ".xml";
                return DanmakuDownloadUtil.downloadXML(url);
            }
        }).subscribe(new Observer<BaseDanmakuParser>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                LogUtil.i("onError"+e.getLocalizedMessage());
                startText=startText+"[失败]视频缓冲中...";
                videoStartInfo.setText(startText);
                startText=startText+"[失败]"+e.getLocalizedMessage();
                videoStartInfo.setText(startText);
            }

            @Override
            public void onNext(BaseDanmakuParser baseDanmakuParser) {
                svDanmaku.prepare(baseDanmakuParser,danmakuContext);
                svDanmaku.showFPS(true);
                svDanmaku.enableDanmakuDrawingCache(false);
                svDanmaku.setCallback(new DrawHandler.Callback() {
                    @Override
                    public void prepared() {
                        svDanmaku.start();
                    }

                    @Override
                    public void updateTimer(DanmakuTimer timer) {

                    }

                    @Override
                    public void danmakuShown(BaseDanmaku danmaku) {

                    }

                    @Override
                    public void drawingFinished() {

                    }
                });
                palyerView.start();
            }
        });

    }

    private void initAnimation() {
        videoStart.setVisibility(View.VISIBLE);
        startText=startText+"[完成]\n解析视频地址...[完成]\n 弹幕装填.....";
        videoStartInfo.setText(startText);
        biliAnimBackground = (AnimationDrawable) biliAnim.getBackground();
        biliAnimBackground.start();
    }

    @Override
    public void initToolBar() {

    }

    public static void launch(Activity activity,String cid,String title)
    {
        Intent mIntent=new Intent(activity,VideoDetailPlayActivity.class);
        mIntent.putExtra(EXTRA_CID,cid);
        mIntent.putExtra(EXTRA_TITLE,title);
        activity.startActivity(mIntent);
    }

    /**
     * 视频缓冲事件回调
     */
    private IMediaPlayer.OnInfoListener onInfoListener=new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {

            if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_START)
            {
                if (svDanmaku!=null&&svDanmaku.isPrepared())
                {
                    svDanmaku.pause();
                    if (bufferingIndicator!=null)
                    {
                        bufferingIndicator.setVisibility(View.VISIBLE);
                    }
                }
            }else if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_END)
            {
                if (svDanmaku!=null && svDanmaku.isPaused())
                {
                    svDanmaku.resume();
                }
                if (bufferingIndicator!=null)
                {
                    bufferingIndicator.setVisibility(View.GONE);
                }
            }

            return true;
        }
    };

    /**
     * 视频进度 事件回调
     */
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener=new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            if (svDanmaku != null && svDanmaku.isPrepared())
            {
                svDanmaku.seekTo(iMediaPlayer.getCurrentPosition());
            }
        }
    };

    /**
     * 视频迟缓
     */
    private IMediaPlayer.OnCompletionListener onCompletionListener=new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            if (svDanmaku!=null && svDanmaku.isPrepared())
            {
                svDanmaku.seekTo((long)0);
                svDanmaku.pause();
            }
            palyerView.pause();
        }
    };

    /**
     *  暂停和恢复
     */
    private VideoPlayerView.OnControllerEventsListener controllerEventsListener=new VideoPlayerView.OnControllerEventsListener() {
        @Override
        public void onVideoPause() {
            if (svDanmaku!=null && svDanmaku.isPrepared())
            {
                svDanmaku.pause();
            }
        }

        @Override
        public void OnVideoResume() {
            if (svDanmaku!=null &&svDanmaku.isPaused())
            {
                svDanmaku.resume();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (svDanmaku!=null && svDanmaku.isPrepared() &&svDanmaku.isPaused())
        {
            svDanmaku.seekTo((long) LastPosition);
        }
        if (palyerView!=null && !palyerView.isPlaying())
        {
            palyerView.seekTo(LastPosition);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (palyerView!=null)
        {
            LastPosition=palyerView.getCurrentPosition();
            palyerView.pause();
        }
        if (svDanmaku!=null&&svDanmaku.isPrepared())
        {
            svDanmaku.pause();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (svDanmaku!=null)
        {
            svDanmaku.release();
            svDanmaku=null;
        }
        if (biliAnimBackground!=null)
        {
            biliAnimBackground.stop();
            biliAnimBackground=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (palyerView!=null && palyerView.isDrawingCacheEnabled())
        {
            //销毁内存
            palyerView.destroyDrawingCache();
        }
        if (svDanmaku!=null && svDanmaku.isPaused())
        {
            svDanmaku.release();
            svDanmaku=null;
        }
        if (biliAnimBackground!=null)
        {
            biliAnimBackground.stop();
            biliAnimBackground=null;
        }
    }


    //弹幕开不开
    @Override
    public void setDanmakushow(boolean isShow) {
        if (svDanmaku!=null)
        {
            if (isShow)
            {
                svDanmaku.show();
            }else {
                svDanmaku.hide();
            }
        }
    }

    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }
}
