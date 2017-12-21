package fr.enssat.charpentiermorvan.o_layer;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by benjent on 07/12/17.
 */

public class VideoMetadata implements Parcelable {

    private String name, url, pageUrl, thumbnailUrl;
    private ArrayList<Tag> tags;

    public VideoMetadata(JSONObject videoMetadata) {
        this.name = null;
        this.url = null;
        this.pageUrl = null;
        this.tags = new ArrayList<>();

        try {
            setName(videoMetadata.getString("name"));
            setUrl(videoMetadata.getString("url"));
            setPageUrl(videoMetadata.getString("pageUrl"));

            // Thumbnail
            JSONObject thumbnail = videoMetadata.getJSONObject("thumbnail");
            setThumbnailUrl(thumbnail.getString("url"));

            JSONArray videoTags = videoMetadata.getJSONArray("tags");

            for (int i = 0; i < videoTags.length(); i++) {
                JSONObject videoTag = (JSONObject) videoTags.get(i);
                this.tags.add(new Tag(videoTag.getString("label"), videoTag.getInt("timestamp"), videoTag.getString("url")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private VideoMetadata(Parcel in) {
        this.tags = new ArrayList<>();

        this.setName(in.readString());
        this.setUrl(in.readString());
        this.setPageUrl(in.readString());
        this.setThumbnailUrl(in.readString());
        in.readTypedList(this.tags, Tag.CREATOR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() { return this.url; }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() { return this.pageUrl; }

    public void setThumbnailUrl(String url) {
        this.thumbnailUrl = url;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getName());
        parcel.writeString(this.getUrl());
        parcel.writeString(this.getPageUrl());
        parcel.writeString(this.getThumbnailUrl());
        parcel.writeTypedList(this.getTags());
    }

    public static final Parcelable.Creator<VideoMetadata> CREATOR = new Parcelable.Creator<VideoMetadata>() {
        public VideoMetadata createFromParcel(Parcel in) {
            return new VideoMetadata(in);
        }

        public VideoMetadata[] newArray(int size) {
            return new VideoMetadata[size];
        }
    };
}
