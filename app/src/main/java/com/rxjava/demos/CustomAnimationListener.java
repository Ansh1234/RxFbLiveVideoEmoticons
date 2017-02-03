package com.rxjava.demos;

import android.view.animation.Animation;

/**
 * Created by anshul on 3/2/17.
 */

public class CustomAnimationListener implements Animation.AnimationListener {
  @Override
  public void onAnimationStart(Animation animation) {
    System.out.println("on Animation start");
  }

  @Override
  public void onAnimationEnd(Animation animation) {
    System.out.println("on Animation End");
  }

  @Override
  public void onAnimationRepeat(Animation animation) {

  }
}
