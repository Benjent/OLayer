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
 * Created by caillou on 12/7/17.
 */

public class TagListViewAdapter extends ArrayAdapter<Tag> {
    private Context context;
    private ArrayList<Tag> tags;

    public TagListViewAdapter(Context mContext, ArrayList<Tag> tags) {
        super(mContext, -1, tags);

        this.context = mContext;
        this.tags = tags;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.tag_list_item, parent, false);

        TextView labelTextView = itemView.findViewById(R.id.label);
        TextView timestampTextView = itemView.findViewById(R.id.timestamp);

        labelTextView.setText(tags.get(position).getLabel());

        timestampTextView.setText(Integer.toString(tags.get(position).getTimeStamp()) + "s");

        return itemView;
    }
}
