package com.fengzhi.campusmanager.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.activity.MyInfoActivity;
import com.fengzhi.campusmanager.activity.ToolActivity;
import com.fengzhi.campusmanager.base.BaseFragment;

import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.tool, R.id.info_layout, R.id.sys_info_tv, R.id.my_share_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tool:
                startActivity(ToolActivity.class, null, false);
                break;
            case R.id.info_layout:
                startActivity(MyInfoActivity.class, null, false);
                break;
            case R.id.sys_info_tv:
                break;
            case R.id.my_share_tv:
                break;
        }
    }
}
