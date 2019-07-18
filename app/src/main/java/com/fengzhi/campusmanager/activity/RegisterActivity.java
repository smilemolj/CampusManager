package com.fengzhi.campusmanager.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends SlideBackBaseActivity {

    @BindView(R.id.account_ET)
    EditText et_account; //用户名
    @BindView(R.id.name_ET)
    EditText et_name; //姓名
    EditText et_post; //职务
    @BindView(R.id.phone_ET)
    EditText et_phone; //手机
    @BindView(R.id.password_ET)
    EditText et_password; //密码
    @BindView(R.id.register_BTN)
    Button registerBTN;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.string_register);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.register_BTN)
    public void onViewClicked() {
    }

}
