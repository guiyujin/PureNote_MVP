package com.guiyujin.purenote_mvp.callback;

import android.content.Intent;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.callback
 * @ClassName: ICallBack
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/7 9:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/7 9:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface ICallBack {
    void onSuccess();
    void onFailed();

    void onSuccess(Intent intent);
}
