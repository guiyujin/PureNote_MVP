package com.guiyujin.purenote_mvp.model.addcontent;

import android.content.Context;

import com.guiyujin.purenote_mvp.base.mvp.BaseModel;
import com.guiyujin.purenote_mvp.base.mvp.BaseView;
import com.guiyujin.purenote_mvp.base.mvp.IPresenter;
import com.guiyujin.purenote_mvp.base.ICallBack;
import com.guiyujin.purenote_mvp.room.Note;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.constract
 * @ClassName: AddContentConstract
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 9:16
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 9:16
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface AddContentContract {
    interface AddCallBack extends ICallBack{
        void onSuccess();
    }

    interface Model extends BaseModel {
        void save(Note note, Context context, AddCallBack addCallBack);
    }

    interface View extends BaseView {
        void onSuccess();
        void onFailed();
        void clear();
    }

    interface presenter{
        void save(Note note, Context context);
        void clear();
    }
}
