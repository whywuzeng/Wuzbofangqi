package com.wuz.bofangqi.wuzbofangqi.wuzeng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils.PreferenceUtils;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);

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
                    finish();
                }
                else if (s.equals(GOTO_LOGIN))
                {

                    //登录的界面
                    finish();
                }
            }
        });

    }
}
