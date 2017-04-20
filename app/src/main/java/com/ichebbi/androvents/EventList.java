package com.ichebbi.androvents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {

    ListView listView;
    ArrayList<Event> eventArrayList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        setTitle("Events list");
        listView = (ListView)findViewById(R.id.listview);


        StringRequest stringRequest = new StringRequest("http://api.eventful.com/json/events/search?app_key=GnNfrzwjfLn5cDxM&location=San+Diego",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        JSONObject j1 = null;
                        JSONArray result1;
                        try {
                            eventArrayList = new ArrayList<>();
                            j = new JSONObject(response);
                            j1 = j.getJSONObject("events");
                            result1 = j1.getJSONArray("event");

                            for (int i = 0; i< result1.length();i++) {
                                try {
                                    JSONObject json = result1.getJSONObject(i);
                                    Event event = new Event(json.getString("start_time"),json.getString("venue_address"),json.getString("venue_name"),json.getString("city_name"),json.getString("region_name"),json.getString("postal_code"),json.getString("title"),json.getString("id"));
                                    eventArrayList.add(event);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            listView = (ListView)findViewById(R.id.listview);
                            System.out.println(eventArrayList.size());
                            listView.setAdapter(new Adapter(eventArrayList,getApplicationContext()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                for (Event l : eventArrayList) {
                                    if (l.getTitle().contains(listView.getItemAtPosition(position).toString())) {
                                        Intent intent = new Intent(getApplicationContext(), EventDetails.class);
                                        intent.putExtra("event", l);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void myEvents(View view) {
        Intent intent = new Intent(this,MyEvents.class);
        startActivity(intent);
    }
}
