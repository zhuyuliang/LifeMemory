package com.zyl.melife.ui.view.main.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zyl.melife.R;
import com.zyl.melife.logic.EveryfaceLogic;
import com.zyl.melife.ui.presenter.main.impl.MainPresenterImpl;
import com.zyl.melife.ui.view.base.BaseAppCompatActivity;
import com.zyl.melife.ui.view.everyface.impl.EveryfaceFragment;
import com.zyl.melife.ui.view.main.IMainView;
import com.zyl.melife.utils.DoubleClickExitHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 项目首页
 * @author zhuyuliang
 * @created 2015-09-10
 */
public class MainActivity extends BaseAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,IMainView {

    private final static String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;

    //菜单视图
    @InjectView(R.id.nav_view)
    NavigationView navigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    //退出程序的工具类
    private DoubleClickExitHelper mDoubleClickExitHelper;

    //侧滑菜单的内容处理
    private View headerView;
    private TextView txt_user;
    private CircleImageView img_avatar;

    //framgnet
    private FragmentManager fm;
    EveryfaceFragment everyfaceframgent;

    //fragment事件
    private boolean everyfaceAddEvent;

    private MainPresenterImpl mMainPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initToolBar();
        initDrawListView();
        initNavigationView();
        initMainFragment();
    }

    /**
     * 一般初始化
     */
    @Override
    public void init(){
        ButterKnife.inject(this);
        mMainPresenterImpl = new MainPresenterImpl();
        mMainPresenterImpl.attachView(this);
        EventBus.getDefault().register(this);
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
    }

    /**
     * 初始化ToolBar
     */
    @Override
    public void initToolBar() {
        toolbar.setTitle(getResources().getString(R.string.toolbar_title));
        setSupportActionBar(toolbar);//添加toolbar
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    /**
     * 初始化侧滑菜单
     */
    @Override
    public void initDrawListView() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);//添加切换监听

    }

    /**
     *  侧滑菜单的内容处理
     */
    @Override
    public void initNavigationView(){
        img_avatar = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.avatar);
        txt_user = (TextView)navigationView.getHeaderView(0).findViewById(R.id.headerText);
        headerView = (View)navigationView.findViewById(R.id.nav_header);

        navigationView.setNavigationItemSelectedListener(this);
        //headerView.setOnClickListener(this);暂时不支持用户

        //img_avatar.setImageResource(R.mipmap.ic_nav_header);
        img_avatar.setVisibility(View.GONE);
        //txt_user.setText(getResources().getString(R.string.nav_username_yun_propert));
        txt_user.setText(getResources().getString(R.string.app_name));
        txt_user.setTextSize(getResources().getDimension(R.dimen.text_size_middle));
    }

    /**
     * 初始化首页的Fragment
     * @return
     */
    @Override
    public void initMainFragment(){
        fm = getSupportFragmentManager();
        setUpTab(R.id.everyface);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(navigationView)) {
                mDrawerLayout.closeDrawer(navigationView);
                return true;
            }
            return mDoubleClickExitHelper.onKeyDown(keyCode, event);
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (mDrawerLayout.isDrawerOpen(navigationView)) {
                mDrawerLayout.closeDrawer(navigationView);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        //可以处理在其他点击事件之前处理的事情
        //和点击事件的处理
        switch (v.getId()){
            case R.id.nav_header:
                showToast("nav_header");
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawers();
        setUpTab(menuItem.getItemId());
        return true;
    }
    public void setUpTab(int id) {
        switch (id) {
            case R.id.everyface:
                toolbar.setTitle(getResources().getString(R.string.everyface));
                everyfaceframgent = EveryfaceFragment.newInstance();
                fm.beginTransaction().replace(R.id.content_fl, everyfaceframgent, null).commit();
                break;
            case R.id.nav_setting:
                showToast("setting");
                return;
            case R.id.nav_about:
                showToast("about");
                break;
        }
    }

    /**
     * eventbus接收事件
     * @param event
     */
    public void onEvent(Integer event) {
        switch (event) {
            case EveryfaceLogic.EVERYFACE_ADD_EVENT:
                everyfaceAddEvent = true;
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (everyfaceAddEvent) {
            everyfaceframgent.refreshList();
            everyfaceAddEvent = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
