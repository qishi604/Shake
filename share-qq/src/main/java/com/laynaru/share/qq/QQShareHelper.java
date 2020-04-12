package com.laynaru.share.qq;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * https://wiki.connect.qq.com/%E5%88%86%E4%BA%AB%E6%B6%88%E6%81%AF%E5%88%B0qq%EF%BC%88%E6%97%A0%E9%9C%80qq%E7%99%BB%E5%BD%95%EF%BC%89
 * @author zhengqi
 * @since 2020/4/12
 **/
public class QQShareHelper {

  private static Tencent sTencent;

  public static final String APP_ID = "101865767";


  public static void init(Application app) {
    if (null == sTencent) {
      sTencent = Tencent.createInstance(APP_ID, app);
    }
  }

  public static void share(Activity activity, String title, String content, IUiListener listener) {
    final int type = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

    final String url = "https://cn.bing.com";
    final String img = "";

    Bundle bundle = new Bundle();

    bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, type);
    bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
    bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
    bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
    //bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, img);
    sTencent.shareToQQ(activity, bundle, listener);
  }

  public static void shareMiniProgram(Activity activity, String title, String content, IUiListener listener) {
    final int type = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

    final String url = "https://cn.bing.com";
    final String img = "";

    Bundle bundle = new Bundle();

    bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, type);
    bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
    bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, content);
    bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
    //bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, img);
    sTencent.shareToQQ(activity, bundle, listener);
  }
}
