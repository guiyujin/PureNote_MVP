package com.guiyujin.purenote_mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.guiyujin.purenote_mvp.R;
import com.guiyujin.purenote_mvp.Util;
import com.guiyujin.purenote_mvp.base.BaseActivity;
import com.guiyujin.purenote_mvp.model.addcontent.AddContentConstract;
import com.guiyujin.purenote_mvp.model.addcontent.AddcontentModelImpl;
import com.guiyujin.purenote_mvp.model.addcontent.AddcontentPresenter;
import com.guiyujin.purenote_mvp.room.Note;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp
 * @ClassName: AddContentActivity
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 9:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 9:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AddContentActivity extends BaseActivity<AddcontentPresenter, AddcontentModelImpl> implements AddContentConstract.View {
    private EditText et_text;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this, model);
    }

    @Override
    protected AddcontentPresenter initPresenter() {
        return new AddcontentPresenter();
    }

    @Override
    protected AddcontentModelImpl initModel() {
        return new AddcontentModelImpl();
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
        return R.layout.activity_add_content;
    }

    @Override
    public void initView(View view) {
        et_text = find(R.id.et_text);

        initToolBar(R.id.toolbar_add, R.menu.menu_add);
    }

    @Override
    public void initListener() {
        showBack(R.drawable.ic_close);
    }

    @Override
    public void widgetClick(int id) {
        switch (id){
            case R.id.action_save:
                Note note = new Note(et_text.getText().toString(), Util.getTime());
                presenter.save(note,this);
                finish();
                break;
            case R.id.action_clear:
                presenter.clear();
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
        showToast("保存成功");
        finish();
    }

    @Override
    public void onFailed() {
        showToast("保存失败");
    }

    @Override
    public void clear() {
        et_text.setText("");
    }
}
