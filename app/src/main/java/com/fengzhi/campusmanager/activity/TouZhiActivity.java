package com.fengzhi.campusmanager.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

public class TouZhiActivity extends SlideBackBaseActivity {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.touzhi);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tou_zhi;
    }
}
