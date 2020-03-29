package com.lanayru.shake;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //setDebugs();
  }

  //private void setDebugs() {
  //  if (!BuildConfig.DEBUG) {
  //    return;
  //  }
  //  ShakeDebugItem[] items = new ShakeDebugItem[] {
  //      new ShakeDebugItem("Hello", ()-> toast("hello")),
  //      new ShakeDebugItem("Show Main", () -> toast("main")),
  //      new ShakeDebugItem("Test", () -> toast("Test")),
  //      new ShakeDebugItem("Unbind", () -> toast("Unbind")),
  //  };
  //  ShakeDebug.attach(this, items);
  //}

  private void toast(CharSequence s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }
}
