package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016-10-25.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.adapter.helper
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADERS_START = Integer.MIN_VALUE;
    private static final Integer ITEMS_START = Integer.MIN_VALUE + 20;
    private static final int ADAPTER_MAX_TYPES = 100;
    private static final int FOOTER_START = Integer.MIN_VALUE + 10;
    private List<View> HeaderViews,FooterViews;
    private RecyclerView.Adapter mWrappedAdapter;
    private HashMap<Class,Integer> mItemTypesOffset;

    public HeaderViewRecyclerAdapter(RecyclerView.Adapter adapter)
    {
        HeaderViews=new ArrayList<>();
        FooterViews=new ArrayList<>();
        mItemTypesOffset=new HashMap<>();
        setWrappedAdapter(adapter);
    }

    private void setWrappedAdapter(RecyclerView.Adapter adapter) {
        if (mWrappedAdapter!=null)mWrappedAdapter.unregisterAdapterDataObserver(mDataObserver);
        mWrappedAdapter=adapter;
        Class adapterClass= mWrappedAdapter.getClass();
        if (!mItemTypesOffset.containsKey(adapterClass))putAdapterTypeOffset(adapterClass);
        mWrappedAdapter.registerAdapterDataObserver(mDataObserver);
    }

    @Override
    public int getItemViewType(int position) {
        int headerCount = getHeaderCount();
        if (position<headerCount)return HEADERS_START+position;
        else
        {
            int itemCount = mWrappedAdapter.getItemCount();
            if (position<headerCount+itemCount)
            {
                return getAdapterTypeOffser()+mWrappedAdapter.getItemViewType(position-headerCount);
            }else return FOOTER_START+position-headerCount-itemCount;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType<HEADERS_START+getHeaderCount())
        {
            return new StaticViewHolder(HeaderViews.get(viewType-HEADERS_START));
        }else if (viewType<FOOTER_START+getFooterCount())
        {
            return new StaticViewHolder(FooterViews.get(viewType-FOOTER_START));
        }else {
            return mWrappedAdapter.onCreateViewHolder(parent,viewType-getAdapterTypeOffser());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int hCount = getHeaderCount();
        if (position>=hCount &&position<hCount+mWrappedAdapter.getItemCount())
        {
            mWrappedAdapter.onBindViewHolder(holder,position-hCount);
        }
    }

    public void addHeaderView(View view)
    {
        HeaderViews.add(view);
    }

    public void addFooterView(View view)
    {
        FooterViews.add(view);
    }

    public void removeHeadView()
    {
        HeaderViews.clear();
    }


    @Override
    public int getItemCount() {
        return getHeaderCount()+getFooterCount()+mWrappedAdapter.getItemCount();
    }

    private int getHeaderCount()
    {
        return HeaderViews.size();
    }

    private int getFooterCount()
    {
        return FooterViews.size();
    }

    private void putAdapterTypeOffset(Class adapterClass)
    {
        mItemTypesOffset.put(adapterClass,ITEMS_START+mItemTypesOffset.size()*ADAPTER_MAX_TYPES);
    }

    private int getAdapterTypeOffser()
    {
       return mItemTypesOffset.get(mWrappedAdapter.getClass());
    }

    private RecyclerView.AdapterDataObserver mDataObserver=new RecyclerView.AdapterDataObserver()
    {
        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart+getHeaderCount(),itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart+getHeaderCount(),itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            notifyItemRangeChanged(fromPosition+getHeaderCount(),toPosition+getHeaderCount()+itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart+getHeaderCount(),itemCount);
        }
    };

    private static class StaticViewHolder extends RecyclerView.ViewHolder
    {

        public StaticViewHolder(View itemView) {
            super(itemView);
        }
    }
}
