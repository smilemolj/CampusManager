package com.fengzhi.campusmanager.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.constants.Constants;
import com.fengzhi.campusmanager.model.LoginModel;
import com.fengzhi.campusmanager.util.NetWorkStateUtil;
import com.fengzhi.campusmanager.util.PreUtils;
import com.fengzhi.campusmanager.view.ILoginView;

public class SplashActivity extends AppCompatActivity implements ILoginView {

    private RelativeLayout mRelativeLayout;

    LoginModel model = new LoginModel(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_splash);
        mRelativeLayout = findViewById(R.id.relative_layout);
        //初始化动画
        initAnmi();
    }

    private void initAnmi() {
        //透明度动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000); //设置动画时长
        alpha.setFillAfter(true); //动画运行完成保留结束时状态
        //监听动画
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //网络可用的情况下判断用户密码有没有在其他应用端被修改过
                if (NetWorkStateUtil.isNetworkAvailable(SplashActivity.this)) {
                    Constants.HOST_URL = PreUtils.getInstance().getString(SplashActivity.this,
                            Constants.PRE_SERVER, "");
                    String username = PreUtils.getInstance().getString(SplashActivity.this,
                            "account", "");
                    String password = PreUtils.getInstance().getString(SplashActivity.this,
                            "password", "");
                    if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                        return;
                    }
//                    model.doLogin(username, password);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画运行完进入下一个页面
                String userId = String.valueOf(PreUtils.getInstance().getInt(SplashActivity.this,
                        "userId", 0));
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade_in_500, R.anim.fade_out_500);
                finish();
                return;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mRelativeLayout.startAnimation(alpha);
    }

//    @Override
//    public void onShowError(String message) {
//        //登录错误跳到登录页面
//        PreUtils.getInstance().cleanAll(this);
//        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
}
