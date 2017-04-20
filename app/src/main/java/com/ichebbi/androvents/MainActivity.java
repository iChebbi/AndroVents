package com.ichebbi.androvents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String PREFERENCE_FILENAME ="eventApp";
    EditText login;
    EditText pass;
    SharedPreferences eventPref;
    SharedPreferences.Editor prefEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Event List");

        login = (EditText)findViewById(R.id.login);
        pass = (EditText)findViewById(R.id.pass);

        eventPref = getSharedPreferences(PREFERENCE_FILENAME,MODE_PRIVATE);
        prefEditor = eventPref.edit();
        prefEditor.putString("login","admin");
        prefEditor.putString("pass","admin");
        prefEditor.commit();
        login.setText(eventPref.getString("login", String.valueOf(0)));
        pass.setText(eventPref.getString("pass", String.valueOf(0)));

    }

    public void connect(View view) {





        String loginInput = login.getText().toString();
        String passInput = pass.getText().toString();

        boolean Empty = false;

        if (loginInput.length() == 0) {
            Empty = true ;
        }

        else if (passInput.length() == 0) {
            Empty = true;
        }

        if (Empty == true) {
            Toast.makeText(getApplicationContext(),"Champ Vide",Toast.LENGTH_SHORT).show();
        } else {

            if (loginInput.equals(eventPref.getString("login", String.valueOf(0))) && passInput.equals(eventPref.getString("pass", String.valueOf(0)))) {
                Intent intent = new Intent(this, EventList.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Authentification échoué", Toast.LENGTH_SHORT).show();
            }

        }
    }
}