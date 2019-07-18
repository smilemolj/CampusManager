package com.fengzhi.campusmanager.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

public class GonGaoActivity5 extends SlideBackBaseActivity {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.gongao5);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gon_gao5;
    }
}
