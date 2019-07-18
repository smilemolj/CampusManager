package com.fengzhi.campusmanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.fengzhi.campusmanager.base.BaseActivity;
import com.fengzhi.campusmanager.fragment.ChatFragment;
import com.fengzhi.campusmanager.fragment.ContactFragment;
import com.fengzhi.campusmanager.fragment.HomeFragment;
import com.fengzhi.campusmanager.fragment.MineFragment;
import com.fengzhi.campusmanager.util.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_naigation)
    BottomNavigationView mBottomNaigation;
    private long lastClickTime;

    public static final int FARGMENT_HOME = 1;
    public static final int FRAGMENT_CHAT = 2;
    public static final int FARGMENT_CONTACT = 3;
    public static final int FARGMENT_MINE = 4;

    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private ContactFragment contactFragment;
    private MineFragment mineFragment;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        //手动设置状态栏颜色
        setStatusBar = true;
        BottomNavigationViewHelper.disableShiftMode(mBottomNaigation);
        mBottomNaigation.setSelectedItemId(mBottomNaigation.getMenu().getItem(FARGMENT_HOME).getItemId());
        mBottomNaigation.setSelectedItemId(R.id.action_home);
        showFragment(FARGMENT_HOME);
        mBottomNaigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    showFragment(FARGMENT_HOME);
                    break;
                case R.id.action_order:
                    showFragment(FRAGMENT_CHAT);
                    break;
                case R.id.action_followorder:
                    showFragment(FARGMENT_CONTACT);
                    break;
                case R.id.action_business:
                    showFragment(FARGMENT_MINE);
                    break;
            }
            return true;
        });
    }

    private void showFragment(int type) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (type) {
            case FRAGMENT_CHAT:
                if (chatFragment == null) {
                    chatFragment = new ChatFragment();
                    ft.add(R.id.container, chatFragment, ChatFragment.class.getName());
                } else {
                    ft.show(chatFragment);
                }
                break;
            case FARGMENT_HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.container, homeFragment, HomeFragment.class.getName());
                } else {
                    ft.show(homeFragment);
                }
                break;
            case FARGMENT_CONTACT:
                if (contactFragment == null) {
                    contactFragment = new ContactFragment();
                    ft.add(R.id.container, contactFragment, ContactFragment.class.getName());
                } else {
                    ft.show(contactFragment);
                }
                break;
            case FARGMENT_MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.container, mineFragment, MineFragment.class.getName());
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果不为空，就先隐藏起来
        if (chatFragment != null) {
            fragmentTransaction.hide(chatFragment);
        }
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (contactFragment != null) {
            fragmentTransaction.hide(contactFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (lastClickTime <= 0) {
            SToast("再按一次退出应用");
            lastClickTime = System.currentTimeMillis();
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime < 2000) {
                finish();
            } else {
                lastClickTime = System.currentTimeMillis();
            }
        }
    }//双击退出

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
