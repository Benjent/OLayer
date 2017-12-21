package fr.enssat.charpentiermorvan.o_layer;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Parses a JSON file and returns an ArrayList of VideoMetadata
 */

public class VideoMetadataParser {
    private Context mContext;

    /**
     * @param context the context of the application
     */
    public VideoMetadataParser(Context context) {
        this.mContext = context;
    }

    /**
     * @return an ArrayList of VideoMetadata objects
     */
    public ArrayList<VideoMetadata> parse() {
        ArrayList<VideoMetadata> videoMetadataArray = new ArrayList<>();

        try {
            // Read the JSON files
            InputStream is = mContext.getResources().openRawResource(R.raw.videos);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                is.close();
            } catch(Exception e) {
                e.printStackTrace();
            }

            // Create a JSONObject from the JSON file
            String metadataJSON = writer.toString();
            JSONObject metadata = new JSONObject(metadataJSON);

            // Extract the video array
            JSONArray videos = metadata.getJSONArray("videos");

            // Extract each video and create VideoMetadata objects
            for (int i = 0; i < videos.length(); i++) {
                JSONObject videoMetadataJSON = (JSONObject) videos.get(i);

                VideoMetadata videoMetadata = new VideoMetadata(videoMetadataJSON);

                videoMetadataArray.add(videoMetadata);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return videoMetadataArray;
    }
}
