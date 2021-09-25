package com.guiyujin.purenote_mvp.activity;


import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.guiyujin.purenote_mvp.R;

import java.util.concurrent.Executor;

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
    private Boolean isFinger;
    private ImageView imageView;
    private Executor executor;
    private Intent intent;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().getDecorView().setBackground(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_launcher);

        imageView = findViewById(R.id.imageView);
        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        isFinger = sp.getBoolean("isFinger", false);
        intent = new Intent(LauncherActivity.this, MainActivity.class);
        showLauncherAnim();
    }

    private void checkFinger() {
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LauncherActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                Toast.makeText(getApplicationContext(),
                        "验证错误： " + errString, Toast.LENGTH_SHORT)
                        .show();
                finish();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "验证成功", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                Toast.makeText(getApplicationContext(), "验证失败",
                        Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("指纹验证")
                .setSubtitle("请验证指纹")
                .setNegativeButtonText("退出")
                .setConfirmationRequired(true)
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void showLauncherAnim() {
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f,1,0.5f,1,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f,1);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);

        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        imageView.setAnimation(set);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFinger){
                    checkFinger();
                }else {
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);
    }
}
