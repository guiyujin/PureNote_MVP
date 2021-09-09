package com.guiyujin.purenote_mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.Util;
import com.guiyujin.purenote_mvp.base.BaseActivity;
import com.guiyujin.purenote_mvp.model.detail.DetailConstract;
import com.guiyujin.purenote_mvp.model.detail.DetailModelImpl;
import com.guiyujin.purenote_mvp.model.detail.DetailPresenter;
import com.guiyujin.purenote_mvp.room.Note;

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
public class DetailActivity extends BaseActivity<DetailPresenter, DetailModelImpl> implements DetailConstract.View {
    private EditText editText;
    private String content;
    private int _id;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this, model);
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
    public void initParms(Bundle parms) {
        content = parms.getString("content");
        _id = parms.getInt("_id");
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
        editText = findViewById(R.id.d_text);

        editText.setText(content);
        initToolBar(R.id.toolbar_detail, R.menu.menu_detail);
    }

    @Override
    public void initListener() {
        showBack(R.drawable.ic_close);
    }

    @Override
    public void widgetClick(int id) {
        Note note = new Note(editText.getText().toString(), Util.getTime());
        note.setId(_id);
        switch (id) {
            case R.id.action_save:
                presenter.update(note, this);
                finish();
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
    public void checkPermission() {

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
