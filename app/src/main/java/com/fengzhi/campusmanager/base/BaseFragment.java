package com.fengzhi.campusmanager.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;
    protected Context mContext;
    protected ProgressDialog progressDialog;
    protected Context dialogContext;
    protected Toast toast;
    protected boolean isVisible;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        initView(inflater, container, savedInstanceState);
        return view;

    }


    /**
     * 显示加载对话框
     *
     * @param message
     */
    protected void showLoadingDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
        }
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        if (!getActivity().isFinishing()) {
            progressDialog.show();
        }
    }

    /**
     * 显示加载对话框
     */
    protected void showLoadingDialog(String message, boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
        }
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        //dialog是依赖于activity上的 所以如果activity被销毁的话就无法显示dialog然后抛出一个dialog无法添加到view上的异常
        if (!getActivity().isFinishing()) {
            progressDialog.show();
        }
    }

    /**
     * 隐藏加载对话框
     */
    protected void dismissLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();

        }
    }

    /**
     * 提示框
     */
    protected void SToast(String message) {
        synchronized (mContext) {//锁住context
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    /**
     * 提示框(中间)
     */
    protected void CToast(String message) {
        synchronized (mContext) {
            toast.cancel();
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void startActivity(Class<?> activity, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getActivity().startActivity(intent);
        if (isFinish) {
            getActivity().finish();
        }
    }

    public void startActivityForResult(Class<?> activity, Bundle bundle, int requestCode,
                                       boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        getActivity().startActivityForResult(intent, requestCode);
        if (isFinish) {
            getActivity().finish();
        }
    }


    /**
     * 隐藏键盘
     */
    public void unkeyboard() {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public Context getContext() {
        return mContext;
    }

    protected abstract void initView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState);


    protected abstract int getLayoutResId();

}
