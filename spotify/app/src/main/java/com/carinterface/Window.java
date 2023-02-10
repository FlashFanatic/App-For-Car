package com.carinterface;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.spotify.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Window extends MainActivity{


    // declaring required variables
    private Context context;
    private View mView;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private LayoutInflater layoutInflater;






    public Window(Context context){
        this.context=context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // set the layout parameters of the window
            mParams = new WindowManager.LayoutParams(
                    // Shrink the window to wrap the content rather
                    // than filling the screen
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    // Display it on top of other application windows
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    // Don't let it grab the input focus
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    // Make the underlying application window visible
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT);

        }
        // getting a LayoutInflater
        layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        // inflating the view with the custom layout we created
        mView = layoutInflater.inflate(R.layout.popup_window, null);
        // set onClickListener on the remove button, which removes
        // the view from the window
        AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService(AUDIO_SERVICE);

        mView.findViewById(R.id.window_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });
        mView.findViewById(R.id.window_closes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            }
        });
        final boolean[] fine = {true};
        mView.findViewById(R.id.toggleButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (fine[0] == true) {


                    // Build the intent.
                    // Build the intent.
                    Uri location = Uri.parse("waze://");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    //ALLOWS TO USE STARTACTIVITY IN SEPERATE TASK
                    mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //ALLOWS TO USE STARTACTIVITY IN SEPERATE TASK

// Try to invoke the intent.
                    try {
                        context.startActivity(mapIntent);
                    } catch (ActivityNotFoundException e) {
                        // Define what your app should do if no activity can handle the intent.
                    }
                    fine[0] = false;
                }
                else{

                    Intent intent = new Intent(context,MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    fine[0] = true;

                }


            }
        });

        // Define the position of the
        // window within the screen
        mParams.gravity = Gravity.LEFT;
        mWindowManager = (WindowManager)context.getSystemService(WINDOW_SERVICE);

    }

    public void open() {

        try {
            // check if the view is already
            // inflated or present in the window
            if(mView.getWindowToken()==null) {
                if(mView.getParent()==null) {
                    mWindowManager.addView(mView, mParams);
                }
            }
        } catch (Exception e) {
            Log.d("Error1",e.toString());
        }

    }

    public void close() {

        try {
            // remove the view from the window
            ((WindowManager)context.getSystemService(WINDOW_SERVICE)).removeView(mView);
            // invalidate the view
            mView.invalidate();
            // remove all views
            ((ViewGroup)mView.getParent()).removeAllViews();

            // the above steps are necessary when you are adding and removing
            // the view simultaneously, it might give some exceptions
        } catch (Exception e) {
            Log.d("Error2",e.toString());
        }
    }
}
