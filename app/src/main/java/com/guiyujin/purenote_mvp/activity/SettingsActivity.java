package com.guiyujin.purenote_mvp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import com.guiyujin.purenote_mvp.FileUtils;
import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.base.BaseActivity;
import com.guiyujin.purenote_mvp.skinloader.listener.ILoaderListener;
import com.guiyujin.purenote_mvp.skinloader.load.SkinManager;

import java.io.File;

public class SettingsActivity extends BaseActivity {
    private static String TAG = "ChangeSkinFragment";
    private ListView lv;
    private SwitchCompat night, finger;
    private static String SKIN_DIR;
    private static String SKIN_BLACK_NAME = "black.skin";
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
        night = find(R.id.nightMode);
        night.setChecked(sp.getBoolean("isNightMode",false));
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

            }
        });

        SKIN_DIR = FileUtils.getSkinDirPath(SettingsActivity.this);
        night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String skinFullName = SKIN_DIR + File.separator + "skin_black.skin";
                    FileUtils.moveRawToDir(SettingsActivity.this, "skin_black.skin", skinFullName);
                    File skin = new File(skinFullName);
                    if (!skin.exists()) {
                        Toast.makeText(SettingsActivity.this, "请检查" + skinFullName + "是否存在", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SkinManager.getInstance().load(skin.getAbsolutePath(),
                            new ILoaderListener() {
                                @Override
                                public void onStart() {
                                    Log.e(TAG, "loadSkinStart");
                                }

                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onSuccess() {
                                    Log.i(TAG, "loadSkinSuccess");
                                    editor.putBoolean("isNightMode", true);
                                    Toast.makeText(SettingsActivity.this, "切换成功", Toast.LENGTH_SHORT).show();
                                    getToolbar().setTitleTextColor(R.color.white);
                                }

                                @Override
                                public void onFailed() {
                                    Log.i(TAG, "loadSkinFail");
                                    Toast.makeText(SettingsActivity.this, "切换失败", Toast.LENGTH_SHORT).show();
                                }
                            });

                }else {
                    editor.putBoolean("isNightMode", false);
                    SkinManager.getInstance().restoreDefaultTheme();
                }
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

    @Override
    protected void onPause() {
        super.onPause();
        editor.commit();
    }
}