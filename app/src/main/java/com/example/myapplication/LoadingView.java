package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingView extends View {

    private Bitmap bitmap[] = new Bitmap[2];
    private int padding = 12;
    private int loading = 0 ;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);


        bitmap[0] = BitmapFactory.decodeResource(getResources(), R.drawable.circle1);
        bitmap[1] = BitmapFactory.decodeResource(getResources(), R.drawable.circle2);

        startLoading();


    }


    protected void onDraw(Canvas canvas) {

        if(loading>=3) {
            loading= 0 ;
        }

        for (int i = 0; i < 3; i++) {

            if(loading==i) {
                canvas.drawBitmap(bitmap[1] , i*(bitmap[0].getWidth()+padding),0,null);
            }else{
                canvas.drawBitmap(bitmap[0] , i*(bitmap[0].getWidth()+padding),0,null);
            }

        }

    }


    void startLoading() {


        Timer timer = new Timer();
        final Handler handler = new Handler();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loading++;
                        invalidate();
                    }
                });
            }
        }, 0, 200);

    }


}
