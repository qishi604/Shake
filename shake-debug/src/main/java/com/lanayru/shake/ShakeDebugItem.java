package com.lanayru.shake;

/**
 * @author zhengqi
 * @since 2020/3/28
 **/
public class ShakeDebugItem {

  public String title;

  public ShakeItemCallback callback;

  public ShakeDebugItem(String title, ShakeItemCallback callback) {
    this.title = title;
    this.callback = callback;
  }
}
