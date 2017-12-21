package fr.enssat.charpentiermorvan.o_layer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
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
 * Activity displaying the enriched video
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

        // Get VideoMetadata object from the Intent
        Intent intent = getIntent();
        videoMetadata = (VideoMetadata) intent.getParcelableExtra(MainActivity.VIDEO_METADATA_MESSAGE);

        // ********** VIDEO ********** //
        videoView = findViewById(R.id.VideoView);

        // Prepare wiki view
        wikiView = (WebView) findViewById(R.id.WikiView);
        wikiView.getSettings().setJavaScriptEnabled(true);
        wikiView.setWebViewClient(new WebViewClient());
        final MediaController mediacontroller;
        try {
            // Start the MediaController
            mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(videoMetadata.getUrl());
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(video);

            videoView.requestFocus();
            videoView.setOnPreparedListener(new OnPreparedListener() {
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                    mediacontroller.setAnchorView(videoView);
                    videoView.start();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        // ********** TAGS ********** //
        ListView tagListView = (ListView) findViewById(R.id.tagListView);

        tagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Tag tag = videoMetadata.getTags().get(position);

                wikiView.loadUrl(tag.getUrl());
                videoView.seekTo(tag.getTimeStamp() * 1000);
            }
        });
        
        TagListViewAdapter tagListViewAdapter = new TagListViewAdapter(this, videoMetadata.getTags());
        tagListView.setAdapter(tagListViewAdapter);

        // ********** WIKI ********** //
        wikiView.loadUrl(videoMetadata.getPageUrl());

        Handler h = new Handler();
        Thread checkVideo = new CheckVideo(videoView,  videoMetadata.getTags(), wikiView, h);
        checkVideo.start();
    }
}

class CheckVideo extends Thread {
    VideoView videoView;
    ArrayList<Tag> tags;
    WebView wikiView;
    Handler h;
    final Index currentTagIndex = new Index();

    CheckVideo(VideoView videoView, ArrayList<Tag> tags, WebView wikiView, Handler h) {
        this.videoView = videoView;
        this.tags = tags;
        this.wikiView = wikiView;
        this.h = h;
    }

    class Index {
        public int i;

        public Index() {
            this.i = 0;
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                sleep(1000);
                int currentTime = this.videoView.getCurrentPosition();
                final Index index = new Index();
                int i = 0;
                for(final Tag tag : this.tags) {
                    if (tag.getTimeStamp() > currentTime / 1000) {
                        break;
                    }
                    this.currentTagIndex.i = i;
                    i++;
                }

                if (this.currentTagIndex.i != i) {
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            wikiView.loadUrl(tags.get(currentTagIndex.i).getUrl());
                        }
                    });
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
