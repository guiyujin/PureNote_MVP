package com.guiyujin.purenote_mvp.model.addcontent;

import android.content.Context;

import com.guiyujin.purenote_mvp.base.BasePresenter;
import com.guiyujin.purenote_mvp.room.Note;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.presenter
 * @ClassName: AddcontentPresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 9:21
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 9:21
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AddcontentPresenter extends BasePresenter<AddContentConstract.View, AddContentConstract.Model> implements AddContentConstract.presenter{
    @Override
    public void save(Note note, Context context) {
        mModel.save(note, context, new AddContentConstract.AddCallBack() {
            @Override
            public void onSuccess() {
                mView.onSuccess();
            }

            @Override
            public void onFailed() {
                mView.onFailed();
            }
        });
    }

    @Override
    public void clear() {
        mView.clear();
    }
}
