package com.fengzhi.campusmanager.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.bean.HomeFunctionBean;

import java.util.List;

public class FunctionAdapter extends BaseQuickAdapter<HomeFunctionBean, BaseViewHolder> {

    public FunctionAdapter(int layoutResId, @Nullable List<HomeFunctionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeFunctionBean item) {
        helper.setImageResource(R.id.function_img, item.getImageResId());
        helper.setText(R.id.function_name, item.getFunctionName());
    }
}
