package com.ichebbi.androvents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {


    TextView title;
    TextView startTime;
    TextView region;
    TextView venue;
    TextView city;
    Button participer;
    Button annuler;
    Event event;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        setTitle("Details");

        title = (TextView)findViewById(R.id.title);
        startTime = (TextView)findViewById(R.id.startTime);
        region = (TextView)findViewById(R.id.region);
        venue = (TextView)findViewById(R.id.venueAdName);
        city = (TextView)findViewById(R.id.city);

        db= new DBHelper(this);

        participer = (Button)findViewById(R.id.participer);
        annuler = (Button)findViewById(R.id.undo);

        event = (Event) getIntent().getSerializableExtra("event");

        title.setText(event.getTitle());
        startTime.setText(event.getStart_time());
        region.setText(event.getRegion_name());
        venue.setText(event.getVenue_address() + event.getVenue_name());
        city.setText(event.getCity_name());

        if ( db.searchEvent(event.getId()) == true) {
            annuler.setVisibility(View.VISIBLE);
            participer.setVisibility(View.GONE);
        } else {
            annuler.setVisibility(View.GONE);
            participer.setVisibility(View.VISIBLE);
        }

    }

    public void annuler(View view) {
        annuler.setVisibility(View.GONE);
        participer.setVisibility(View.VISIBLE);
        db.deleteEvent(event.getId());
    }

    public void participer(View view) {
        participer.setVisibility(View.GONE);
        annuler.setVisibility(View.VISIBLE);
        db.insertEvent(event);

    }
}
