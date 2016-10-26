package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016-10-25.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener{

    private LinearLayoutManager linearLayoutManager;
    private boolean loading=true;
    private int  previousTotal=0;
    private int  currentPage=1;

    protected EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager)
    {
        this.linearLayoutManager=linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visiableItemCount = recyclerView.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();//得到recycleView Item所有的总数

        int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

        if (loading)
        {
            //总数改变就是 多加了一些数据，说明下载完了
            if (totalItemCount> previousTotal)
            {
                loading=false;
                previousTotal=totalItemCount;
            }
        }
        if (!loading &&(visiableItemCount>0)
                &&(lastCompletelyVisibleItemPosition>=totalItemCount-1))
        {
            //这里就是可以取下载更多的请求了 触发下载
            currentPage++;
            onLoadMore(currentPage);
            loading=true;
        }
    }

    protected abstract void onLoadMore(int currentPage);
}
