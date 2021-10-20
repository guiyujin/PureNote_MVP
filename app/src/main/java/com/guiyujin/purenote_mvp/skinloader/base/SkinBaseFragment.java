package com.guiyujin.purenote_mvp.skinloader.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.guiyujin.purenote_mvp.skinloader.attr.DynamicAttr;
import com.guiyujin.purenote_mvp.skinloader.listener.IDynamicNewView;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:35
 */
public class SkinBaseFragment extends Fragment implements IDynamicNewView {

    private IDynamicNewView mIDynamicNewView;
    private LayoutInflater mLayoutInflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mIDynamicNewView = (IDynamicNewView) context;
        } catch (ClassCastException e) {
            mIDynamicNewView = null;
        }
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        if (mIDynamicNewView == null) {
            throw new RuntimeException("IDynamicNewView should be implements !");
        } else {
            mIDynamicNewView.dynamicAddView(view, pDAttrs);
        }
    }

    public void dynamicAddSkinView(View view, String attrName, int attrValueResId) {
        List<DynamicAttr> pDAttrs = new ArrayList<>();
        pDAttrs.add(new DynamicAttr(attrName, attrValueResId));
        dynamicAddView(view, pDAttrs);
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater result = getActivity().getLayoutInflater();
        return result;
    }
}
