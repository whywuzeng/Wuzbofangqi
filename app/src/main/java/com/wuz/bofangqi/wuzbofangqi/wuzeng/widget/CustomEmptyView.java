package com.wuz.bofangqi.wuzbofangqi.wuzeng.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuz.bofangqi.wuzbofangqi.R;

/**
 * Created by Administrator on 2016-10-11.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.widget
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class CustomEmptyView extends FrameLayout {

    private ImageView mEmptyImg;
    private TextView mEmptyTex;
    private Button mReloadBtn;

    private ReloadBtnListerner mReloadBtnListerner;


    public CustomEmptyView(Context context) {
        super(context);
    }

    public CustomEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, this);
//        android:id="@+id/empty_img"
        mEmptyImg= (ImageView)view.findViewById(R.id.empty_img);
        mEmptyTex= (TextView)view.findViewById(R.id.empty_text);
        mReloadBtn=(Button)view.findViewById(R.id.btn_reload);

    }

    public void reload(ReloadBtnListerner reloadBtnListerner)
    {
        this.mReloadBtnListerner=reloadBtnListerner;
        if (mReloadBtnListerner!=null)
        {
            mReloadBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mReloadBtnListerner.reloadClick();
                }
            });
        }
    }

    public void setmEmptyImg(int imgRes)
    {
        mEmptyImg.setImageResource(imgRes);
    }

    public void setmEmptyTex(String text)
    {
        mEmptyTex.setText(text);
    }

    public void hideReloadButton()
    {
        mReloadBtn.setVisibility(View.GONE);
    }

    public interface ReloadBtnListerner
    {
        void reloadClick();
    }


}
