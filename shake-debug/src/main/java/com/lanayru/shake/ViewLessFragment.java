package com.lanayru.shake;

import androidx.fragment.app.Fragment;

/**
 * @author zhengqi
 * @since 2020/3/28
 **/
public class ViewLessFragment extends Fragment {

  private ActivityFragmentLifecycle mLifecycle;

  public ViewLessFragment(ActivityFragmentLifecycle mLifecycle) {
    super();
    this.mLifecycle = mLifecycle;
  }

  @Override public void onStart() {
    super.onStart();
    mLifecycle.onStart();
  }

  @Override public void onStop() {
    super.onStop();
    mLifecycle.onStop();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mLifecycle.onDestroy();
  }
}
