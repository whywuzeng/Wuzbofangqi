package com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wuz.bofangqi.wuzbofangqi.R;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.Utils.ToastUtil;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.bean.RegionHomeInfo;
import com.wuz.bofangqi.wuzbofangqi.wuzeng.widget.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016-11-10.
 * ClassName: com.wuz.bofangqi.wuzbofangqi.wuzeng.activity.module.home.region.adapter
 * Author:Administrator
 * Fuction:
 * UpdateUser:
 * UpdateDate:
 */
public class RegionRecommendTypeSection extends StatelessSection {

    private Context mContext;
    private List<RegionHomeInfo.DataBean.ChildrenBean> mChildrenBeanList;

    public RegionRecommendTypeSection(Context context,List<RegionHomeInfo.DataBean.ChildrenBean> mChildrenBeanList) {
        super( R.layout.layout_region_recommend_type_section,R.layout.layout_home_recommend_empty);
        this.mContext=context;
        this.mChildrenBeanList=mChildrenBeanList;
    }

    @Override
    public int getContentItemsTotal() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {

        return new EmptyViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new TypeViewHolder(view);

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        if (holder instanceof TypeViewHolder)
        {
            try {
                TypeViewHolder holder1 = (TypeViewHolder) holder;
                GridLayoutManager manager = new GridLayoutManager(mContext, 4);
                holder1.recyclerView.setLayoutManager(manager);

                final List<String> ids=new ArrayList<>();
                final List<String> titles=new ArrayList<>();
                Observable.from(mChildrenBeanList)
                        .subscribe(new Action1<RegionHomeInfo.DataBean.ChildrenBean>() {
                            @Override
                            public void call(RegionHomeInfo.DataBean.ChildrenBean childrenBean) {
                                titles.add(childrenBean.name);
                                ids.add(childrenBean.logo);
                            }
                        });

                RegionRecommendTypeAdapter regionRecommendTypeAdapter = new RegionRecommendTypeAdapter(holder1.recyclerView, mContext, ids, titles);
                holder1.recyclerView.setAdapter(regionRecommendTypeAdapter);
            } catch (Exception e) {
                ToastUtil.ShortToast(mChildrenBeanList.get(0).reid + ":" + e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class TypeViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.recycler_view)
        RecyclerView recyclerView;

        TypeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
