package com.guiyujin.purenote_mvp.model.main;

import android.content.Context;

import com.guiyujin.purenote_mvp.base.mvp.BasePresenter;
import com.guiyujin.purenote_mvp.room.Note;

import java.util.List;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.main
 * @ClassName: MainPresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 14:24
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 14:24
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainPresenter  extends BasePresenter<MainModelConstract.View, MainModelConstract.Model> implements MainModelConstract.presenter{

    @Override
    public void search(List<Note> note, String text, Context context) {
        mModel.search(note, text, context, new MainModelConstract.MainCallBack() {
            @Override
            public void onSuccess(List<Note> noteList) {
                mView.onSearchSuccess(noteList);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void delete(Note note, Context context) {
        mModel.delete(note, context);
        mView.onDelete();
    }

    @Override
    public void clear() {

    }
}
