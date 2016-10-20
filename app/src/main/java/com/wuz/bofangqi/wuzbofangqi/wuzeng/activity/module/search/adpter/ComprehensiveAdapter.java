package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-10-20.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.search.adpter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class ComprehensiveAdapter extends RecyclerView.Adapter<ComprehensiveAdapter.MyViewHolder> {

    private Context mContext;

    private  List<SearchResult.ResultBean.Video1Bean> mvideo1=new ArrayList<>();

    public ComprehensiveAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    public void setAllData(SearchResult.ResultBean mResultBean)
    {
        mvideo1.clear();
        mvideo1.addAll(mResultBean.video1);
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.comprehensive_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mvideo1.get(position).pic).into(holder.imgOverBigpic);
        //【LexBurner  】14分钟带你了解fate英灵的前  世
        holder.tvTitleBig.setText(mvideo1.get(position).title);
        //play
        holder.tvPalynum.setText(mvideo1.get(position).play+"");
                //author
        holder.tvAuthor.setText(mvideo1.get(position).author);
        //favorites
        holder.tvCommentSubtitle.setText(mvideo1.get(position).favorites+"");
    }

    @Override
    public int getItemCount() {
        return mvideo1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_over_bigpic)
        ImageView imgOverBigpic;
        @Bind(R.id.tv_title_big)
        TextView tvTitleBig;
        @Bind(R.id.tv_author)
        TextView tvAuthor;
        @Bind(R.id.tv_palynum)
        TextView tvPalynum;
        @Bind(R.id.tv_comment_subtitle)
        TextView tvCommentSubtitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
