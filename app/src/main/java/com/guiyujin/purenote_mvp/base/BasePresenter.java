package com.guiyujin.purenote_mvp.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @ProjectName: MVPDemo
 * @Package: com.guiyujin.mvpdemo.base
 * @ClassName: BasePresenter
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/6 11:41
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/6 11:41
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class BasePresenter<V extends BaseView, E extends BaseModel> implements IPresenter<V, E>, LifecycleObserver {
    public V mView;
    public E mModel;

    @Override
    public void attach(V view, E model) {
        mView = view;
        mModel = model;
        mView.getLifecycle().addObserver(this);
    }

    @Override
    public void detach() {
        if (mView != null){
            mView.getLifecycle().removeObserver(this);
            mView = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner){
        detach();
    }

}
