package com.rxjava.demos;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.anshul.rxdownloader.R;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by anshul on 2/2/17.
 */

public class EmoticonsView extends View {
  private Paint mPaint;
  private Path mAnimPath;
  private Matrix mMatrix;
  private Bitmap mLike48, mLove48, mHaha48, mWow48, mSad48, mAngry48;

  private ArrayList<LiveEmoticon> mLiveEmoticons = new ArrayList<>();
  private final int X_CORDINATE_STEP = 8, Y_CORDINATE_OFFSET = 100, Y_CORDINATE_RANGE = 200;
  private int mScreenWidth;

  public EmoticonsView(Context activity) {
    super(activity);

  }

  public EmoticonsView(Context activity, AttributeSet attrs) {
    super(activity, attrs);
  }

  public EmoticonsView(Context activity, AttributeSet attrs, int defStyleAttr) {
    super(activity, attrs, defStyleAttr);
  }

  public void initView(Activity activity) {

    DisplayMetrics displayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    mScreenWidth = displayMetrics.widthPixels;
    mPaint = new Paint();

    mAnimPath = new Path();
    mMatrix = new Matrix();
    Resources res = getResources();
    //Like emoticons
    mLike48 = BitmapFactory.decodeResource(res, R.drawable.like_48);
    //Love emoticons
    mLove48 = BitmapFactory.decodeResource(res, R.drawable.love_48);
    //Haha emoticons
    mHaha48 = BitmapFactory.decodeResource(res, R.drawable.haha_48);
    //Wow emoticons
    mWow48 = BitmapFactory.decodeResource(res, R.drawable.wow_48);
    //Sad emoticons
    mSad48 = BitmapFactory.decodeResource(res, R.drawable.sad_48);
    //Angry emoticons
    mAngry48 = BitmapFactory.decodeResource(res, R.drawable.angry_48);
  }

  protected void onDraw(Canvas canvas) {
    canvas.drawPath(mAnimPath, mPaint);
    drawAllLiveEmoticons(canvas);
  }

  private void drawAllLiveEmoticons(Canvas canvas) {
    ListIterator<LiveEmoticon> iterator = mLiveEmoticons.listIterator();
    while (iterator.hasNext()) {
      Object object = iterator.next();

      LiveEmoticon liveEmoticon = (LiveEmoticon) object;
      Integer xCoordinate = liveEmoticon.getxCordinate() - X_CORDINATE_STEP;
      Integer yCoordinate = liveEmoticon.getyCordinate();
      liveEmoticon.setxCordinate(xCoordinate);
      if (xCoordinate > 0) {
        mMatrix.reset();
        mMatrix.postTranslate(xCoordinate, yCoordinate);
        resizeImageSizeBasedOnXCoordinates(canvas, liveEmoticon);
        invalidate();
      } else {
        iterator.remove();
      }
    }
  }

  private void resizeImageSizeBasedOnXCoordinates(Canvas canvas, LiveEmoticon liveEmoticon) {
    if (liveEmoticon == null) {
      return;
    }

    int xCoordinate = liveEmoticon.getxCordinate();
    Bitmap bitMap48 = null;
    Bitmap scaled = null;

    Emoticons emoticons = liveEmoticon.getEmoticons();
    if (emoticons == null) {
      return;
    }

    switch (emoticons) {
      case LIKE:
        bitMap48 = mLike48;
        break;
      case LOVE:
        bitMap48 = mLove48;
        break;
      case HAHA:
        bitMap48 = mHaha48;
        break;
      case WOW:
        bitMap48 = mWow48;
        break;
      case SAD:
        bitMap48 = mSad48;
        break;
      case ANGRY:
        bitMap48 = mAngry48;
        break;
    }

    if (xCoordinate > mScreenWidth / 2) {
      canvas.drawBitmap(bitMap48, mMatrix, null);
    } else if (xCoordinate > mScreenWidth / 4) {
      scaled = Bitmap.createScaledBitmap(bitMap48, 3 * bitMap48.getWidth() / 4, 3 * bitMap48.getHeight() / 4, false);
      canvas.drawBitmap(scaled, mMatrix, null);
    } else {
      scaled = Bitmap.createScaledBitmap(bitMap48, bitMap48.getWidth() / 2, bitMap48.getHeight() / 2, false);
      canvas.drawBitmap(scaled, mMatrix, null);
    }
  }

  public void addView(Emoticons emoticons) {
    int startXCoordinate = mScreenWidth;
    int startYCoordinate = new Random().nextInt(Y_CORDINATE_RANGE) + Y_CORDINATE_OFFSET;
    LiveEmoticon liveEmoticon = new LiveEmoticon(emoticons, startXCoordinate, startYCoordinate);
    mLiveEmoticons.add(liveEmoticon);
    invalidate();
  }
}