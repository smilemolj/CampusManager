package com.fengzhi.campusmanager.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.bean.ContactBean;

import java.util.List;

public class ContactAdapter extends BaseQuickAdapter<ContactBean, BaseViewHolder> {
    public ContactAdapter(int layoutResId, @Nullable List<ContactBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactBean item) {
        helper.setImageResource(R.id.img_icon, item.getIcon()).setText(R.id.text_title,
                "昵称：" + item.getNick()).setText(R.id.text_sex, "性别：" + item.getSex());
    }
}
