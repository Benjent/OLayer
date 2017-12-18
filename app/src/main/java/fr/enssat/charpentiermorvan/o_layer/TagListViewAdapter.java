package fr.enssat.charpentiermorvan.o_layer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        /*TextView textView = (TextView) itemView.findViewById(R.id.name);

        textView.setText(tags.get(position).getLabel());*/

        return itemView;
        // Todo make it so it's not a textview but a button
    }
}
