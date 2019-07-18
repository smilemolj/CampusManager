package com.fengzhi.campusmanager.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;


import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetpswActivity extends SlideBackBaseActivity {


    @BindView(R.id.account_ET)
    EditText et_account; //用户名
    @BindView(R.id.phone_ET)
    EditText et_phone; //手机号
    @BindView(R.id.set_psw)
    EditText setPsw; //密码
    @BindView(R.id.confirm_psw)
    EditText confirmPsw; //确认密码

    @OnClick(R.id.submit_psw)
    public void onViewClicked() {
        String account = et_account.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String password = setPsw.getText().toString().trim();
        String confirmPassword = confirmPsw.getText().toString().trim();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            SToast("请输入完整信息");
        } else {
//            model.doForgetpsw(account,phone,password,confirmPassword);
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.string_forgetpsw);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forgetpsw;
    }

}
