package com.guiyujin.purenote_mvp.skinloader.base;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import com.guiyujin.purenote_mvp.skinloader.attr.DynamicAttr;
import com.guiyujin.purenote_mvp.skinloader.listener.IDynamicNewView;
import com.guiyujin.purenote_mvp.skinloader.listener.ISkinUpdate;
import com.guiyujin.purenote_mvp.skinloader.load.SkinInflaterFactory;
import com.guiyujin.purenote_mvp.skinloader.load.SkinManager;
import com.guiyujin.purenote_mvp.skinloader.statusbar.StatusBarBackground;

import java.util.List;



/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:24
 * <p>
 * 需要实现换肤功能的Activity就需要继承于这个Activity
 */
public class SkinBaseActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {

    // 当前Activity是否需要响应皮肤更改需求
    private boolean isResponseOnSkinChanging = true;
    private SkinInflaterFactory mSkinInflaterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory();
        //getLayoutInflater().cloneInContext(this).setFactory(mSkinInflaterFactory);
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
//        changeStatusColor();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        mSkinInflaterFactory.clean();
    }

    @Override
    public void onThemeUpdate() {
        Log.i("SkinBaseActivity", "onThemeUpdate");
        if (!isResponseOnSkinChanging) {
            return;
        }
        mSkinInflaterFactory.applySkin();
//        changeStatusColor();

//        //设置window的背景色
//        Drawable drawable = new ColorDrawable(SkinManager.getInstance().getColorPrimaryDark());
//        getWindow().setBackgroundDrawable(drawable);
    }

    public void changeStatusColor() {
        //如果当前的Android系统版本大于4.4则更改状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i("SkinBaseActivity", "changeStatus");
            int color = SkinManager.getInstance().getColorPrimaryDark();
            StatusBarBackground statusBarBackground = new StatusBarBackground(
                    this, color);
            if (color != -1)
                statusBarBackground.setStatusBarbackColor();
        }
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    final protected void enableResponseOnSkinChanging(boolean enable) {
        isResponseOnSkinChanging = enable;
    }
}
