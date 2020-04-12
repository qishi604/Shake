package com.lanayru;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.lanayru.shake.R;

/**
 * @author zhengqi
 * @since 2020/4/1
 **/
public class FragmentContainerActivity extends AppCompatActivity {

  private static final int FRAGMENT_ID = 0x888;

  private static final String EXTRA_FRAGMENT_CLASS = "EXTRA_FRAGMENT_CLASS";

  private static final String EXTRA_FRAGMENT_NAME = "EXTRA_FRAGMENT_NAME";

  public static void show(Context context, Class<? extends Fragment> fragmentClass, Bundle args) {
    Intent intent = new Intent(context, FragmentContainerActivity.class);
    if (!(context instanceof Activity)) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    if (null == args) {
      args = new Bundle();
    }
    args.putSerializable(EXTRA_FRAGMENT_CLASS, fragmentClass);
    intent.putExtras(args);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FrameLayout frameLayout = new FrameLayout(this);
    frameLayout.setId(FRAGMENT_ID);
    setContentView(frameLayout);
    if (null == savedInstanceState) {
      addFragment();
    }
  }

  private void addFragment() {
    String fname;
    Class clz = (Class) getIntent().getSerializableExtra(EXTRA_FRAGMENT_CLASS);
    if (null != clz) {
      fname = clz.getName();
    } else {
      fname = getIntent().getStringExtra(EXTRA_FRAGMENT_NAME);
    }

    if (null != fname) {

      Fragment f = null;
      try {
        f = Fragment.instantiate(this, fname, getIntent().getExtras());
      } catch (Throwable e) {
        e.printStackTrace();
        finish();
      }
      getSupportFragmentManager().beginTransaction().add(FRAGMENT_ID, f).commit();
    }
  }
}
