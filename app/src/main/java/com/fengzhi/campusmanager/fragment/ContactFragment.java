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
import com.fengzhi.campusmanager.adapter.ContactAdapter;
import com.fengzhi.campusmanager.base.BaseFragment;
import com.fengzhi.campusmanager.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ContactFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back)
    ImageButton titleBack;
    @BindView(R.id.title)
    TextView title;

    private ContactAdapter contactAdapter;
    private List<ContactBean> contactBeans = new ArrayList<>();
    private ContactBean contactBean;
    private int[] iconImgIDs = {R.mipmap.headimg, R.mipmap.headimg, R.mipmap.headimg,
            R.mipmap.headimg, R.mipmap.headimg, R.mipmap.headimg, R.mipmap.headimg,
            R.mipmap.headimg, R.mipmap.headimg, R.mipmap.headimg,};
    private String[] nick = {"小宝贝", "小宝贝", "小宝贝", "小宝贝", "小宝贝", "小宝贝", "小宝贝", "小宝贝", "小宝贝", "小宝贝",};
    private String[] sex = {"女", "女", "女", "女", "女", "女", "女", "女", "女", "女",};

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        title.setText(R.string.string_contact);
        titleBack.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initData();
        contactAdapter = new ContactAdapter(R.layout.item_contact, contactBeans);
        contactAdapter.setOnItemClickListener((adapter, view, position) -> startActivity(MyInfoActivity.class, null, false));
        recyclerView.setAdapter(contactAdapter);
    }

    private void initData() {
        contactBeans.clear();
        for (int i = 0; i < 10; i++) {
            contactBean = new ContactBean();
            contactBean.setIcon(iconImgIDs[i]);
            contactBean.setNick(nick[i]);
            contactBean.setSex(sex[i]);
            contactBeans.add(contactBean);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_contact;
    }

}
