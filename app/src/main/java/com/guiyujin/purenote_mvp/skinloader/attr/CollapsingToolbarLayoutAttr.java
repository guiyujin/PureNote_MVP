package com.guiyujin.purenote_mvp.skinloader.attr;


import android.util.Log;
import android.view.View;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import com.guiyujin.purenote_mvp.skinloader.load.SkinManager;


/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:14:23
 */
public class CollapsingToolbarLayoutAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof CollapsingToolbarLayout) {
            Log.i("CollapsingToolbarAttr", "apply");
            CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                Log.i("CollapsingToolbarAttr", "apply color");
                int color = SkinManager.getInstance().getColor(attrValueRefId);
                ctl.setContentScrimColor(color);
                ctl.setBackgroundColor(color);
            } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
                Log.i("CollapsingToolbarAttr", "apply drawable");
            }
        }
    }
}
