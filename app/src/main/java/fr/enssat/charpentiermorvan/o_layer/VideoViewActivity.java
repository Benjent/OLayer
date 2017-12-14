package fr.enssat.charpentiermorvan.o_layer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by benjent on 07/12/17.
 */

public class VideoViewActivity extends Activity {

    ProgressDialog pDialog;
    VideoView videoView;
    WebView wikiView;

    // Insert your Video URL
    String VideoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview_main);
      
        Intent intent = getIntent();
        VideoURL = intent.getStringExtra(MainActivity.VIDEO_URL_MESSAGE);

        // ********** VIDEO ********** //
        videoView = (VideoView) findViewById(R.id.VideoView);

        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(VideoViewActivity.this);
        pDialog.setTitle("Android Video Streaming Tutorial");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
            }
        });

        // ********** WIKI ********** //

        wikiView = (WebView) findViewById(R.id.WikiView);
        wikiView.getSettings().setJavaScriptEnabled(true);

        wikiView.setWebViewClient(new WebViewClient());

        //wikiView.loadUrl("https://en.wikipedia.org/wiki/Main_Page");
        wikiView.loadUrl("https://www.google.fr");

        //setContentView(wikiView);

    }
}
