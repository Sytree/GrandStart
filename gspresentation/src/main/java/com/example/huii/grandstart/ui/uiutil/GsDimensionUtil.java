package com.example.huii.grandstart.ui.uiutil;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by huii on 17/1/11.
 */
public class GsDimensionUtil {
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
}
