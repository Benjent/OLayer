package fr.enssat.charpentiermorvan.o_layer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ArrayAdapter allowing to list a set of VideoMedata object
 */
public class VideoListViewAdapter extends ArrayAdapter<VideoMetadata> {
    private Context context;
    private ArrayList<VideoMetadata> metadataArray;

    /**
     * @param mContext the context of the application
     * @param metadataArray an ArrayList of VideoMetadata
     */
    public VideoListViewAdapter (Context mContext, ArrayList<VideoMetadata> metadataArray) {
        super(mContext, -1, metadataArray);

        this.context = mContext;
        this.metadataArray = metadataArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.video_list_item, parent, false);

        ImageView thumbnailImageView = itemView.findViewById(R.id.thumbnail);

        TextView textView = (TextView) itemView.findViewById(R.id.name);

        new TaskDownloadImage(thumbnailImageView).execute(metadataArray.get(position).getThumbnailUrl());

        textView.setText(metadataArray.get(position).getName());

        return itemView;
    }
}
