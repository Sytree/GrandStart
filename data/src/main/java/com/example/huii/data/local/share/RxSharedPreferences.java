package com.example.huii.data.local.share;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.Set;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;


/** A factory for reactive {@link Preference} objects. */
public final class RxSharedPreferences {
    /** Create an instance of {@link RxSharedPreferences} for {@code preferences}. */
     @NonNull
    public static RxSharedPreferences create(@NonNull SharedPreferences preferences) {
        return new RxSharedPreferences(preferences);
    }

    private final SharedPreferences preferences;
    private final Observable<String> keyChanges;

    private RxSharedPreferences(final SharedPreferences preferences) {
        this.preferences = preferences;
        this.keyChanges = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                final OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
                        subscriber.onNext(key);
                    }
                };

                preferences.registerOnSharedPreferenceChangeListener(listener);

                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        preferences.unregisterOnSharedPreferenceChangeListener(listener);
                    }
                }));
            }
        }).share();
    }

    /** Create a boolean preference for {@code key}. Default is {@code null}. */
    public Preference<Boolean> getBoolean(@NonNull String key) {
        return getBoolean(key, null);
    }

    /** Create a boolean preference for {@code key} with a default of {@code defaultValue}. */
    public Preference<Boolean> getBoolean(@NonNull String key, @Nullable Boolean defaultValue) {
        return new Preference<>(preferences, key, defaultValue, BooleanAdapter.INSTANCE, keyChanges);
    }

    /** Create an enum preference for {@code key}. Default is {@code null}. */
    public <T extends Enum<T>> Preference<T> getEnum(@NonNull String key,
                                                     @NonNull Class<T> enumClass) {
        return getEnum(key, null, enumClass);
    }

    /** Create an enum preference for {@code key} with a default of {@code defaultValue}. */
    public <T extends Enum<T>> Preference<T> getEnum(@NonNull String key, @Nullable T defaultValue,
                                                     @NonNull Class<T> enumClass) {
        Preference.Adapter<T> adapter = new EnumAdapter<>(enumClass);
        return new Preference<>(preferences, key, defaultValue, adapter, keyChanges);
    }

    /** Create a float preference for {@code key}. Default is {@code null}. */
    public Preference<Float> getFloat(@NonNull String key) {
        return getFloat(key, null);
    }

    /** Create a float preference for {@code key} with a default of {@code defaultValue}. */
    public Preference<Float> getFloat(@NonNull String key, @Nullable Float defaultValue) {
        return new Preference<>(preferences, key, defaultValue, FloatAdapter.INSTANCE, keyChanges);
    }

    /** Create an integer preference for {@code key}. Default is {@code null}. */
    public Preference<Integer> getInteger(@NonNull String key) {
        return getInteger(key, null);
    }

    /** Create an integer preference for {@code key} with a default of {@code defaultValue}. */
    public Preference<Integer> getInteger(@NonNull String key, @Nullable Integer defaultValue) {
        return new Preference<>(preferences, key, defaultValue, IntegerAdapter.INSTANCE, keyChanges);
    }

    /** Create a long preference for {@code key}. Default is {@code null}. */
    public Preference<Long> getLong(@NonNull String key) {
        return getLong(key, null);
    }

    /** Create a long preference for {@code key} with a default of {@code defaultValue}. */
    public Preference<Long> getLong(@NonNull String key, @Nullable Long defaultValue) {
        return new Preference<>(preferences, key, defaultValue, LongAdapter.INSTANCE, keyChanges);
    }

    /** Create a preference of type {@code T} for {@code key}. Default is {@code null}. */
    public <T> Preference<T> getObject(@NonNull String key, @NonNull Preference.Adapter<T> adapter) {
        return getObject(key, null, adapter);
    }

    /**
     * Create a preference for type {@code T} for {@code key} with a default of {@code defaultValue}.
     */
    public <T> Preference<T> getObject(@NonNull String key, @Nullable T defaultValue,
                                       @NonNull Preference.Adapter<T> adapter) {
        return new Preference<>(preferences, key, defaultValue, adapter, keyChanges);
    }

    /** Create a string preference for {@code key}. Default is {@code null}. */
    public Preference<String> getString(@NonNull String key) {
        return getString(key, null);
    }

    /** Create a string preference for {@code key} with a default of {@code defaultValue}. */
    public Preference<String> getString(@NonNull String key, @Nullable String defaultValue) {
        return new Preference<>(preferences, key, defaultValue, StringAdapter.INSTANCE, keyChanges);
    }

    /** Create a string set preference for {@code key}. Default is an empty set. */
    public Preference<Set<String>> getStringSet(@NonNull String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /** Create a string set preference for {@code key} with a default of {@code defaultValue}. */
    public Preference<Set<String>> getStringSet(@NonNull String key,
                                                @NonNull Set<String> defaultValue) {
        return new Preference<>(preferences, key, defaultValue, StringSetAdapter.INSTANCE, keyChanges);
    }

    public boolean hasKey(String key) {
        return preferences.contains(key);
    }
}
