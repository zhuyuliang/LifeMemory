package com.zyl.melife.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.zyl.melife.R;
import com.zyl.melife.bean.EveryfaceTagBean;
import com.zyl.melife.logic.EveryfaceLogic;
import com.zyl.melife.ui.base.ToolbarActivity;
import com.zyl.melife.utils.DateUtil;
import com.zyl.melife.utils.ToastUtil;
import com.zyl.melife.view.DialogHelper;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * 添加EveryFace标签的activity
 * @author zhuyuliang
 * @created 2015-09-15
 */
public class AddEveryfaceTagActivity extends ToolbarActivity{

    private final static String TAG = AddEveryfaceTagActivity.class.getSimpleName();

    @InjectView(R.id.tv_edit_tag)
    MaterialEditText tv_edit_tag;
    @InjectView(R.id.tv_edit_desc)
    MaterialEditText tv_edit_desc;
    @InjectView(R.id.tv_create_time_line)
    TextView tv_create_time_line;

    private long timesamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 一般初始化
     */
    private void init(){
        //监听
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_done:
                        doneEdit();
                        break;
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提示是否保存--判断重要信息是否为空
                if(tv_edit_tag.getText().toString().length() != 0){
                    showBackDialog();
                }else{
                    finish();
                }
            }
        });

        timesamp = System.currentTimeMillis();
        tv_create_time_line.setText(getResources().getString(R.string.create_time_line_default) + " " + DateUtil.timeSampToStr(timesamp));
    }

    @Override
    protected String getToolbarTitle() {
        return getResources().getString(R.string.add_everyface_tag_title_str);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.add_everyface_tag_activity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //提示是否保存--判断重要信息是否为空
            if(tv_edit_tag.getText().toString().length() != 0){
                showBackDialog();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 显示是否退出编辑
     */
    public void showBackDialog(){
        AlertDialog.Builder ad = new AlertDialog.Builder(AddEveryfaceTagActivity.this);
        ad.setTitle(R.string.prompt);
        ad.setMessage(R.string.add_edit_back_prompt_str);
        ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        ad.show();
    }

    /**
     * 完成编辑
     */
    public void doneEdit(){
        if(tv_edit_tag.getText().toString().length() == 0){
            //提示
            ToastUtil.showToast(this,R.string.input_null_str);
            return;
        }
        if(tv_edit_tag.getText().toString().length() > 20){
            //提示
            ToastUtil.showToast(this,R.string.input_long_str);
            return;
        }
        //查询是否有同名的数据
        List<EveryfaceTagBean> everyfaceTagBeans = (DataSupport.where("name=?", tv_edit_tag.getText().toString()).find(EveryfaceTagBean.class, true));
        if (everyfaceTagBeans.size() == 0) {
            int lastId;
            if (DataSupport.findLast(EveryfaceTagBean.class) == null) {
                lastId = 0;
            } else {
                lastId = DataSupport.findLast(EveryfaceTagBean.class).getId();
            }
            int id = lastId + 1;
            //保存数据库
            EveryfaceTagBean everyfaceBean = new EveryfaceTagBean();
            everyfaceBean.setId(id);
            everyfaceBean.setName(tv_edit_tag.getText().toString());
            everyfaceBean.setDesc(tv_edit_desc.getText().toString());
            everyfaceBean.setCreatedtime(timesamp + "");
            everyfaceBean.save();
        } else {
            ToastUtil.showToast(this,R.string.repeat_create_str);
            return;
        }
        //传递事件
        EventBus.getDefault().post(EveryfaceLogic.EVERYFACE_ADD_EVENT);
        finish();
    }

}
