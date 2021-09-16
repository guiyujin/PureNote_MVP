package com.guiyujin.purenote_mvp.model.addcontent;

import android.content.Context;

import com.guiyujin.lib_common.security.AESCrypt;
import com.guiyujin.purenote_mvp.room.DBengine;
import com.guiyujin.purenote_mvp.room.Note;

import java.security.GeneralSecurityException;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model
 * @ClassName: AddcontentImpl
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 9:21
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 9:21
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AddContentModelImpl implements AddContentConstract.Model{
    @Override
    public void save(Note note, Context context, AddContentConstract.AddCallBack addCallBack) {
        DBengine dBengine = new DBengine(context.getApplicationContext());
        dBengine.insert(note);
        addCallBack.onSuccess();
    }
}
