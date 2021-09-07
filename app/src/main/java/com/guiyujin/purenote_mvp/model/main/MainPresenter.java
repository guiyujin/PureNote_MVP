package com.guiyujin.purenote_mvp.model.main;

import android.content.Context;

import com.guiyujin.purenote_mvp.base.BasePresenter;
import com.guiyujin.purenote_mvp.model.addcontent.AddContentConstract;
import com.guiyujin.purenote_mvp.room.Note;

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
    public void search(Note note, Context context) {

    }

    @Override
    public void delete(Note note, Context context) {

    }

    @Override
    public void clear() {

    }
}
