package com.lanayru.shake;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.laynaru.share.qq.QQShareHelper;
import com.tencent.tauth.UiError;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import okio.ByteString;
import okio.Okio;

/**
 * @author zhengqi
 * @since 2020/4/12
 **/
public class ShareActivity extends AppCompatActivity {

  private static final String TAG = "ShareActivity";

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    QQShareHelper.init(getApplication());

    setContentView(R.layout.activity_share);
    TextView tv = findViewById(R.id.tv_content);
    tv.setText(String.format("md5: %s",  getSignatureMd5()));
  }

  private String getSignatureMd5() {
    PackageManager packageManager = getPackageManager();
    try {
      PackageInfo packageInfo =
          packageManager.getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
      if (null != packageInfo && packageInfo.signatures.length > 0) {
        Signature signature = packageInfo.signatures[0];
        byte[] bytes = signature.toByteArray();
        return md5(bytes);
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return "not found";
  }

  private String md5(byte[] bytes) {
    // 用 okhttp 的库来生成 md5
    return ByteString.of(bytes).md5().hex();
  }

  public void normalShare(View view) {
    QQShareHelper.share(this, "hello", "hello world", new com.tencent.tauth.IUiListener() {

      @Override public void onComplete(Object o) {
        Log.i(TAG, "onComplete " + o);
      }

      @Override public void onError(UiError uiError) {
        Log.i(TAG, "onError " + uiError);
      }

      @Override public void onCancel() {
        Log.i(TAG, "onCancel ");
      }
    });
  }
}
