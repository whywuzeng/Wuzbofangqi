package com.wuz.bofangqi.wuzbofangqi.wuzeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils.PreferenceUtils;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.MainActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class SplashMainActivity extends AppCompatActivity {

    public static final String GOTO_HOME="goto_home";
    public static final String GOTO_LOGIN="goto_login";
    public static final String KEY="login";
    private TextView tv_hellowd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);
        tv_hellowd=(TextView)findViewById(R.id.tv_hellowd);
        ButterKnife.bind(this);
        setUpSplash();
    }

    private void setUpSplash() {

        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong) {
                        boolean isLogin = PreferenceUtils.getBoolean(KEY, false);
                        if (isLogin)
                        {
                            return Observable.just(GOTO_HOME);
                        }
                        else {
                            return Observable.just(GOTO_LOGIN);
                        }
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                if (s.equals(GOTO_HOME))
                {

                    //去home
                    startActivity(new Intent(SplashMainActivity.this, MainActivity.class));
                    finish();
                }
                else if (s.equals(GOTO_LOGIN))
                {
                    startActivity(new Intent(SplashMainActivity.this, MainActivity.class));
                    //登录的界面
                    finish();
                }
            }
        });

    }
}
