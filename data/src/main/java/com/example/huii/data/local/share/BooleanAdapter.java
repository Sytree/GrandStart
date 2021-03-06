package com.example.huii.data.local.share;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;


public final class BooleanAdapter implements Preference.Adapter<Boolean> {
    public static final BooleanAdapter INSTANCE = new BooleanAdapter();

    @Override
    public Boolean get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getBoolean(key, false);
    }

    @Override
    public void set(@NonNull String key, @NonNull Boolean value,
                    @NonNull SharedPreferences.Editor editor) {
        editor.putBoolean(key, value);
    }
}
