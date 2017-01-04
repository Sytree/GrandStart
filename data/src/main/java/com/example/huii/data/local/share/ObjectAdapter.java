package com.example.huii.data.local.share;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by huii on 16/7/6.
 */
public class ObjectAdapter implements Preference.Adapter<Object> {

    public static final ObjectAdapter INSTANCE = new ObjectAdapter();
    

    @Override
    public Object get(@NonNull String key, @NonNull SharedPreferences preferences) {
        Object object = null;

        try {
            String e = preferences.getString(key, "");
            if(TextUtils.isEmpty(e)) {
                throw new NullPointerException("get an empty string accroding key" + key);
            }

            byte[] base64Bytes = Base64.decode(e.getBytes(), 0);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            object = ois.readObject();

        } catch (Exception var8) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);

            Log.e("SharePreferenceUtil", var8.toString());
            // TODO: 16/7/8  
        }

        if (object == null) {
            throw new NullPointerException();
        }

        return object;
    }

    @Override
    public void set(@NonNull String key, @NonNull Object value, @NonNull SharedPreferences.Editor editor) {

        String objectBase64 = "";

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            objectBase64 = Base64.encodeToString(bos.toByteArray(), 0);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        editor.putString(key, objectBase64);
        editor.commit();
    }
}
