package com.fengzhi.campusmanager.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

import butterknife.OnClick;

public class ToolActivity extends SlideBackBaseActivity {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.tool);
    }

    @OnClick({R.id.update_tv, R.id.about_tv, R.id.out_login_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_tv:
                SToast("已是最新版本");
                break;
            case R.id.about_tv:
                SToast("关于");
                break;
            case R.id.out_login_but:
                SToast("退出登录");
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tool;
    }

}
