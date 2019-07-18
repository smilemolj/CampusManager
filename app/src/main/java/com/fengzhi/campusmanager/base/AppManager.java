package com.fengzhi.campusmanager.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

public class AppManager {

    private static Stack<Activity> activityStack;

    private int count = 0;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static AppManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
        count++;
    }

    /**
     * 获取当前Activity（堆栈最后一个压入的）
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity(堆栈中最后一个压入的)
     */
    public void finishActivity() {
        finishActivity(activityStack.lastElement());
        count--;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            count--;
        }
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        count = 0;
    }


    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {

        try {
            finishAllActivity();
            ActivityManager activityMgr =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());

            System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * 将指定Activity移除堆栈
     */
    public void removeActivityFromStack(Activity activity) {
        activityStack.remove(activity);
    }


    private static class SingletonHolder {
        public static final AppManager INSTANCE = new AppManager();
    }


}
