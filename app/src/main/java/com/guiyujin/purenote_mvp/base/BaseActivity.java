package com.guiyujin.purenote_mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Method;

/**
 * @ProjectName: AndroidLibBase
 * @Package: com.guiyujin.android_lib_base.base
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/10/5 10:02
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/10/5 10:02
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    /** 当前Activity渲染的视图View **/
    private View mContextView = null;
    /** 当前Activity的ToolBar **/
    private Toolbar mToolbar;
    /** ToolBar的popupmenu菜单资源 **/
    private int menu_res;
    private Menu mMenu;
    protected FragmentManager mFragmentManager;
    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        initParams(bundle);
        mIntent = new Intent();
        mFragmentManager = getSupportFragmentManager();
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this)
                    .inflate(bindLayout(), null);
        } else
            mContextView = mView;
        setContentView(bindLayout());
        initView(mContextView);
        initListener();
        initData();
        doBusiness(this);
    }


    /**
     * [初始化参数]
     *
     * @param params
     */
    protected abstract void initParams(Bundle params);

    /**
     * [绑定视图]
     *
     * @return
     */
    protected abstract View bindView();

    /**
     * [绑定布局]
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * [绑定控件]
     *
     * @param resId
     *
     * @return
     */
    protected <T extends View> T find(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * [初始化事件监听]
     */
    protected abstract void initListener();

    /**
     * [初始化数据]
     */
    protected abstract void initData();

    /**
     * [业务操作]
     *
     * @param mContext
     */
    protected abstract void doBusiness(Context mContext);

    /** View点击 **/
    protected abstract void widgetClick(int id);

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v.getId());
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        return System.currentTimeMillis() - lastClick > 1000;
    }

    //TODO
    // 以下区域封装通用工具方法

    /**
     * [简化Toast]
     * @param msg
     */
    protected void showToast(Object msg){
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    protected void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    protected void startActivity(Class<?> clz, Bundle bundle) {
        mIntent.setClass(this, clz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        startActivity(mIntent);
    }

    /**
     * [携带数据且有返回的页面跳转]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    protected void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        mIntent.setClass(this, cls);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        startActivityForResult(mIntent, requestCode);
    }

    /**
     * [Fragment切换]
     *
     * @param contentRes
     * @param from
     * @param to
     */
    protected void switchFragment(int contentRes, Fragment from, Fragment to) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!to.isAdded()) {	// 先判断是否被add过
            transaction.hide(from).add(contentRes, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }

    /**
     * 封装Toolbar
     */
    protected void initToolBar(int toolbar_res){
        mToolbar = find(toolbar_res);
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * 封装可设置标题的Toolbar
     */
    protected void initToolBar(int toolbar_res, String title){
        initToolBar(toolbar_res);
        setToolbarTitle(title);
    }

    /**
     * 封装可设置Menu的Toolbar
     */
    protected void initToolBar(int toolbar_res, int menu_res){
        initToolBar(toolbar_res);
        setMenuRes(menu_res);
    }

    protected void setToolbarTitle(CharSequence title){
        getToolbar().setTitle(title);
        setSupportActionBar(getToolbar());
    }

    protected void setToolbarSubTitle(CharSequence title) {
        getToolbar().setSubtitle(title);
        setSupportActionBar(getToolbar());
    }

    protected Toolbar getToolbar(){
        return mToolbar;
    }

    /**
     * 封装toolbar返回键功能
     */
    protected void showBack(int back_resId){
        getToolbar().setNavigationIcon(back_resId);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public int getMenu_res() {
        return menu_res;
    }

    public void setMenuRes(int menu_res){
        this.menu_res = menu_res;
    }

    public Menu getmMenu() {
        return mMenu;
    }
    // TODO
    // 必不可少的固定代码

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu_res != 0){
            getMenuInflater().inflate(menu_res, menu);
        }
        this.mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        widgetClick(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
