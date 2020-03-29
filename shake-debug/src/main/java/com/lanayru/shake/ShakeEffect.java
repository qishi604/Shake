package com.lanayru.shake;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.lanayru.shake.lib.R;

import static android.os.VibrationEffect.DEFAULT_AMPLITUDE;

/**
 * @author zhengqi
 * @since 2020/3/29
 **/
public class ShakeEffect {

  private static SoundPool sSoundPool;

  private static int sSoundId;

  public static void playSound(Context context) {
    if (null == sSoundPool) {
      AudioAttributes audioAttributes = new AudioAttributes.Builder()
          .setLegacyStreamType(AudioManager.STREAM_MUSIC)
          .build();
      sSoundPool = new SoundPool.Builder()
          .setMaxStreams(1)
          .setAudioAttributes(audioAttributes)
          .build();

      sSoundPool.load(context, R.raw.shake_sound, 1);

      sSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
        @Override public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
          if (status != 0) {
            return;
          }
          sSoundId = sampleId;
          play();
        }
      });
    } else {
      play();
    }
  }

  private static void play() {
    if (0 != sSoundId) {
      sSoundPool.play(sSoundId, 0.5f, 0.5f, 0, 0, 1f);
    }
  }

  public static void vibrate(Context context) {
    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    vibrator.vibrate(300L);
    //long[] patten = { 200L, 100L,200L };
    //vibrator.vibrate(200L, -1);
    //VibrationEffect effect = VibrationEffect.createOneShot(1000L, DEFAULT_AMPLITUDE);
    //vibrator.vibrate(effect);
  }
}
