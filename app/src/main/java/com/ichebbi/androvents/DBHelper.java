package com.ichebbi.androvents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ichebbi on 20/04/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_EVENT = "event";

    public static final String START_TIME = "start_time";
    public static final String VENUE_ADDRESS = "venue_address";
    public static final String VENUE_NAME = "venue_name";
    public static final String CITY_NAME = "city_name";
    public static final String REGION_NAME = "region_name";
    public static final String POSTAL_CODE = "postal_code";
    public static final String TITLE = "title";
    public static final String ID = "id";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_EVENT = "CREATE TABLE " + TABLE_EVENT + " (" +
            START_TIME + " TEXT, " +
            VENUE_ADDRESS + " TEXT, " +
            VENUE_NAME + " TEXT, " +
            CITY_NAME + " TEXT, " +
            REGION_NAME + " TEXT, " +
            POSTAL_CODE + " TEXT, " +
            TITLE + " TEXT, " +
            ID + " TEXT);";

    public DBHelper(Context context) {
        super(context, TABLE_EVENT, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT + ";");
        onCreate(db);
    }

    public boolean insertEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(START_TIME, event.getStart_time());
        contentValues.put(VENUE_ADDRESS, event.getVenue_address());
        contentValues.put(VENUE_NAME, event.getVenue_name());
        contentValues.put(CITY_NAME, event.getCity_name());
        contentValues.put(REGION_NAME, event.getRegion_name());
        contentValues.put(POSTAL_CODE, event.getPostal_code());
        contentValues.put(TITLE, event.getTitle());
        contentValues.put(ID, event.getId());
        db.insert(TABLE_EVENT, null, contentValues);
        return true;
    }

    public boolean deleteEvent(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_EVENT, ID + "='" + id + "'", null) > 0;
    }

    public boolean searchEvent(String id) {
        String query = "Select * FROM " + TABLE_EVENT + " WHERE id = '"+id+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        boolean found = false;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            found = true;
            cursor.close();
        }
        db.close();
        return found;
    }

    public ArrayList<Event> getAllEvent() {
        ArrayList<Event> arrayList = new ArrayList<Event>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENT+";",null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setTitle(cursor.getString(6));

                arrayList.add(event);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }
}
