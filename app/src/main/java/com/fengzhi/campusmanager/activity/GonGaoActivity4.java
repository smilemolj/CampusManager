package com.fengzhi.campusmanager.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

public class GonGaoActivity4 extends SlideBackBaseActivity {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.gongao4);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gon_gao4;
    }
}
