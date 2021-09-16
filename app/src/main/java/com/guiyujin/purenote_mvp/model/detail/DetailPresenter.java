package com.guiyujin.purenote_mvp.model.detail;

import android.content.Context;
import android.content.Intent;

import com.guiyujin.purenote_mvp.base.BasePresenter;
import com.guiyujin.purenote_mvp.room.Note;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.detail
 * @ClassName: DetailPresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 10:59
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 10:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DetailPresenter extends BasePresenter<DetailConstract.View, DetailConstract.Model> implements DetailConstract.presenter{
    @Override
    public void delete(Note note, Context context) {
        mModel.delete(note, context);
        mView.onSuccess();
    }

    @Override
    public void update(Note note, Context context) {
        mModel.update(note, context);
        mView.onSuccess();
    }

    @Override
    public void share(Note note) {
        mModel.share(note, new DetailConstract.DetailCallBack() {
            @Override
            public void onSuccess(Intent intent) {
                mView.share(intent);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void addAlarm(Context context, Note note) {
        mModel.addAlarm(context, note);
        mView.onSuccess();
    }

}
