package com.guiyujin.purenote_mvp.model.detail;

import android.content.Context;
import android.content.Intent;

import com.guiyujin.purenote_mvp.Util;
import com.guiyujin.purenote_mvp.room.DBengine;
import com.guiyujin.purenote_mvp.room.Note;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.model.detail
 * @ClassName: DetailModelImpl
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 10:59
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 10:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class DetailModelImpl implements DetailConstract.Model{
    @Override
    public void delete(Note note, Context context) {
        DBengine dBengine = new DBengine(context.getApplicationContext());
        dBengine.delete(note);
    }

    @Override
    public void update(Note note, Context context) {
        DBengine dBengine = new DBengine(context.getApplicationContext());
        dBengine.updateNotes(note);
    }

    @Override
    public void share(Note note, DetailConstract.DetailCallBack detailCallBack) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, note.getContent().replaceAll("<img src='(.*?)'/>","[图片]").replaceAll("<voice src='(.*?)'/>","[语音]"));
        detailCallBack.onSuccess(intent);

    }

    @Override
    public void addAlarm(Context context, Note note) {
        Util.OpenCalendar(context, note.getContent());
    }
}
