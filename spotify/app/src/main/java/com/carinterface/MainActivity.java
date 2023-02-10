package com.carinterface;




import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.spotify.R;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;



public class MainActivity extends AppCompatActivity {

    private VideoView videoBG;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    Button openMenu;


    private void waze(View view) {
        // Build the intent.
        Uri location = Uri.parse("waze://");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

// Try to invoke the intent.
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.car_jamz:
                        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:5ghhLvRtFZOpP3Vg8eBEUp");
                        return true;
                    case R.id.love_fi:
                        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:7oHS0kpeukSeE2cGTEJpUV");
                        return true;
                    case R.id.gen:
                        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:7tnVi1kcB696iGu6flTQat");
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();

    }
    private void showPopups(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.vids, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.lofi:
                        // Hook up the VideoView to our UI.
                        videoBG = (VideoView) findViewById(R.id.videoView);
//
//        // Build your video Uri
                        Uri uri = Uri.parse("android.resource://" // First start with this,
                                + getPackageName() // then retrieve your package name,
                                + "/" // add a slash,
                                + R.raw.tes_lofi_naud); // and then finally add your video resource. Make sure it is stored
//        // in the raw folder.
//
//        // Set the new Uri to our VideoView
                        //this keeps it so the video doesnt mute backround noise
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            videoBG.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                        }
                        videoBG.setVideoURI(uri);
                        // Start the VideoView
                        videoBG.start();
                        return true;
                    case R.id.blonde:
                        Uri uris = Uri.parse("android.resource://" // First start with this,
                                + getPackageName() // then retrieve your package name,
                                + "/" // add a slash,
                                + R.raw.blonde); // and then finally add your video resource. Make sure it is stored
//        // in the raw folder.
//
//        // Set the new Uri to our VideoView
                        //this keeps it so the video doesnt mute backround noise
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            videoBG.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                        }
                        videoBG.setVideoURI(uris);
                        // Start the VideoView
                        videoBG.start();
                        return true;
                    case R.id.beach:
                        Uri uriszss = Uri.parse("android.resource://" // First start with this,
                                + getPackageName() // then retrieve your package name,
                                + "/" // add a slash,
                                + R.raw.beach); // and then finally add your video resource. Make sure it is stored
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            videoBG.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                        }
                        videoBG.setVideoURI(uriszss);
                        // Start the VideoView
                        videoBG.start();
                        return true;
                    case R.id.carandmoon:
                        Uri urisass = Uri.parse("android.resource://" // First start with this,
                                + getPackageName() // then retrieve your package name,
                                + "/" // add a slash,
                                + R.raw.carandmoon); // and then finally add your video resource. Make sure it is stored
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            videoBG.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                        }
                        videoBG.setVideoURI(urisass);
                        // Start the VideoView
                        videoBG.start();
                        return true;
                    case R.id.gun:
                        Uri urissss = Uri.parse("android.resource://" // First start with this,
                                + getPackageName() // then retrieve your package name,
                                + "/" // add a slash,
                                + R.raw.gun); // and then finally add your video resource. Make sure it is stored
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            videoBG.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                        }
                        videoBG.setVideoURI(urissss);
                        // Start the VideoView
                        videoBG.start();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();

    }

    private static final String CLIENT_ID = "bcfba7e20bd94e8da815e8693384b788";
    private static final String REDIRECT_URI = "com.spotify://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkOverlayPermission();
        startService();
        //Video

        // Hook up the VideoView to our UI.
        videoBG = (VideoView) findViewById(R.id.videoView);
//
//        // Build your video Uri
        Uri uri = Uri.parse("android.resource://" // First start with this,
                + getPackageName() // then retrieve your package name,
                + "/" // add a slash,
                + R.raw.tes_lofi_naud); // and then finally add your video resource. Make sure it is stored
//        // in the raw folder.
//
//        // Set the new Uri to our VideoView
        //this keeps it so the video doesnt mute backround noise
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            videoBG.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
        }
        videoBG.setVideoURI(uri);
        // Start the VideoView
        videoBG.start();


        // Set an OnPreparedListener for our VideoView. For more information about VideoViews,
        // check out the Android Docs: https://developer.android.com/reference/android/widget/VideoView.html
        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                // We want our video to play over and over so we set looping to true.
                mMediaPlayer.setVolume(0f,0f);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.start();
                // We then seek to the current posistion if it has been set and play the video.
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });



        //Menu
        Button drops = findViewById(R.id.button6);
        drops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopups(v);

            }
        });



        TextView textViewbottom = findViewById(R.id.textView2);
        textViewbottom.setSelected(true);





        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);




    }

    @Override
    protected void onStart() {
        super.onStart();
        // We will start writing our code here.
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        connected();


                    }



                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }




    private void connected() {
        // Then we will write some more code here.


        //testing
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //testing


        //mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.duration + " by " + track.artist.name);
                        TextView textview = findViewById(R.id.textView);
                        TextView textview2 = findViewById(R.id.textView2);
                        textview.setText(track.artist.name);
                        textview2.setText(track.name);


                    }
                });

        //start-stop
        ToggleButton Pause = findViewById(R.id.toggleButton);
        Pause.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){

                boolean value = Pause.isChecked();

                if (value == true) {
                    mSpotifyAppRemote.getPlayerApi().pause();

                } else {
                    mSpotifyAppRemote.getPlayerApi().resume();
                }


            }
        });
        //next song
        Button Next = findViewById(R.id.button);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSpotifyAppRemote.getPlayerApi().skipNext();

            }
        });
        //previous song
        Button Previous = findViewById(R.id.button4);
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSpotifyAppRemote.getPlayerApi().skipPrevious();

            }
        });

        //Shuffle
        Button shuffle = findViewById(R.id.toggleButton3);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSpotifyAppRemote.getPlayerApi().toggleShuffle();

            }
        });





        //Menu
        Button drop = findViewById(R.id.button5);
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopup(v);

            }
        });

        //menuend

        //end
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Aaand we will finish off here.
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

        /*================================ Important Section! ================================
    We must override onPause(), onResume(), and onDestroy() to properly handle our
    VideoView.
     */

    @Override
    protected void onPause() {
        super.onPause();
        // Capture the current video position and pause the video.
        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService();
        // Restart the video when resuming the Activity
        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
    //end of video

    // method for starting the service
    public void startService(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // check if the user has already granted
            // the Draw over other apps permission
            if(Settings.canDrawOverlays(this)) {
                // start the service based on the android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(new Intent(this, ForegroundService.class));
                } else {
                    startService(new Intent(this, ForegroundService.class));
                }
            }
        }else{
            startService(new Intent(this, ForegroundService.class));
        }
    }

    // method to ask user to grant the Overlay permission
    public void checkOverlayPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(myIntent);
            }
        }
    }



}


