package com.guiyujin.purenote_mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.guiyujin.purenote_mvp.AESCrypt;
import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.Util;
import com.guiyujin.purenote_mvp.base.mvp.BaseMVPActivity;
import com.guiyujin.purenote_mvp.model.detail.DetailContract;
import com.guiyujin.purenote_mvp.model.detail.DetailModelImpl;
import com.guiyujin.purenote_mvp.model.detail.DetailPresenter;
import com.guiyujin.purenote_mvp.room.Note;

import java.security.GeneralSecurityException;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.activity
 * @ClassName: DetailActivity
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 10:55
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 10:55
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DetailActivity extends BaseMVPActivity<DetailPresenter, DetailModelImpl> implements DetailContract.View {
    private EditText et_detail_text, et_detail_title;
    private String text_title, text_content;
    private String title, content;
    private int _id;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        presenter.attach(this, model);
    }

    @Override
    protected void initParams(Bundle params) {
        title = params.getString("title");
        content = params.getString("content");
        _id = params.getInt("_id");
    }

    @Override
    protected DetailPresenter initPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected DetailModelImpl initModel() {
        return new DetailModelImpl();
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView(View view) {
        et_detail_title = find(R.id.d_title);
        et_detail_text = findViewById(R.id.d_text);

        et_detail_title.setText(title);
        try {
            et_detail_text.setText(AESCrypt.decrypt("mypassword", content));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        initToolBar(R.id.toolbar_detail);
        setMenuRes(R.menu.menu_detail);
    }

    @Override
    public void initListener() {
        showBack(R.drawable.ic_close);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(int id) {
        text_title = et_detail_title.getText().toString();
        text_content = et_detail_text.getText().toString();
        String encryptText = null;
        try {
            encryptText = AESCrypt.encrypt("mypassword", text_content);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        Note note = new Note(text_title, encryptText, Util.getTime());
        note.setId(_id);
        switch (id) {
            case R.id.action_save:
                presenter.update(note, this);
                finish();
                break;
            case R.id.action_edit:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                getmMenu().findItem(R.id.action_save).setVisible(true);
                getmMenu().findItem(R.id.action_edit).setVisible(false);
                break;
            case R.id.action_delete:
                presenter.delete(note, this);
                finish();
                break;
            case R.id.action_share:
                presenter.share(note);
                break;
            case R.id.action_add_alarm:
                presenter.addAlarm(this, note);
                break;
            case R.id.action_text_size:
                showToast("开发中");
                break;
            case R.id.action_box:
                showToast("开发中");
                break;
            default:
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    public void share(Intent intent) {
        startActivity(Intent.createChooser(intent, "分享到"));
    }

}
