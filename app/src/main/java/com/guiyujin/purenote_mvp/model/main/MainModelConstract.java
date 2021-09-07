package com.guiyujin.purenote_mvp.model.main;

import android.content.Context;

import com.guiyujin.purenote_mvp.base.BaseModel;
import com.guiyujin.purenote_mvp.base.BaseView;
import com.guiyujin.purenote_mvp.base.IPresenter;
import com.guiyujin.purenote_mvp.callback.ICallBack;
import com.guiyujin.purenote_mvp.model.addcontent.AddContentConstract;
import com.guiyujin.purenote_mvp.room.Note;

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
    interface Model extends BaseModel {
        void search(Note note, Context context, ICallBack iCallBack);
        void delete(Note note, Context context);
    }

    interface View extends BaseView {
        void onSuccess();
        void onFailed();
        void clear();
    }

    interface presenter extends IPresenter<MainModelConstract.View, MainModelConstract.Model> {
        void search(Note note, Context context);
        void delete(Note note, Context context);
        void clear();
    }
}
