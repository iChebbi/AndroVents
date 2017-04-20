package com.ichebbi.androvents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ichebbi on 20/04/17.
 */

public class Adapter extends BaseAdapter {

    private ArrayList<Event> eventlist;
    private Context context;

    public Adapter(ArrayList<Event> eventlist, Context context) {
        this.eventlist = eventlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return eventlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View customEntryItem = LayoutInflater.from(context).inflate(R.layout.entrylayout,parent,false);
        final Event event = eventlist.get(position);

        TextView eventTitle = (TextView)customEntryItem.findViewById(R.id.eventTitle);
        ImageView eventImg = (ImageView)customEntryItem.findViewById(R.id.eventImg);

        eventTitle.setText(event.getTitle());
        eventImg.setImageResource(R.mipmap.ic_launcher);

        return customEntryItem;
    }
}
