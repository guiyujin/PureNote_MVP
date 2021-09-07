package com.guiyujin.purenote_mvp.model.main;

import android.content.Context;

import com.guiyujin.purenote_mvp.callback.ICallBack;
import com.guiyujin.purenote_mvp.room.DBengine;
import com.guiyujin.purenote_mvp.room.Note;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.main
 * @ClassName: MainModelImpl
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 14:24
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 14:24
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MainModelImpl implements MainModelConstract.Model{

    @Override
    public void search(List<Note> noteList, String text, Context context, ICallBack iCallBack) {
        LinkedList<Note> filterResults = new LinkedList<>();
        for(Note note : noteList){
            if (note.getContent().contains(text)) {
                filterResults.add(note);
            }
        }
        iCallBack.onSuccess(filterResults);
    }

    @Override
    public void delete(Note note, Context context) {
        DBengine dBengine = new DBengine(context.getApplicationContext());
        dBengine.delete(note);
    }
}
