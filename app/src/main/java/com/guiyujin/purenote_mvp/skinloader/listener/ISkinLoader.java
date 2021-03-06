package com.guiyujin.purenote_mvp.skinloader.listener;

/**
 * Created by _SOLID
 * Date:2016/4/13
 * Time:20:47
 * <p/>
 * 用来添加、删除需要皮肤更新的界面以及通知界面皮肤更新
 */
public interface ISkinLoader {
    void attach(ISkinUpdate observer);

    void detach(ISkinUpdate observer);

    void notifySkinUpdate();
}
