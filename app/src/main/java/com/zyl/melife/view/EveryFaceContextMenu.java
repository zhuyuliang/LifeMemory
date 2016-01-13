package com.zyl.melife.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zyl.melife.R;
import com.zyl.melife.utils.ViewUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by froger_mcs on 15.12.14.
 */
public class EveryFaceContextMenu extends LinearLayout {

    private static final int CONTEXT_MENU_WIDTH = ViewUtil.dpToPx(240);

    private int everyfaceItem = -1;

    private OnEveryFaceContextMenuItemClickListener onItemClickListener;

    public EveryFaceContextMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_context_menu, this, true);
        setBackgroundResource(R.drawable.bg_container_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void bindToItem(int feedItem) {
        this.everyfaceItem = feedItem;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.inject(this);
    }

    public void dismiss() {
        ((ViewGroup) getParent()).removeView(EveryFaceContextMenu.this);
    }

    @OnClick(R.id.btnSharePhoto)
     public void onSharePhotoClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onSharePhotoClick(everyfaceItem);
        }
    }

    @OnClick(R.id.btnDelete)
    public void onDeleteClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onDeleteEveryFaceClick(everyfaceItem);
        }
    }

    @OnClick(R.id.btnCancel)
    public void onCancelClick() {
        if (onItemClickListener != null) {
            onItemClickListener.onCancelClick(everyfaceItem);
        }
    }

    public void setOnEveryFaceMenuItemClickListener(OnEveryFaceContextMenuItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnEveryFaceContextMenuItemClickListener {
        public void onSharePhotoClick(int everyfaceItem);
        public void onDeleteEveryFaceClick(int everyfaceItem);
        public void onCancelClick(int everyfaceItem);
    }
}