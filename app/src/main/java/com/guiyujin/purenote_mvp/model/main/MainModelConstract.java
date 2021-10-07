package com.guiyujin.purenote_mvp.model.main;

import android.content.Context;

import com.guiyujin.purenote_mvp.base.mvp.BaseModel;
import com.guiyujin.purenote_mvp.base.mvp.BaseView;
import com.guiyujin.purenote_mvp.base.mvp.IPresenter;
import com.guiyujin.purenote_mvp.base.ICallBack;
import com.guiyujin.purenote_mvp.room.Note;

import java.util.List;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.main
 * @ClassName: MainModelConstract
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 14:23
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 14:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface MainModelConstract {
    interface MainCallBack extends ICallBack{
        void onSuccess(List<Note> noteList);
    }

    interface Model extends BaseModel {
        void search(List<Note> note, String text, Context context, MainCallBack mainCallBack);
        void delete(Note note, Context context);
    }

    interface View extends BaseView {
        void onSearchSuccess(List<Note> note);
        void onDelete();
        void onFailed();
    }

    interface presenter extends IPresenter<MainModelConstract.View, MainModelConstract.Model> {
        void search(List<Note> note, String text, Context context);
        void delete(Note note, Context context);
        void clear();
    }
}
