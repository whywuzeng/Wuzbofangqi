package com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.wuz.bofangqi.wuzbofangqi.wuzeng.App.OhMyWuzZhibo;

/**
 * Created by Administrator on 2016-11-10.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class ToastUtil {

    public static final boolean isShow=true;

    public static void showShort(Context context, String text)
    {
        if (isShow)
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int resId)
    {
        if (isShow)
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text)
    {
        if (isShow)
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId)
    {
        if (isShow)
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void LongToast(final String text)
    {
        if (isShow) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.
                            makeText(OhMyWuzZhibo.getmInstance(), text, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static void LongToast(final int stringId)
    {
        if (isShow) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.
                            makeText(OhMyWuzZhibo.getmInstance(), stringId, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static void ShortToast(final String text)
    {
        if (isShow) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.
                            makeText(OhMyWuzZhibo.getmInstance(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void ShortToast(final int stringId)
    {
        if (isShow) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.
                            makeText(OhMyWuzZhibo.getmInstance(), stringId, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
