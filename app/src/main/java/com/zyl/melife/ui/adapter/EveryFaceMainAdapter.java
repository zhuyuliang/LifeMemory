package com.zyl.melife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyl.melife.R;
import com.zyl.melife.bean.EveryfaceTagBean;
import com.zyl.melife.view.SquaredFrameLayout;

import java.util.List;

/**
 * everyface mian item adapter
 * @author  zhuyuliang
 * @email   zhuyuliang0@126.com
 * @created 2015-09-11
 */
//继承自 RecyclerView.Adapter
public class EveryFaceMainAdapter extends RecyclerView.Adapter<EveryFaceMainAdapter.ViewHolder> {

    public Context mCtx;
    public List<EveryfaceTagBean> data;

    //监听接口
    private OnEveryFaceItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnEveryFaceItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 构造方法
     * @param context
     */
    public EveryFaceMainAdapter(Context context,List<EveryfaceTagBean> data){
        mCtx = context;
        this.data = data;
    }

    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.everyface_main_item, viewGroup, false);
        return new ViewHolder(v);
    }

    //将数据绑定到子View，会自动复用View
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int i) {
        //处理内容
        viewHolder.tv_everyface_tag_name.setText(data.get(i).getName());
        String desc_str = data.get(i).getDesc();
        if(desc_str.equals("")||desc_str.equals(null)) {
            viewHolder.tv_tag_desc.setVisibility(View.GONE);
        }else{
            viewHolder.tv_tag_desc.setText("  "+desc_str);
        }
        viewHolder.everyface_cover_iv.setBackgroundResource(R.mipmap.ic_default_everyface_item);
        //监听
        viewHolder.vImageRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) mOnItemClickListener.onPictureClick(v, i);
            }
        });
        viewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) mOnItemClickListener.onMoreClick(v, i);
            }
        });
        viewHolder.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) mOnItemClickListener.onCameraClick(v, i);
            }
        });
    }

    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return data.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        ImageView everyface_cover_iv;
        SquaredFrameLayout vImageRoot;
        TextView tv_everyface_tag_name;
        TextView tv_tag_desc;
        ImageButton btnCamera;
        ImageButton btnMore;
        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            everyface_cover_iv = (ImageView) itemView.findViewById(R.id.ivEveryfaceCenter);
            vImageRoot = (SquaredFrameLayout) itemView.findViewById(R.id.vImageRoot);
            tv_everyface_tag_name = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_tag_desc = (TextView) itemView.findViewById(R.id.tv_tag_desc);
            btnMore = (ImageButton) itemView.findViewById(R.id.btnMore);
            btnCamera = (ImageButton) itemView.findViewById(R.id.btnCamera);
        }
    }

    public interface OnEveryFaceItemClickListener {
        public void onPictureClick(View v,int position);
        public void onCameraClick(View v, int position);
        public void onMoreClick(View v, int position);
    }

}