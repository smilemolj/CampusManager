package com.fengzhi.campusmanager.fragment;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.activity.MyInfoActivity;
import com.fengzhi.campusmanager.adapter.MessageAdapter;
import com.fengzhi.campusmanager.base.BaseFragment;
import com.fengzhi.campusmanager.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back)
    ImageButton titleBack;
    @BindView(R.id.title)
    TextView title;
    private MessageAdapter messageAdapter;
    private List<MessageBean> messageBeans = new ArrayList<>();
    private MessageBean messageBean;
    private int[] iconImgIDs = {R.mipmap.avatar_01, R.mipmap.avatar_02, R.mipmap.avatar_03,
            R.mipmap.avatar_04, R.mipmap.avatar_05, R.mipmap.avatar_06, R.mipmap.avatar_07};
    private String[] nick = {"小宝贝", "萌萌哒", "美女1号", "小马哥", "老大", "666", "哈喽"};
    private String[] info = {"不错哈", "我要飞得更高", "这有点帅,不好意思了", "小伙子有前途啊", "啦啦啦", "有前途", "加油"};
    private String[] time = {"21:32", "20:23", "20:03", "18:58", "15:42", "15:02", "10:48"};
    private String[] number = {"3", "16", "2", "5", "1", "3", "6", "12", "2", "1",};

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        title.setText(R.string.string_chat);
        titleBack.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initData();
        messageAdapter = new MessageAdapter(R.layout.msg_list_item, messageBeans);
        messageAdapter.setOnItemClickListener((adapter, view, position) -> startActivity(MyInfoActivity.class, null, false));
        recyclerView.setAdapter(messageAdapter);
    }

    private void initData() {
        messageBeans.clear();
        for (int i = 0; i < 7; i++) {
            messageBean = new MessageBean();
            messageBean.setIcon(iconImgIDs[i]);
            messageBean.setNick(nick[i]);
            messageBean.setMsginfo(info[i]);
            messageBean.setTime(time[i]);
            messageBean.setMesnumber(number[i]);
            messageBeans.add(messageBean);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_chat;
    }

}
