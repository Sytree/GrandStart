package com.example.huii.data.local.share;//package com.example.data.cache.share;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Created by huii on 16/7/7.
// */
//@Module
//public class ShareModule {
//
//    SharedPreferences getPreference(Context mContext) {
//        return mContext.getSharedPreferences("lawyerclient",0);
//    }
//
//    @Singleton
//    @Provides
//    RxSharedPreferences provideRxPreference(Context context) {
//        return RxSharedPreferences.create(getPreference(context));
//    }
//}
