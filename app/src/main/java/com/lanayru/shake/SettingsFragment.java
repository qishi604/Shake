package com.lanayru.shake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author zhengqi
 * @since 2020/4/1
 **/
public class SettingsFragment extends Fragment {

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    // 注意 attachToRoot 一定要 false，这个坑死人啊
    return inflater.inflate(R.layout.fragment_settings, container, false);
  }
}
