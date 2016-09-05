package com.example.hwhong.videoexplore;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private VideoView display;

    private final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.record);
        display = (VideoView) findViewById(R.id.videoView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //still working on trying to directly display video upon recording
                /*
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                File file = getFilePath();
                Uri uri = Uri.fromFile(file);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                //indicates maximum quality for 1
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                startActivityForResult(intent, REQUEST_CODE);
                */

                Intent intent = new Intent();

                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"), REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            try
            {
                Uri mVideoURI = data.getData();
                display.setVideoURI(mVideoURI);
                display.start();

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private File getFilePath() {
        File folder = new File("sdcard/video");

        if(!folder.exists()) {
            folder.mkdir();
        }

        File vidFile = new File(folder, "sample.mp4");

        return vidFile;
    }
}
