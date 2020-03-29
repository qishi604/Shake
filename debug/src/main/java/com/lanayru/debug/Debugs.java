package com.lanayru.debug;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.lanayru.shake.ShakeDebug;
import com.lanayru.shake.ShakeDebugItem;

/**
 * @author zhengqi
 * @since 2020/3/29
 **/
public final class Debugs {

  private static final String MAIN_CLASS_NAME = "com.lanayru.shake.MainActivity";

  private static Application sApp;

  private static boolean mIsAttach = false;

  public static void setup(Application app) {
    sApp = app;
    app.registerActivityLifecycleCallbacks(lifecycleCallbacks);
  }

  private static Application.ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacksAdapter() {
    @Override public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
      if (MAIN_CLASS_NAME.equals(activity.getClass().getName())) {
        if (activity instanceof FragmentActivity) {
          init((FragmentActivity) activity);
        }
      }
    }

    @Override public void onActivityDestroyed(@NonNull Activity activity) {
      if (MAIN_CLASS_NAME.equals(activity.getClass().getName())) {
        mIsAttach = false;
      }
    }
  };

  private static void init(FragmentActivity activity) {
    if (mIsAttach) {
      return;
    }
    mIsAttach = true;
    ShakeDebugItem[] items = new ShakeDebugItem[] {
        new ShakeDebugItem("Hello", ()-> toast("hello")),
        new ShakeDebugItem("Show Main", () -> toast("main")),
        new ShakeDebugItem("Test", () -> toast("Test")),
        new ShakeDebugItem("Show Settings", () -> showActivity("com.lanayru.shake.SettingsActivity")),
    };
    ShakeDebug.attach(activity, items);
  }

  private static void toast(CharSequence s) {
    Toast.makeText(sApp, s, Toast.LENGTH_SHORT).show();
  }

  private static void showActivity(String activityClass) {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setClassName(sApp.getPackageName(), activityClass);
    sApp.startActivity(intent);
  }
}
