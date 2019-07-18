package com.fengzhi.campusmanager.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

public class LoanActivity extends SlideBackBaseActivity {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.loan);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_loan;
    }
}
