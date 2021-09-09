package com.guiyujin.purenote_mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.base.BaseActivity;
import com.guiyujin.purenote_mvp.base.BaseModel;
import com.guiyujin.purenote_mvp.base.BasePresenter;

public class SettingsActivity extends BaseActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected BaseModel initModel() {
        return null;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_settings;
    }

    @Override
    public void initView(View view) {

        initToolBar(R.id.toolbar_settings, R.menu.menu_setting);
    }

    @Override
    public void initListener() {
        showBack(R.drawable.ic_close);
    }

    @Override
    public void widgetClick(int id) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void checkPermission() {

    }
}