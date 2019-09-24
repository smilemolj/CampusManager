package com.fengzhi.campusmanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.MainActivity;
import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.BaseActivity;
import com.fengzhi.campusmanager.model.LoginModel;
import com.fengzhi.campusmanager.view.ILoginView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity1 extends BaseActivity implements ILoginView {

    LoginModel loginModel = new LoginModel(this);

    @BindView(R.id.user_name)
    EditText et_userName; //用户名
    @BindView(R.id.pass_word)
    EditText et_passWord; //密码

    @OnClick({R.id.registered, R.id.forget_psw, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registered:
                startActivity(RegisterActivity.class, null, false);
                break;
            case R.id.forget_psw:
                startActivity(ForgetpswActivity.class, null, false);
                break;
            case R.id.btn_login:
                if (!et_userName.getText().toString().equals("") && !et_passWord.getText().toString().equals("")) {
//                    loginModel.doLogin(et_userName.getText().toString(),
//                            et_passWord.getText().toString());
//                    startActivity(MainActivity.class, null, false);
//                    finish();
                } else {
                    SToast("用户名密码不能为空");
                }
                break;
        }
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
