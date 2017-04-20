package com.ichebbi.androvents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyEvents extends AppCompatActivity {

    TextView textView;
    ListView listView;
    DBHelper db;
    ArrayList<Event> events;
    ArrayList<String> eventsTitle;

    protected ArrayList<String> populateEventList(ArrayList<Event> events) {
        ArrayList<String> eventsTitle = null;
        for(int i=1;i<events.size();i++) {
            eventsTitle.add(events.get(i).getTitle());
        }
        return eventsTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        setTitle("My Events");

        db = new DBHelper(this);
        listView = (ListView)findViewById(R.id.myEventsListView);
        textView = (TextView)findViewById(R.id.alert);

        events = db.getAllEvent();



        if (events.size() == 0) {
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, events));
        }
    }
}
