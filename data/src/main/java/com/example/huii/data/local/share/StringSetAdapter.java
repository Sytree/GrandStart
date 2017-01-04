package com.example.huii.data.local.share;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Set;

public final class StringSetAdapter implements Preference.Adapter<Set<String>> {
    public static final StringSetAdapter INSTANCE = new StringSetAdapter();

    @Override
    public Set<String> get(@NonNull String key, @NonNull SharedPreferences preferences) {
        return preferences.getStringSet(key, null);
    }

    @Override
    public void set(@NonNull String key, @NonNull Set<String> value,
                    @NonNull SharedPreferences.Editor editor) {
        editor.putStringSet(key, value);
    }
}
