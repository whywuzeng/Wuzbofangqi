package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-25.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public abstract class AbsRecyclerViewAdapter extends RecyclerView.Adapter<AbsRecyclerViewAdapter.ClickableViewHolder>{

    private Context mContext;

    protected RecyclerView mRecyclerView;

    private List<RecyclerView.OnScrollListener> mListener =new ArrayList<>();

    public AbsRecyclerViewAdapter(RecyclerView recyclerView)
    {
        this.mRecyclerView=recyclerView;

        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                for (RecyclerView.OnScrollListener listener:mListener)
                {
                    listener.onScrollStateChanged(recyclerView,newState);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (RecyclerView.OnScrollListener listener:mListener)
                {
                    listener.onScrolled(recyclerView,dx,dy);
                }
            }
        });
    }



    public void addOnScrollListener(RecyclerView.OnScrollListener listener)
    {
        mListener.add(listener);
    }

    @Override
    public void onBindViewHolder(final ClickableViewHolder holder, final int position) {
        holder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    holder.getParentView().setOnClickListener(new View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View v) {

                                                                      mOnItemClickListener.onItemClick(position,holder);
                                                                  }
                                                              }
                    );

            }
        });
    }


    public void setContext(Context mContext)
    {
        this.mContext=mContext;
    }

    public Context getmContext()
    {
        return this.mContext;
    }

    /**
     * item, Click点击事件
     */
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener
    {
        public void onItemClick(int postion,ClickableViewHolder holder);
    }

    public void setmOnItemClickListener(OnItemClickListener mListener)
    {
        this.mOnItemClickListener=mListener;
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
