package fr.enssat.charpentiermorvan.o_layer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by caillou on 12/7/17.
 */

public class VideoListViewAdapter extends ArrayAdapter<VideoMetadata> {
    private Context context;
    private ArrayList<VideoMetadata> metadataArray;

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

        TaskDownloadImage t = new TaskDownloadImage(thumbnailImageView);
        t.execute(metadataArray.get(position).getThumbnailUrl());


        textView.setText(metadataArray.get(position).getName());

        return itemView;
    }
}
