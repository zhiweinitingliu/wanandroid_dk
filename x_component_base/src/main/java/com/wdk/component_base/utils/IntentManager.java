package com.wdk.component_base.utils;

import android.content.Context;
import android.content.Intent;

public class IntentManager {

    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
