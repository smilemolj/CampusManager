package com.fengzhi.campusmanager.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.activity.GonGaoActivity;
import com.fengzhi.campusmanager.activity.GonGaoActivity4;
import com.fengzhi.campusmanager.activity.GonGaoActivity5;
import com.fengzhi.campusmanager.activity.LoanActivity;
import com.fengzhi.campusmanager.activity.LoginActivity;
import com.fengzhi.campusmanager.activity.LoginActivity1;
import com.fengzhi.campusmanager.activity.RepaymentActivity;
import com.fengzhi.campusmanager.activity.TouZhiActivity;
import com.fengzhi.campusmanager.activity.XueFeiActivity;
import com.fengzhi.campusmanager.adapter.FunctionAdapter;
import com.fengzhi.campusmanager.base.BaseFragment;
import com.fengzhi.campusmanager.bean.HomeFunctionBean;
import com.fengzhi.campusmanager.util.GlideImageLoader;
import com.fengzhi.campusmanager.util.MPChartHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    public static final int BUSINESS = 1;
    public static final int CLIENT = 4;
    public static final int[] functionImageResIds = {R.mipmap.loan, R.mipmap.investment,
            R.mipmap.repayment, R.mipmap.xuefei, R.mipmap.gonggao, R.mipmap.sushe, R.mipmap.jiucan};
    public static final String[] functionName = {"校园门禁", "钱包", "景点", "交学费", "校园公告栏", "宿舍管理",
            "食堂就餐"};

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title_back)
    ImageButton titleBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.home_banner)
    Banner banner;

    private FunctionAdapter mFunctionAdapter;
    private List<HomeFunctionBean> mFunctionDatas = new ArrayList<>();//存储主页列表功能
    private List<String> xAxisValues;//柱形图的x轴的值
    private List<Float> yAxisValues;//柱形图的y轴的值

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container,
                            final Bundle savedInstanceState) {
        title.setText(R.string.app_name);
        titleBack.setVisibility(View.INVISIBLE);
        initFunctionDatas();
        initData();

        mFunctionAdapter = new FunctionAdapter(R.layout.item_function, mFunctionDatas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mFunctionAdapter);
        //添加报表到列表头部
//        mFunctionAdapter.addHeaderView(showBarChart());
        //添加报表下的数据列表到头部
        mFunctionAdapter.addHeaderView(showRoleType(BUSINESS));
        //添加线条到列表头部
        mFunctionAdapter.addHeaderView(showLineView());
        //追加线条
        mFunctionAdapter.addFooterView(showLineView());

        mFunctionAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
//                    startActivity(LoanActivity.class, null, false);
                    SToast("开门中...");
                    break;
                case 1:
                    startActivity(TouZhiActivity.class, null, false);
                    break;
                case 2:
                    startActivity(RepaymentActivity.class, null, false);
                    break;
                case 3:
                    startActivity(XueFeiActivity.class, null, false);
                    break;
                case 4:
                    startActivity(GonGaoActivity.class, null, false);
                    break;
                case 5:
                    startActivity(GonGaoActivity4.class, null, false);
                    break;
                case 6:
                    startActivity(GonGaoActivity5.class, null, false);
                    break;
            }
        });

//                添加广告轮播图片
        List<String> images = new ArrayList<>();
        images.add("http://img2.imgtn.bdimg.com/it/u=2711698379,2510140807&fm=26&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=605435257,2656259734&fm=26&gp=0.jpg");
        images.add("http://img4.imgtn.bdimg.com/it/u=146194547,2303495867&fm=26&gp=0.jpg");
//        添加图片加载类
        banner.setImageLoader(new GlideImageLoader());
        //设置图片
        banner.setImages(images);
        banner.setDelayTime(3000);//设置轮播时间
        banner.start();

    }

    private void initData() {
        xAxisValues = new ArrayList<>();
        yAxisValues = new ArrayList<>();
        for (int i = 0; i < 12; ++i) {
            xAxisValues.add(String.valueOf(i + 1));
            yAxisValues.add((float) (Math.random() * 1000 + 20));
        }
    }

    private void initFunctionDatas() {
        mFunctionDatas.clear();
        HomeFunctionBean homeFunctionBean;
        for (int i = 0; i < 7; i++) {
            homeFunctionBean = new HomeFunctionBean();
            homeFunctionBean.setImageResId(functionImageResIds[i]);
            homeFunctionBean.setFunctionName(functionName[i]);
            mFunctionDatas.add(homeFunctionBean);
        }
    }

    private View showRoleType(int type) {
        View view = null;
        if (type == BUSINESS) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_business, null);
            TextView msales = view.findViewById(R.id.m_sales);
            TextView ysales = view.findViewById(R.id.y_sales);
            TextView completiyuanonRate = view.findViewById(R.id.completion_rate); //完成率
            msales.setText("未读消息：100");
            ysales.setText("通知：300");
            completiyuanonRate.setText("我的会议：16");
//                linearLayout.addView(view);
            return view;
        } else if (type == CLIENT) {
        }
        return null;
    }

    private View showBarChart() {
        View view = null;
        view = LayoutInflater.from(getContext()).inflate(R.layout.item_home_barchart, null);
        BarChart mBarChart = view.findViewById(R.id.barchart);
        MPChartHelper.setBarChart(mBarChart, xAxisValues, yAxisValues, "", 15, null);
        return view;
    }

    //    灰色的线
    private View showLineView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, null);
        TextView textView = view.findViewById(R.id.line_view);
        textView.setBackgroundResource(R.color.color_f6f6f6);
        return view;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }


}
