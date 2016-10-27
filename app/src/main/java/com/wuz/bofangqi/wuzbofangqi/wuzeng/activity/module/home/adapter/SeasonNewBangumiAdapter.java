package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-27.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class SeasonNewBangumiAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;

    public SeasonNewBangumiAdapter(RecyclerView recyclerView, Context Context) {
        super(recyclerView);
        this.mContext = Context;
    }


    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_new_season_bangumi, parent, false);
        return new SeasonClickViewholder(view);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SeasonClickViewholder extends ClickableViewHolder {

        @Bind(R.id.iv_season_center)
        ImageView ivSeasonCenter;
        @Bind(R.id.tv_season_banguminum)
        TextView tvSeasonBanguminum;
        @Bind(R.id.season_card_view)
        CardView seasonCardView;
        @Bind(R.id.tv_season_title)
        TextView tvSeasonTitle;

        public SeasonClickViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
