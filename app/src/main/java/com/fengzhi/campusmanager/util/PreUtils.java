package com.fengzhi.campusmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PreUtils {

    public static final String PRE_NAME = "XIONGEN_PDA";

    public static SharedPreferences preferences;


    public static PreUtils getInstance() {
        return SingletonHodler.INSTANCE;
    }

    private static class SingletonHodler {
        public static final PreUtils INSTANCE = new PreUtils();
    }


    /**
     * boolean
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public void putBoolean(Context context, String key, boolean value) {

        if (preferences == null) {
            preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        }
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(Context context, String key, boolean defValue) {

        if (preferences == null) {
            preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        }
        return preferences.getBoolean(key, defValue);
    }

    public void putString(Context context, String key, String value) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        }
        preferences.edit().putString(key, value).apply();
    }

    public String getString(Context context, String key, String defValue) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        }
        return preferences.getString(key, defValue);
    }

    public void putInt(Context context, String key, Integer value) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        }
        preferences.edit().putInt(key, value).apply();
    }

    public Integer getInt(Context context, String key, Integer defValue) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        }
        return preferences.getInt(key, defValue);
    }


    /**
     * 清除数据
     */
    public void cleanAll(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 存放实体类以及任意类型
     *
     * @param context 上下文对象
     * @param key
     * @param obj
     */
    public void putBean(Context context, String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                SharedPreferences.Editor editor = context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE).edit();
                editor.putString(key, string64).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("the obj must implement Serializble");
        }

    }

    public Object getBean(Context context, String key) {
        Object obj = null;
        try {
            String base64 = context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE).getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
