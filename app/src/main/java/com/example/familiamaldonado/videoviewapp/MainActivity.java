package com.example.familiamaldonado.videoviewapp;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button clk;
    VideoView videov;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clk = (Button) findViewById(R.id.button2);
        videov = (VideoView) findViewById(R.id.videoView);
        mediaC = new MediaController(this);
    }

public void videoplay (View v)
{

    String videopath = "android.resource://com.example.familiamaldonado.videoviewapp/"+R.raw.cloud;
    Uri uri = Uri.parse(videopath);
    videov.setVideoURI(uri);
    videov.setMediaController(mediaC);
    mediaC.setAnchorView(videov);


    videov.start();

}
}