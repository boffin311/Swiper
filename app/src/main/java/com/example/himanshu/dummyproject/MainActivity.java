package com.example.himanshu.dummyproject;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    ImageView image;
    float finalX, initialX,dpToPxl;
    long initialTime;
    LinearLayout.LayoutParams layoutParams;
    int window_x_cord;
    public static final String TAG = "MAIN";
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        image.setOnTouchListener(this);
        //   image.setOnDragListener(this);

        window_x_cord = getWindow().getWindowManager().getDefaultDisplay().getWidth();
     //   Log.d(TAG, "onCreate: "+getWindow().getWindowManager().getDefaultDisplay());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                Log.d(TAG, "onTouch: " + " the action up is encountered" + event.getX());
                break;
            case MotionEvent.ACTION_UP:
                finalX = event.getX();

                initialTime = SystemClock.elapsedRealtime();
                if(finalX>window_x_cord/2) {
                    if (initialX < finalX) {
                        dpToPxl = convertDpTOPxl(this, 60);
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(image, "TranslationX", window_x_cord - dpToPxl);
                        objectAnimator.setDuration(200);
                        objectAnimator.start();
//                    int x = (int) event.getX();
//                    layoutParams = (LinearLayout.LayoutParams) image.getLayoutParams();
//                    Handler handler = new Handler();
//                    while (x < window_x_cord - 60) {
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                            //    initialTime = SystemClock.elapsedRealtime();
//                                layoutParams.leftMargin =100;
//                                image.setLayoutParams(layoutParams);
//                            }
//                        }, 100);
//
////                              initialTime=SystemClock.elapsedRealtime();
////                              layoutParams.leftMargin=10;
////                              image.setLayoutParams(layoutParams);
//
//
//                    }
//
////                    layoutParams.leftMargin = x_cord - 60;
                        //      image.setLayoutParams(layoutParams);
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int x_cord = (int) event.getRawX();
                if (x_cord > window_x_cord)
                    x_cord = window_x_cord;

                break;
            //  case MotionEvent.Action_


        }
        return true;
    }

    public  float  convertDpTOPxl(Context context,float dp)
    {
        return dp*(context.getResources().getDisplayMetrics().densityDpi/(DisplayMetrics.DENSITY_DEFAULT));
    }
}
