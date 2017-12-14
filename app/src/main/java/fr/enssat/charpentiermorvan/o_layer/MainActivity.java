package fr.enssat.charpentiermorvan.o_layer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    static String VIDEO_URL_MESSAGE = "video_url";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_main);

        VideoMetadataParser parser = new VideoMetadataParser(this);

        final ArrayList<VideoMetadata> videosMetadata = parser.parse();

        ListView videoListView = (ListView) findViewById(R.id.VideoListView);
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent myIntent = new Intent(MainActivity.this,
                        VideoViewActivity.class);
                myIntent.putExtra(VIDEO_URL_MESSAGE, videosMetadata.get(position).getUrl());
                startActivity(myIntent);
            }
        });

        VideoListViewAdapter videoListViewAdapter = new VideoListViewAdapter(this, videosMetadata);

        videoListView.setAdapter(videoListViewAdapter);
    }
}
