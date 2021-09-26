package com.guiyujin.purenote_mvp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.LayoutRes;

public class OperationDialog extends AlertDialog.Builder implements View.OnClickListener{
    private RadioGroup radioGroup;
    private Dialog dialog;
    static RadioButton rb_edit, rb_delete;
    private Context context;

    public void setOperationDialogListener(OperationDialogListener operationDialogListener) {
        this.operationDialogListener = operationDialogListener;
    }

    private OperationDialogListener operationDialogListener;

    public OperationDialog(Context context){
        super(context);
        this.context = context;
    }

    public void showDialog(@LayoutRes int resource, int gravity) {

        //设置要显示的view
        View view = View.inflate(context,resource,null);

        dialog = new Dialog(context, R.style.photo_dialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.center_round_operation_dialog);
//        if (gravity == Gravity.BOTTOM){
//            window.setBackgroundDrawableResource(R.drawable.bottom_round_operation_dialog);
//        }else if (gravity == Gravity.CENTER){
//            window.setBackgroundDrawableResource(R.drawable.center_round_operation_dialog);
//        }

        //设置弹出窗口大小
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        //设置显示位置
        window.setGravity(gravity);
        //设置动画效果
//        window.setWindowAnimations(R.style.Widget_AppCompat_ActionBar);
        dialog.show();
        radioGroup = view.findViewById(R.id.radio_group);

        rb_edit = view.findViewById(R.id.action_edit);
        rb_delete = view.findViewById(R.id.action_delete);
        rb_edit.setOnClickListener(this);
        rb_delete.setOnClickListener(this);

    }
    

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_edit:
                operationDialogListener.edit();
                dialog.dismiss();
                return;
            case R.id.action_delete:
                operationDialogListener.delete();
                dialog.dismiss();
                return;
        }
    }

    public interface OperationDialogListener {
        void edit();
        void delete();
    }
}
