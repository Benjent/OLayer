package fr.enssat.charpentiermorvan.o_layer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by benjent on 07/12/17.
 */

public class VideoViewActivity extends Activity {

    ProgressDialog pDialog;
    VideoView videoView;
    WebView wikiView;

    VideoMetadata videoMetadata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview_main);
      
        Intent intent = getIntent();
        videoMetadata = (VideoMetadata) intent.getParcelableExtra(MainActivity.VIDEO_METADATA_MESSAGE);

        // ********** VIDEO ********** //
        videoView = findViewById(R.id.VideoView);

        // Prepare wiki view
        wikiView = (WebView) findViewById(R.id.WikiView);
        wikiView.getSettings().setJavaScriptEnabled(true);
        wikiView.setWebViewClient(new WebViewClient());

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(videoMetadata.getUrl());
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
                videoView.start();
            }
        });

        // ********** TAGS ********** //
        ListView tagListView = (ListView) findViewById(R.id.tagListView);

        tagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                videoMetadata.getTags().get(position).getLabel();
                Log.i("Tag clicked", videoMetadata.getTags().get(position).getLabel());
                Log.i("Tag clicked", videoMetadata.getTags().get(position).getTimeStamp().toString());

                wikiView.loadUrl("https://en.wikipedia.org/wiki/" + videoMetadata.getTags().get(position).getLabel());
            }
        });

        Log.d("TAGS", Integer.toString(videoMetadata.getTags().size()));
        for (Tag tag : videoMetadata.getTags()) {
            Log.d("TAG", tag.getLabel());
        }
        TagListViewAdapter tagListViewAdapter = new TagListViewAdapter(this, videoMetadata.getTags());
        tagListView.setAdapter(tagListViewAdapter);

        // ********** WIKI ********** //

        wikiView.loadUrl("https://en.wikipedia.org/wiki/Big_Buck_Bunny");

        //setContentView(wikiView);

    }
}
