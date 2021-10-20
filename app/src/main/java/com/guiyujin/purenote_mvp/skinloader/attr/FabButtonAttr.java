package com.guiyujin.purenote_mvp.skinloader.attr;

import android.content.res.ColorStateList;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.guiyujin.purenote_mvp.skinloader.load.SkinManager;


/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:13:52
 */
public class FabButtonAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof FloatingActionButton) {
            FloatingActionButton fb = (FloatingActionButton) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                int color = SkinManager.getInstance().getColor(attrValueRefId);
                fb.setBackgroundTintList(ColorStateList.valueOf(color));
            } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
                //  tv.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
            }
        }
    }
}
