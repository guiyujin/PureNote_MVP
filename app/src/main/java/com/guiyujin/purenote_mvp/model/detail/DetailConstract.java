package com.guiyujin.purenote_mvp.model.detail;

import android.content.Context;
import android.content.Intent;

import com.guiyujin.purenote_mvp.base.BaseModel;
import com.guiyujin.purenote_mvp.base.BaseView;
import com.guiyujin.purenote_mvp.base.IPresenter;
import com.guiyujin.purenote_mvp.base.ICallBack;
import com.guiyujin.purenote_mvp.room.Note;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.detail
 * @ClassName: DetailContent
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 10:06
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 10:06
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface DetailConstract {
    interface DetailCallBack extends ICallBack{
        void onSuccess(Intent intent);
    }

    interface Model extends BaseModel {
        void delete(Note note, Context context);
        void update(Note note, Context context);
        void share(Note note, DetailCallBack detailCallBack);
        void addAlarm(Context context, Note note);
    }

    interface View extends BaseView {
        void onSuccess();
        void share(Intent intent);
    }

    interface presenter extends IPresenter<DetailConstract.View, DetailConstract.Model> {
        void delete(Note note, Context context);
        void update(Note note, Context context);
        void share(Note note);
        void addAlarm(Context context, Note note);
    }
}
