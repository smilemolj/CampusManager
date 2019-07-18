package com.fengzhi.campusmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends SlideBackBaseActivity {

    private static final int NIKENAME = 1;
    private static final int SEX = 2;
    private static final int BIRTHDAY = 3;
    private static final int PROFESSION = 4;
    private static final int HOBBY = 5;

    @BindView(R.id.headImg)
    CircleImageView headImg;
    @BindView(R.id.nickname_tv)
    TextView nicknameTv;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.birthday_tv)
    TextView birthdayTv;
    @BindView(R.id.profession_tv)
    TextView professionTv;
    @BindView(R.id.hobby_tv)
    TextView hobbyTv;


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.my_info);
        getData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取到数据 可能是本地缓存数据
     */
    private void getData() {
        showData();
    }

    /**
     * 展示界面数据
     */
    private void showData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.headImg_but, R.id.nickname_but, R.id.sex_but, R.id.birthday_but,
            R.id.profession_but, R.id.hobby_but, R.id.alter_pwd_but, R.id.address_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headImg_but:
                break;
            case R.id.nickname_but:
                break;
            case R.id.sex_but:
                break;
            case R.id.birthday_but:
                break;
            case R.id.profession_but:
                break;
            case R.id.hobby_but:
                break;
            case R.id.alter_pwd_but:
                break;
            case R.id.address_but:
                break;
        }
    }

    /**
     * 更新修改昵称，生日性别，职业爱好等
     */
    private void updateInfo(int type, String content) {
        switch (type) {
            case NIKENAME:
                break;
            case SEX:
                break;
            case BIRTHDAY:
                break;
            case PROFESSION:
                break;
            case HOBBY:
                break;
        }

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_info;
    }


}
