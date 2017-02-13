package com.rxjava.demos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.anshul.rxdownloader.R;

/**
 * Created by anshul on 29/1/17.
 */
public class FbLiveVideoNormalDemoActivity extends AppCompatActivity {
  //private final int MINIMUM_DURATION_BETWEEN_EMOTICONS = 300; // in milliseconds

  @BindView(R.id.custom_view)
  EmoticonsView emoticonsView;

  private Animation emoticonClickAnimation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Set the view and do all the necessary init.
    setContentView(R.layout.activity_fb_live_video_reaction_demo);
    ButterKnife.bind(this);
    emoticonsView.initView(this);
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
  }

  @OnClick({ R.id.like_emoticon, R.id.love_emoticon, R.id.haha_emoticon, R.id.wow_emoticon, R.id.sad_emoticon, R.id.angry_emoticon })
  public void onClick(View view) {
    //0. // TODO: 2017/2/13 add 300ms delay
    //1.animation in bottom bar
    emoticonClickAnimation = AnimationUtils.loadAnimation(FbLiveVideoNormalDemoActivity
        .this, R.anim.emoticon_click_animation);
    view.startAnimation(emoticonClickAnimation);

    switch (view.getId()) {
      case R.id.like_emoticon:
        //2.animation in EmotionsView flow
        emoticonsView.addView(Emoticons.LIKE);
        break;
      case R.id.love_emoticon:
        //2.animation in EmotionsView flow
        emoticonsView.addView(Emoticons.LOVE);
        break;
      case R.id.haha_emoticon:
        //2.animation in EmotionsView flow
        emoticonsView.addView(Emoticons.HAHA);
        break;
      case R.id.wow_emoticon:
        //2.animation in EmotionsView flow
        emoticonsView.addView(Emoticons.WOW);
        break;
      case R.id.sad_emoticon:
        //2.animation in EmotionsView flow
        emoticonsView.addView(Emoticons.SAD);
        break;
      case R.id.angry_emoticon:
        //2.animation in EmotionsView flow
        emoticonsView.addView(Emoticons.ANGRY);
        break;
    }
  }
}
