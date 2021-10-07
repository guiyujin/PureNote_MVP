package com.guiyujin.purenote_mvp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.widget.SwitchCompat;

import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.base.BaseActivity;

public class SettingsActivity extends BaseActivity {
    private ListView lv;
    private SwitchCompat finger;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initParams(Bundle params) {

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

        sp = getSharedPreferences("settings", MODE_PRIVATE);
        editor = sp.edit();
        finger = (SwitchCompat) find(R.id.fingerprint);
        finger.setChecked(sp.getBoolean("isFinger",false));
        initToolBar(R.id.toolbar_settings, R.menu.menu_setting);
    }

    @Override
    public void initListener() {
        showBack(R.drawable.ic_close);

        finger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("isFinger", isChecked);
                editor.commit();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(int id) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

}