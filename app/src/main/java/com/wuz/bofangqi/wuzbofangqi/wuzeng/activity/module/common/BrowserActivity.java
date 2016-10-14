package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.base.RxAppBasecompatActivity;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.CircleProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-14.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class BrowserActivity extends RxAppBasecompatActivity {

    private static final String TITLE = "title";
    private static final String URL = "url";
    @Bind(R.id.home_toolbar)
    Toolbar homeToolbar;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.circle_progress)
    CircleProgressView circleProgress;
    private String mTitle;
    private String mUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(TITLE);
        mUrl = intent.getStringExtra(URL);

        setupWebView();
    }

    private void setupWebView() {

  /*      //设置WebView的一些缩放功能点
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setSupportZoom(true);*/

       /* //设置WebView可触摸放大缩小
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(70);
        webView.setHorizontalScrollbarOverlay(true);*/

        //WebView双击变大，再双击后变小，当手动放大后，双击可以恢复到原始大小
        //webView.getSettings().setUseWideViewPort(true);

     /*   //提高渲染的优先级
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);*/

       /* //允许JS执行
        webView.getSettings().setJavaScriptEnabled(true);*/

        //把图片加载放在最后来加载渲染
        //webView.getSettings().setBlockNetworkImage(true);
        //用WebView将字符串以HTML的形式显示出来
        //webView.loadDataWithBaseURL("fake://not/needed", <p>zzz</p>, "text/html", "utf-8", "");

        //在同种分辨率的情况下,屏幕密度不一样的情况下,自动适配页面:
//        DisplayMetrics dm = getResources().getDisplayMetrics();

        circleProgress.spin();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webView.getSettings().setBlockNetworkImage(true);
        webView.setWebViewClient(new BaseWebviewClient());
        webView.requestFocus(View.FOCUS_DOWN);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b2 = new AlertDialog.Builder(BrowserActivity.this)
                        .setTitle("wuzeng")
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        });
                b2.setCancelable(false);
                b2.create();
                b2.show();
                return true;
            }
        });

        webView.loadUrl(mUrl);

    }

public static void launch(Activity mActivity,String title,String url)
{
    Intent mIntent=new Intent(mActivity,BrowserActivity.class);
    mIntent.putExtra(TITLE,title);
    mIntent.putExtra(URL,url);
    mActivity.startActivity(mIntent);
}


        public class BaseWebviewClient extends WebViewClient
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                circleProgress.setVisibility(View.GONE);
                circleProgress.stopSpinning();
                webView.getSettings().setBlockNetworkImage(false);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                String errorHtml="<html><body><h2>找不到网页</h2></body></html>";
                view.loadDataWithBaseURL(null,errorHtml,"text/html","UTF-8",null);
            }
        }

    @Override
    protected void onPause() {
        webView.reload();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()&&webView.copyBackForwardList().getSize()>0&&!webView.getUrl().equals(webView.copyBackForwardList()
        .getItemAtIndex(0).getOriginalUrl()))
        {
            webView.goBack();
        }else {
            finish();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_browser,menu);
        return true;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
