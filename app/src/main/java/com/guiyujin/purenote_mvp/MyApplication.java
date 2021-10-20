package com.guiyujin.purenote_mvp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.guiyujin.purenote_mvp.skinloader.base.SkinBaseApplication;
import com.guiyujin.purenote_mvp.skinloader.load.SkinManager;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/8 11:59
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/8 11:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyApplication extends SkinBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
        Log.i("ONRESULTSSSS", "ONAAACREATE " );
    }
}
