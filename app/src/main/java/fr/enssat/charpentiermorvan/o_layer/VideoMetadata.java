package fr.enssat.charpentiermorvan.o_layer;

import android.os.Parcel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * Created by benjent on 07/12/17.
 */

public class VideoMetadata {

    private String name;
    private String url;
    private String thumbnailUrl;
    private HashMap<String, Integer> tags;

    public VideoMetadata(JSONObject videoMetadata) {
        this.name = null;
        this.tags = new HashMap<>();

        try {
            setName(videoMetadata.getString("name"));
            setUrl(videoMetadata.getString("url"));

            // Thumbnail
            JSONObject thumbnail = videoMetadata.getJSONObject("thumbnail");
            setThumbnailUrl(thumbnail.getString("url"));

            JSONArray videoTags = videoMetadata.getJSONArray("tags");

            for (int i = 0; i < videoTags.length(); i++) {
                JSONObject videoTag = (JSONObject) videoTags.get(i);
                this.tags.put(videoTag.getString("label"), videoTag.getInt("timestamp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getTags() {
        return tags;
    }

    public void addTag(String label, Integer timestamp) {
        this.tags.put(label, timestamp);
    }

    public void setUrl(String uri) {
        this.url = uri;
    }

    public String getUrl() { return this.url; }

    public void setThumbnailUrl(String url) {
        this.thumbnailUrl = url;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
}
