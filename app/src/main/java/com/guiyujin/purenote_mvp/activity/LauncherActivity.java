package com.guiyujin.purenote_mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.guiyujin.purenote_mvp.R;

/**
 * @ProjectName: PureNote_MVP
 * @Package: com.guiyujin.purenote_mvp.activity
 * @ClassName: LauncherActivity
 * @Description: java类作用描述
 * @Author: 归余烬
 * @CreateDate: 2021/9/8 12:18
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/9/8 12:18
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LauncherActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().getDecorView().setBackground(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_launcher);

        imageView = findViewById(R.id.imageView);

        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f,1,0.5f,1,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1);
        scaleAnimation.setDuration(1500);
        scaleAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f,1);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setFillAfter(true);

        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        imageView.setAnimation(set);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
