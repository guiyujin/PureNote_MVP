package com.guiyujin.purenote_mvp.base.mvp;

/**
 * @ProjectName: MVPDemo
 * @Package: com.guiyujin.mvpdemo.mvp
 * @ClassName: IPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2021/9/6 11:37
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/6 11:37
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface IPresenter<V, E> {

    void attach(V view, E model);
    void detach();
}
