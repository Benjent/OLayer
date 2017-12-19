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

    String VideoURL;
    private ArrayList<Tag> tags;
    Button tag1, tag2, tag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoview_main);
      
        Intent intent = getIntent();
        VideoURL = intent.getStringExtra(MainActivity.VIDEO_URL_MESSAGE);

        // ********** VIDEO ********** //
        videoView = (VideoView) findViewById(R.id.VideoView);

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
                videoView.start();
            }
        });

        // ********** TAGS ********** //
        // Todo get the video tags instead of declaring dummy values here
        /*tag1 = (Button) findViewById(R.id.Bunny);
        tag2 = (Button) findViewById(R.id.Butterfly);
        tag3 = (Button) findViewById(R.id.Squirrel);*/

        tags.add(new Tag("Bunny", 10));
        tags.add(new Tag("Butterfly", 80));
        tags.add(new Tag("Squirrel", 222));

        ListView tagListView = (ListView) findViewById(R.id.tagListView);
        tagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                tags.get(position);
            }
        });

        TagListViewAdapter tagListViewAdapter = new TagListViewAdapter(this, tags);
        tagListView.setAdapter(tagListViewAdapter);

        // ********** WIKI ********** //

        wikiView = (WebView) findViewById(R.id.WikiView);
        wikiView.getSettings().setJavaScriptEnabled(true);

        wikiView.setWebViewClient(new WebViewClient());

        //wikiView.loadUrl("https://en.wikipedia.org/wiki/Main_Page");
        wikiView.loadUrl("https://www.google.fr");

        //setContentView(wikiView);

    }
}
