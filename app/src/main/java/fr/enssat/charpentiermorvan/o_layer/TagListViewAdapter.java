package fr.enssat.charpentiermorvan.o_layer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * ArrayAdapter to display a set of Tag objects
 */

public class TagListViewAdapter extends ArrayAdapter<Tag> {
    private Context context;
    private ArrayList<Tag> tags;

    /**
     * @param mContext the context of the application
     * @param tags an ArrayList of Tag objects
     */
    public TagListViewAdapter(Context mContext, ArrayList<Tag> tags) {
        super(mContext, -1, tags);

        this.context = mContext;
        this.tags = tags;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the tag_list_item layout
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.tag_list_item, parent, false);

        // Set the label of the tag
        TextView labelTextView = itemView.findViewById(R.id.label);
        labelTextView.setText(tags.get(position).getLabel());

        // Set the timestamp of the tag
        TextView timestampTextView = itemView.findViewById(R.id.timestamp);
        timestampTextView.setText(Integer.toString(tags.get(position).getTimeStamp()) + "s");

        return itemView;
    }
}
