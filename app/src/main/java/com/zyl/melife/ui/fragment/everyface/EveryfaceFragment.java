package com.zyl.melife.ui.fragment.everyface;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zyl.melife.R;
import com.zyl.melife.bean.EveryfaceTagBean;
import com.zyl.melife.logic.UIHelper;
import com.zyl.melife.ui.activity.AddEveryfaceTagActivity;
import com.zyl.melife.ui.activity.camera.TakePhotoActivity;
import com.zyl.melife.ui.adapter.EveryFaceMainAdapter;
import com.zyl.melife.ui.base.BaseFragment;
import com.zyl.melife.utils.ToastUtil;
import com.zyl.melife.view.EveryFaceContextMenu;
import com.zyl.melife.view.EveryFaceContextMenuManager;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.InjectView;

/**
 * everyface
 * @author  zhuyuliang
 * @email   zhuyuliang0@126.com
 * @created 2015-09-10
 */
public class EveryfaceFragment extends BaseFragment implements View.OnClickListener,EveryFaceContextMenu.OnEveryFaceContextMenuItemClickListener,EveryFaceMainAdapter.OnEveryFaceItemClickListener{

    @InjectView(R.id.notelist_recycleView)
    public RecyclerView recycleView;

    //Item适配
    EveryFaceMainAdapter efmadapter;
    LinearLayoutManager layoutManager;
    //数据列表
    List<EveryfaceTagBean> everyfaceTagBeans;

    private static EveryfaceFragment everyfaceListFragment = null;
    //单例
    public static EveryfaceFragment newInstance() {
        if (null == everyfaceListFragment) {
            //Bundle bundle = new Bundle();
            everyfaceListFragment = new EveryfaceFragment();
            //everyfaceListFragment.setArguments(bundle);
        }
        return everyfaceListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //Bundle bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.everyface_main_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();
        refreshList();
    }

    /**
     * 初始化Recycle
     */
    private void initRecycleView() {
        recycleView.setHasFixedSize(true);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity()){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;//设置预加载
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                EveryFaceContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
    }

    //刷新列表
    public void refreshList(){
        //loading
        everyfaceTagBeans = DataSupport.findAll(EveryfaceTagBean.class, true);
        efmadapter = new EveryFaceMainAdapter(getActivity(),everyfaceTagBeans);
        efmadapter.setOnItemClickListener(this);
        recycleView.setAdapter(efmadapter);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case R.id.action_add:
                UIHelper.RedirectToActivity(AddEveryfaceTagActivity.class, getActivity());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //more 监听
    @Override
    public void onSharePhotoClick(int everyfaceItem) {
        ToastUtil.showToast(getActivity(),"onSharePhotoClick");
        EveryFaceContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onDeleteEveryFaceClick(int everyfaceItem) {
        showDialogDeleteEveryface(everyfaceItem);
        EveryFaceContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCancelClick(int everyfaceItem) {
        EveryFaceContextMenuManager.getInstance().hideContextMenu();
    }

    //item 监听
    @Override
    public void onPictureClick(View v, int position) {
        ToastUtil.showToast(getActivity(), "onPictureClick");
    }

    @Override
    public void onCameraClick(View v, int position) {
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        startingLocation[0] += v.getWidth() / 2;
        UIHelper.RedirectToActivity(TakePhotoActivity.class,getActivity());
        //TakePhotoActivity.startCameraFromLocation(startingLocation, getActivity());
        //getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onMoreClick(View v, int position) {
        EveryFaceContextMenuManager.getInstance().toggleContextMenuFromView(v, position, this);
    }

    /**
     * 显示对话框
     */
    public void showDialogDeleteEveryface(final int position) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
        ad.setTitle(R.string.prompt);
        ad.setMessage("是否删除这个标签！");
        ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                DataSupport.delete(EveryfaceTagBean.class, everyfaceTagBeans.get(position).getId());
                everyfaceTagBeans.remove(position);
                efmadapter.notifyItemRemoved(position);
            }
        });
        ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }

}
