package com.lanayru.shake;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.SensorManager;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

/**
 * @author zhengqi
 * @since 2020/3/28
 **/
public class ShakeDebug {

  private Context mContext;

  private Context mApp;

  private ShakeDetector mShakeDetector;

  private SensorManager mSensorManager;

  private AlertDialog mDialog;

  private ShakeDebugItem[] mItems;

  public static void attach(FragmentActivity context, ShakeDebugItem[] items) {
    new ShakeDebug(context, items);
  }

  private ShakeDebug(FragmentActivity context, ShakeDebugItem[] items) {
    mContext = context;
    mApp = context.getApplication();
    mItems = items;
    mShakeDetector = new ShakeDetector(() -> {
      ShakeEffect.playSound(mApp);
      ShakeEffect.vibrate(mApp);
      showChoices();
    });
    mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

    ViewLessFragment fragment = new ViewLessFragment(new ActivityFragmentLifecycle() {
      @Override public void onStart() {
        mShakeDetector.start(mSensorManager);
      }

      @Override public void onStop() {
        mShakeDetector.stop();
        hideDialog();
      }

      @Override public void onDestroy() {
        hideDialog();
      }
    });
    context.getSupportFragmentManager().beginTransaction().add(fragment, "ShakeDebug").commitAllowingStateLoss();
  }

  private void showChoices() {
    if (null == mDialog) {
      final int n = mItems.length;
      final String[] s = new String[n];
      for (int i = 0; i < n; i++) {
        s[i] = mItems[i].title;
      }

      AlertDialog dialog =
          new AlertDialog.Builder(mContext).setItems(s, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
              mItems[which].callback.call();
            }
          }).create();
      mDialog = dialog;
      mDialog.setCanceledOnTouchOutside(false);
    }

    if (!mDialog.isShowing()) {
      mDialog.show();
    }
  }

  private void hideDialog() {
    if (null != mDialog && mDialog.isShowing()) {
      mDialog.dismiss();
    }
  }
}
