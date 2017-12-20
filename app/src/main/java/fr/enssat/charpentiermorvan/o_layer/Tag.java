package fr.enssat.charpentiermorvan.o_layer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benjent on 18/12/17.
 */

public class Tag implements Parcelable {

    String label;
    Integer timeStamp;

    public Tag(String label, Integer timeStamp) {
        this.label = label;
        this.timeStamp = timeStamp;
    }

    private Tag(Parcel in) {
        this.label = in.readString();
        this.timeStamp = in.readInt();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getLabel());
        parcel.writeInt(this.getTimeStamp());
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
