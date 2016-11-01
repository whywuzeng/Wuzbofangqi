package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.BangumiDetailRecommend;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-11-01.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class BangumiDetailRecommendAdapter extends AbsRecyclerViewAdapter {

        private List<BangumiDetailRecommend.ResultBean> recommendsBeanList = new ArrayList<>();
        private Context mContext;

        public BangumiDetailRecommendAdapter(RecyclerView recyclerView, Context context) {
            super(recyclerView);
            this.mContext = context;
        }

        public void SetAllData(List<BangumiDetailRecommend.ResultBean> recommendsBeanList) {
            this.recommendsBeanList.clear();
            this.recommendsBeanList.addAll(recommendsBeanList);
            notifyDataSetChanged();
        }


        @Override
        public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.bangumirecommedadpteritem, parent, false);

            return new RecommedClickViewholder(view);
        }

        @Override
        public void onBindViewHolder(ClickableViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            if (holder instanceof RecommedClickViewholder)
            {
                RecommedClickViewholder viewholder=(RecommedClickViewholder)holder;
                Glide.with(mContext).load(recommendsBeanList.get(position).cover)
                        .into(viewholder.imgRecommed);
                viewholder.tvBangumiTexttitle.setText(recommendsBeanList.get(position).title);
                viewholder.tvBangumiContexttitle.setText(recommendsBeanList.get(position).desc);
            }
        }

        @Override
        public int getItemCount() {
            return recommendsBeanList.size();
        }

        class RecommedClickViewholder extends ClickableViewHolder {

            @Bind(R.id.img_recommed)
            ImageView imgRecommed;
            @Bind(R.id.tv_bangumi_texttitle)
            TextView tvBangumiTexttitle;
            @Bind(R.id.tv_bangumi_contexttitle)
            TextView tvBangumiContexttitle;
            @Bind(R.id.card_view)
            CardView cardView;

            public RecommedClickViewholder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

}
