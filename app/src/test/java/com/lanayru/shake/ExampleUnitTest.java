package com.lanayru.shake;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  @Test public void addition_isCorrect() {
    assertEquals(4, 2 + 2);
  }

  @Test
  public void md5str() {
    String s = "12:8F:FD:82:F9:2C:1D:74:DD:08:C0:25:91:F4:F5:EA";
    System.out.println(s.replaceAll(":", "").toLowerCase());
  }
}