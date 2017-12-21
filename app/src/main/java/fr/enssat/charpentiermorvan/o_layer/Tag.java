package fr.enssat.charpentiermorvan.o_layer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stores to data of a tag
 */
public class Tag implements Parcelable {

    String label, url;
    Integer timeStamp;

    /**
     * @param label the label of the tag
     * @param timeStamp the timestamp, in seconds
     * @param url the url of the page corresponding to that tag
     */
    public Tag(String label, Integer timeStamp, String url) {
        this.label = label;
        this.timeStamp = timeStamp;
        this.url = url;
    }

    /**
     * @param in
     */
    private Tag(Parcel in) {
        this.label = in.readString();
        this.timeStamp = in.readInt();
        this.url = in.readString();
    }

    /**
     * @return the label of the tag
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label of the tag
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the timestamp of the tag in seconds
     */
    public Integer getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timestamp of the tag in seconds
     */
    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the url of the page corresponding to the tag
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url of the page corresponding to the tag
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getLabel());
        parcel.writeInt(this.getTimeStamp());
        parcel.writeString(this.getUrl());
    }

    public static final Parcelable.Creator<Tag> CREATOR =
        new Parcelable.Creator<Tag>() {
            public Tag createFromParcel(Parcel in) {
                return new Tag(in);
            }

            public Tag[] newArray(int size) {
                return new Tag[size];
            }
        };
}
