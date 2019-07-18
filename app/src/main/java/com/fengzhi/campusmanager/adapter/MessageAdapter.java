package com.fengzhi.campusmanager.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.bean.MessageBean;

import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public MessageAdapter(int layoutResId, @Nullable List<MessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setImageResource(R.id.msg_avatar, item.getIcon()).setText(R.id.msg_nickname,
                item.getNick()).setText(R.id.msg_info, item.getMsginfo()).setText(R.id.msg_time,
                item.getTime()).setText(R.id.msg_number, item.getMesnumber());
    }
}
