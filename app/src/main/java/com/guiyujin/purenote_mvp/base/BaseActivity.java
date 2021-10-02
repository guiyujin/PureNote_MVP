package com.guiyujin.purenote_mvp.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.guiyujin.purenote_mvp.R;

import java.lang.reflect.Method;

/**
 * @ProjectName: MVPDemo
 * @Package: com.guiyujin.mvpdemo.base
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/6 13:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/6 13:36
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements View.OnClickListener{
    /** 是否沉浸状态栏 **/
    private boolean isSetStatusBar = false;
    /** 是否允许全屏 **/
    private boolean mAllowFullScreen = false;
    /** 是否禁止旋转屏幕 **/
    private boolean isAllowScreenRoate = false;
    /** 当前Activity渲染的视图View **/
    private View mContextView = null;
    private Toolbar mToolbar;
    private int menu_res;
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    protected T presenter;
    protected E model;
    protected FragmentManager fragmentManager;

    public Menu getMenu() {
        return menu;
    }

    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();
        presenter = initPresenter();
        model = initModel();
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this)
                    .inflate(bindLayout(), null);
        } else
            mContextView = mView;
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }
        setContentView(mContextView);
//        if (savedInstanceState == null) {
//            mContextView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                @Override
//                public boolean onPreDraw() {
//                    mContextView.getViewTreeObserver().removeOnPreDrawListener(this);
//                    startRootAnimation();
//                    return true;
//                }
//            });
//        }
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        initView(mContextView);
        initListener();
        doBusiness(this);
    }

//    private void startRootAnimation() {
//        mContextView.setScaleY(0.1f);
//        mContextView.setPivotY(mContextView.getY() + mContextView.getHeight() / 2);
//        mContextView.animate()
//                .scaleY(1)
//                .setDuration(500)
//                .setInterpolator(new AccelerateInterpolator())
//                .start();
//    }

    protected abstract T initPresenter();
    protected abstract E initModel();

    /**
     * [初始化参数]
     *
     * @param parms
     */
    public abstract void initParms(Bundle parms);

    /**
     * [绑定视图]
     *
     * @return
     */
    public abstract View bindView();

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
    public abstract void initView(View view);

    /**
     * [绑定控件]
     *
     * @param resId
     *
     * @return
     */
    protected    <T extends View> T find(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * [初始化事件]
     */
    public abstract void initListener();

    /** View点击 **/
    public abstract void widgetClick(int id);

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v.getId());
    }

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this,clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void switchFragment(int contentRes, Fragment from, Fragment to) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!to.isAdded()) {	// 先判断是否被add过
            transaction.hide(from).add(contentRes, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }


    /**
     * [简化Toast]
     * @param msg
     */
    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public abstract void checkPermission();

    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 封装Toolbar
     */
    public void initToolBar(int toolbar_res){
        mToolbar = find(toolbar_res);
        this.menu_res = menu_res;
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null){
            mToolbarTitle.setText(getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public int getMenu_res() {
        return menu_res;
    }

    public void setMenuRes(int menu_res){
        this.menu_res = menu_res;
    }

    /**
     * 封装Toolbar
     */
    public void initToolBar(int toolbar_res, int menu_res){
        mToolbar = find(toolbar_res);
        this.menu_res = menu_res;
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null){
            mToolbarTitle.setText(getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public TextView getToolbarTitle(){
        return mToolbarTitle;
    }

    public TextView getToolbarSubTitle(){
        return mToolbarSubTitle;
    }

    public void setToolbarTitle(CharSequence title){
        if (mToolbarTitle != null){
            mToolbarTitle.setText(title);
        }else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    public void setToolbarSubTitle(CharSequence title) {
        if (mToolbarSubTitle != null){
            mToolbarSubTitle.setText(title);
        }else {
            getToolbar().setSubtitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    public Toolbar getToolbar(){
        return mToolbar;
    }

    //封装toolbar返回键功能，在一般的activity中可以重写
    public void showBack(int id){
        getToolbar().setNavigationIcon(id);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_res, menu);
        this.menu = menu;
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
