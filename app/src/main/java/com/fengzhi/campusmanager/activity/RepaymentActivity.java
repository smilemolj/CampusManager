package com.fengzhi.campusmanager.activity;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.base.SlideBackBaseActivity;

public class RepaymentActivity extends SlideBackBaseActivity {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.repayment);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_repayment;
    }
}
