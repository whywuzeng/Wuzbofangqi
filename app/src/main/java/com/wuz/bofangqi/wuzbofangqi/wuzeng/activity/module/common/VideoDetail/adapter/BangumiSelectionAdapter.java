package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.common.VideoDetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper.AbsRecyclerViewAdapter;

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
public class BangumiSelectionAdapter extends AbsRecyclerViewAdapter {

    private Context mContext;
    private int mCount;
    private int itemPosition=-1;

    public BangumiSelectionAdapter(RecyclerView recyclerView, Context context) {
        super(recyclerView);
        this.mContext = context;
    }

    public void SetAllData(String count)
    {
        this.mCount=Integer.valueOf(count);
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_selection_item, parent, false);
        return new SelectionClickableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof SelectionClickableViewHolder)
        {
            SelectionClickableViewHolder viewHolder = (SelectionClickableViewHolder) holder;
            viewHolder.tvFloorNum.setText(position+1+"");

            if (position==itemPosition)
            {
                viewHolder.tvFloorNum.setBackgroundColor(mContext.getResources().getColor( R.color.yellow_30));
            }else
            {
                viewHolder.tvFloorNum.setBackgroundColor(mContext.getResources().getColor(R.color.window_background));
            }
        }
    }

    public void nowItemClick(int positon)
    {
        itemPosition = positon;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCount==0?0:mCount;
    }

    public class SelectionClickableViewHolder extends ClickableViewHolder {

        @Bind(R.id.tv_floor_num)
        TextView tvFloorNum;
        public SelectionClickableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
