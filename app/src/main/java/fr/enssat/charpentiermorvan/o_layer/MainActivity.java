package fr.enssat.charpentiermorvan.o_layer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;

public class MainActivity extends Activity {

    static String VIDEO_URL_MESSAGE = "video_url";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_main);

        VideoMetadataParser parser = new VideoMetadataParser(this);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.MyButton);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        VideoViewActivity.class);
                myIntent.putExtra(VIDEO_URL_MESSAGE, "http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
                startActivity(myIntent);
            }
        });

        ListView videoListView = (ListView) findViewById(R.id.VideoListView);

        VideoListViewAdapter videoListViewAdapter = new VideoListViewAdapter(this, parser.parse());

        videoListView.setAdapter(videoListViewAdapter);
    }
}
