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

    static String VIDEO_METADATA_MESSAGE = "video_metadata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoMetadataParser parser = new VideoMetadataParser(this);
        ListView videoListView = findViewById(R.id.VideoListView);

        // Parse the JSON file
        final ArrayList<VideoMetadata> videosMetadata = parser.parse();

        // Set onItemClick behavior.
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // Get the matching VideoMetadata object
                VideoMetadata videoMetadata = videosMetadata.get(position);

                // Create an Intent to VideoViewActivity
                Intent myIntent = new Intent(MainActivity.this,
                        VideoViewActivity.class);

                // Put the VideoMedata object as an extra
                myIntent.putExtra(VIDEO_METADATA_MESSAGE, videoMetadata);

                // Start the VideoViewActivity
                startActivity(myIntent);
            }
        });

        // Create an ArrayAdapter using the list of VideoMetadata objects
        VideoListViewAdapter videoListViewAdapter = new VideoListViewAdapter(this, videosMetadata);

        // Set the adapter
        videoListView.setAdapter(videoListViewAdapter);
    }
}
