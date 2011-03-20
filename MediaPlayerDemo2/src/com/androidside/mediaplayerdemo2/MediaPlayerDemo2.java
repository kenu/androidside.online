package com.androidside.mediaplayerdemo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MediaPlayerDemo2 extends Activity implements View.OnClickListener {
    MediaPlayer mp = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button playButton = (Button) findViewById(R.id.play);
        Button stopButton = (Button) findViewById(R.id.stop);
        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.play : playAudio(); break;
        case R.id.stop : stopAudio(); break;
        }
        
    }

    
    private void playAudio() {
        
        EditText audioUrlText = (EditText) findViewById(R.id.audio_url);
        
        String audioUrl = audioUrlText.getText().toString();
        try {
            URLConnection conn = new URL(audioUrl).openConnection();
            conn.setRequestProperty("Content-Type","audio/mpeg"); 
            InputStream is = conn.getInputStream();

            File file = new File(this.getCacheDir(), "audio");
            FileOutputStream fos = new FileOutputStream(file);
            
            byte buf[] = new byte[16 * 1024];

            do {
                int numread = is.read(buf);
                if (numread <= 0)
                    break;
                fos.write(buf, 0, numread);
            } while (true);
            
            fos.flush();
            fos.close();
            
            
            mp = new MediaPlayer();

            MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    // free up media player
                    mp.release();

                    Log.i("MediaPlayer.OnCompletionListener",
                            "MediaPlayer Released");
                }
            };
            
            MediaPlayer.OnInfoListener li = new MediaPlayer.OnInfoListener() {
                
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    Log.d("my", "onInfo : " + mp.getDuration());
                    return false;
                }
            };
            mp.setOnInfoListener(li);
            mp.setOnCompletionListener(listener);
            
            FileInputStream fis = new FileInputStream(file);
            mp.setDataSource(fis.getFD());
            mp.prepare();
            mp.start();
            Log.d("my", "duration : " + mp.getDuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void stopAudio() {
        if (mp != null) mp.release();
    }

}