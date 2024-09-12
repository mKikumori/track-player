package com.example.trackplayer;

import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

// Test
public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an instance of MediaPlayer for audio playback
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

        // register all the buttons using their appropriate IDs
        Button bPlay = findViewById(R.id.playButton);
        Button bPause = findViewById(R.id.pauseButton);
        Button bStop = findViewById(R.id.stopButton);

        // handle the play button to start audio playback
        bPlay.setOnClickListener(view -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(), "Playback started", Toast.LENGTH_SHORT).show();
            }
        });

        // handle the pause button to pause the MediaPlayer instance
        bPause.setOnClickListener(view -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                Toast.makeText(getApplicationContext(), "Playback paused", Toast.LENGTH_SHORT).show();
            }
        });

        // handle the stop button to stop playing and prepare the MediaPlayer instance for the next play
        bStop.setOnClickListener(view -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                try {
                    // after stopping the MediaPlayer instance, it needs to be prepared for the next playback
                    mediaPlayer.prepare();
                    Toast.makeText(getApplicationContext(), "Playback stopped", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resource when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
