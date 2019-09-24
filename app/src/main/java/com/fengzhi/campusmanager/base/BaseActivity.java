package com.fengzhi.campusmanager.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fengzhi.campusmanager.App;
import com.fengzhi.campusmanager.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Toast toast;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        AppManager.getInstance().addActivity(this);
        unbinder = ButterKnife.bind(this); //绑定ButterKnife
        mContext = this;
        toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);//初始化一个toast，解决多次弹出toast冲突问题
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView(savedInstanceState);

        //设置状态栏5.0以上的Android系统才可以设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //将View全屏
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //将状态栏改成背景色改成透明的
            window.setStatusBarColor(Color.TRANSPARENT);
//            if (setStatusBar) {
//                window.setStatusBarColor(App.getInstance().getResources().getColor(R.color.theme_color));
//            } else {
//                //将View全屏
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                //将状态栏改成背景色改成透明的
//                window.setStatusBarColor(Color.TRANSPARENT);
//            }
        }

    }

    public void setTitle(int id) {
        ((TextView) findViewById(R.id.title)).setText(id);
    }

    protected void setRight(int id) {
        findViewById(R.id.right).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.right)).setText(id);
    }

    protected void SToast(String message) {
        synchronized (mContext) {
            toast.cancel();
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void CToast(String message) {
        synchronized (mContext) {
            toast.cancel();
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    protected void startActivity(Class<?> activity, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(mContext, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    protected void startActivityForResult(Class<?> activity, Bundle bundle, int requestCode,
                                          boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(mContext, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        if (isFinish) {
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void unkeyboard() {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void backfinish(View view) {
        unkeyboard();
        finish();
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract void initView(@Nullable Bundle savedInstanceState);  //初始化

    protected abstract int getLayoutResId();  //加载布局文件

}
