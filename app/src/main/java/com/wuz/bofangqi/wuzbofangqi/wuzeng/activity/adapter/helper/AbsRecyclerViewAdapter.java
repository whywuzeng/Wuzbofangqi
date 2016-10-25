package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016-10-25.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public abstract class AbsRecyclerViewAdapter extends RecyclerView.Adapter<AbsRecyclerViewAdapter.ClickableViewHolder>{


    @Override
    public void onBindViewHolder(final ClickableViewHolder holder, final int position) {
        holder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }


    public interface OnItemClickListener
    {
        public void onItemClick(int postion,ClickableViewHolder holder);
    }




    public class ClickableViewHolder extends RecyclerView.ViewHolder {

        private View parentView;

        public ClickableViewHolder(View itemView) {
            super(itemView);
            this.parentView = itemView;
        }

        View getParentView() {
            return parentView;
        }

        public <T extends View> T $(@IdRes int id)
        {
            return (T)parentView.findViewById(id);
        }

    }
}
