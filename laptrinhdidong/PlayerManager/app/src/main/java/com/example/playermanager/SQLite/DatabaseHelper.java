package com.example.playermanager.SQLite;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.playermanager.Models.MD_Event;
import com.example.playermanager.Models.MD_Hall;
import com.example.playermanager.Models.MD_Review;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 09-06-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "football.db";

    //player registration table
    public static final String TABLE_PLAYER = "registerplayer";

    public static final String col_1 = "name";
    public static final String col_2 = "email";
    public static final String col_3 = "age";
    public static final String col_4 = "password";
    public static final String col_5 = "position";


    //hall registration table
    public static final String TABLE_HALLDATA = "registerhall";
    public static final String col_6 = "hallname";
    public static final String col_7 = "hallemail";
    public static final String col_8 = "address";
    public static final String col_9 = "about";
    public static final String col_10 = "hallpassword";

    //hall data table
    public static final String TABLE_HALL = "halldata";
    public static final String col_12 = "hallname";
    //public static final String col_25 = "hallposition";
    public static final String col_13 = "halladdress";
    public static final String col_14 = "hallcapacity";
    public static final String col_15 = "hallreview";
    public static final String col_16 = "hallrating";





    public static final String TABLE_EVENT = "eventdata";
    public static final String col_17 = "eventname";
    public static final String col_18 = "eventdate";
    public static final String col_19 = "eventtime";
    public static final String col_20 = "hallcapacity";
    //public static final String col_25 = "hallposition";
    // public static final String col_12 = "hallname";



    public static final String TABLE_EVENT_POSITION = "eventdataposition";
    public static final String col_21 = "eventname";
    public static final String col_22 = "eventdate";
    public static final String col_23 = "eventtime";
    public static final String col_24 = "hallcapacity";
    public static final String col_25 = "hallposition";
    public static final String TABLE_EVENT_POS = "eventdatapos";

    public static final String TABLE_HALL_NEW = "newhalldata";
    public static final String col_26 = "hallname";
    public static final String col_27 = "hallposition";
    public static final String col_28 = "halladdress";
    public static final String col_29 = "hallcapacity";
    public static final String col_30 = "hallreview";
    public static final String col_31 = "hallrating";


    public static final String TABLE_EVENT_NEW = "neweventdata";
    public static final String col_32 = "eventname";
    public static final String col_33 = "eventdate";
    public static final String col_34 = "eventtime";
    public static final String col_35 = "eventcapacity";
    public static final String col_36 = "hallname";
    public static final String col_37 = "hallposition";



    //create statements
    private static final String SQL_REGISTER_PLAYER =" Create TABLE " + TABLE_PLAYER + "(" + col_1 + " TEXT, " + col_2 + " TEXT, " + col_3 + " integer , " + col_4 + " TEXT, " + col_5 + " TEXT  )" ;
    private static final String SQL_HALL_DATA =" Create TABLE " + TABLE_HALL + "(" + col_12 + " TEXT, " + col_13 + " TEXT, " + col_14 + " integer , " + col_15 + " integer, " + col_16 + " float  )" ;
    private static final String SQL_REGISTER_HALL =" Create TABLE " + TABLE_HALLDATA + "(" + col_6 + " TEXT, " + col_7 + " TEXT, " + col_8 + " TEXT , " + col_9 + " TEXT, " + col_10 + " TEXT  )" ;
    private static final String SQL_EVENT_DATA =" Create TABLE " + TABLE_EVENT + "(" + col_17 + " TEXT, " + col_18 + " TEXT, " + col_19 + " TEXT , " + col_20 + " TEXT  )" ;
    //private static final String SQL_EVENT_DATA_POSITION =" Create TABLE " + TABLE_EVENT_POSITION + "(" + col_21 + " TEXT, " + col_22 + " TEXT, " + col_23 + " TEXT , " + col_24 + " TEXT , " + col_25 + " integer )" ;
    private static final String SQL_EVENT_DATA_POS =" Create TABLE " + TABLE_EVENT_POS + "(" + col_21 + " TEXT, " + col_22 + " TEXT, " + col_23 + " TEXT , " + col_24 + " TEXT , " + col_25 + " TEXT )" ;
    private static final String SQL_HALL_DATA_NEW =" Create TABLE " + TABLE_HALL_NEW + "(" + col_26 + " TEXT, " + col_27 + " TEXT, " + col_28 + " TEXT , " + col_29 + " TEXT , " + col_30 + " TEXT , " + col_31 + " TEXT )" ;
    private static final String SQL_EVENT_DATA_NEW =" Create TABLE " + TABLE_EVENT_NEW + "(" + col_32 + " TEXT, " + col_33 + " TEXT, " + col_34 + " TEXT , " + col_35 + " TEXT , " + col_36 + " TEXT , " + col_37 + " TEXT )" ;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 7);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_REGISTER_PLAYER);
        db.execSQL(SQL_HALL_DATA);
        db.execSQL(SQL_REGISTER_HALL);
        db.execSQL(SQL_EVENT_DATA);
        db.execSQL(SQL_EVENT_DATA_POS);
        db.execSQL(SQL_HALL_DATA_NEW);
        db.execSQL(SQL_EVENT_DATA_NEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALL);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALLDATA);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_EVENT);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_EVENT_POS);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_HALL_NEW);
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_EVENT_NEW);
        onCreate(db);

    }

    //insert query for player registration
    public boolean insertPlayerRegistration(String name, String email, int age, String pass, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_1, name);
        cv.put(col_2, email);
        cv.put(col_3, age);
        cv.put(col_4, pass);
        cv.put(col_5, position);

        long result = db.insert(TABLE_PLAYER, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    //insert query for hall data
    public boolean insertHallData(String name, String address, int capacity, int reviews, float rating,int hallPosition) {
        String hallPos = String.valueOf(hallPosition);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_26, name);
        cv.put(col_27, hallPos);
        cv.put(col_28, address);
        cv.put(col_29, capacity);
        cv.put(col_30, reviews);
        cv.put(col_31, rating);

        long result = db.insert(TABLE_HALL_NEW, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertHallRegisterData(String name, String email, String address, String about, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_6,name);
        cv.put(col_7, email);
        cv.put(col_8, address);
        cv.put(col_9, about);
        cv.put(col_10, password);

        long result = db.insert(TABLE_HALLDATA, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertEventData(String eventName,String date,String time,String capacity,String hallName,String position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_32, eventName);
        cv.put(col_33, date);
        cv.put(col_34, time);
        cv.put(col_35, capacity);
        cv.put(col_36, hallName);
        cv.put(col_37, position);
        long result = db.insert(TABLE_EVENT_NEW, null, cv);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor checkLoginDetailsForPlayers(String email, String password)
    {
        String columns[]={col_2};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("registerplayer", columns , "email=? and password=?",new String[]{email,password},null,null,null);
        return res;
    }

    public Cursor checkHallLoginDetails(String email,String password)
    {
        String columns[]={col_7};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query("registerhall", columns , "hallemail=? and hallpassword=?",new String[]{email,password},null,null,null);
        return res;
    }

    public List<MD_Hall> retrieveHallData(){
        List<MD_Hall> hallsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_HALL_NEW;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(selectQuery, null);

        while(res.moveToNext()){
            @SuppressLint("Range") String name = res.getString(res.getColumnIndex(col_26));
            @SuppressLint("Range") String position = res.getString(res.getColumnIndex(col_27));
            @SuppressLint("Range") String addr = res.getString(res.getColumnIndex(col_28));
            @SuppressLint("Range") String cap = res.getString(res.getColumnIndex(col_29));
            @SuppressLint("Range") String rev = res.getString(res.getColumnIndex(col_30));
            @SuppressLint("Range") String rate = res.getString(res.getColumnIndex(col_31));

            MD_Hall model = new MD_Hall();
            model.setName(name);
            model.setHallPosition(Integer.parseInt(position));
            model.setAddress(addr);
            model.setTotalCapacity(Integer.parseInt(cap));
            model.setReviewCount(Integer.parseInt(rev));
            model.setRating(Float.parseFloat(rate));
            hallsList.add(model);
        }
        return hallsList;
    }

    public List<MD_Event> retrieveEventData(String hallName,String hallPosition){
        List<MD_Event> eventList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String columns[]={col_32,col_33,col_34,col_35,col_36,col_37};
        Cursor res = db.query("neweventdata", columns , "hallname=? and hallposition=?",new String[]{hallName,hallPosition},null,null,null);

        //String selectQuery = "SELECT  * FROM " + TABLE_EVENT_NEW ;
        // SQLiteDatabase db = this.getReadableDatabase();
        // Cursor res = db.rawQuery(selectQuery, null);

        while(res.moveToNext()){
            @SuppressLint("Range") String name = res.getString(res.getColumnIndex(col_32));
            @SuppressLint("Range") String date = res.getString(res.getColumnIndex(col_33));
            @SuppressLint("Range") String time = res.getString(res.getColumnIndex(col_34));
            @SuppressLint("Range") String capacity = res.getString(res.getColumnIndex(col_35));
            @SuppressLint("Range") String pos = res.getString(res.getColumnIndex(col_36));

            MD_Event model = new MD_Event();
            model.setName(name);
            model.setDate(date);
            model.setTimeInMins(Integer.parseInt(time));
            model.setTotalCapacity(Integer.parseInt(capacity));

            eventList.add(model);
        }
        return eventList;
    }

    public boolean updateEventData(String capacity, String hallName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(col_35, capacity);
        //cv.put(col_36, hallName);

        String whereClause = "hallName=?";
        String whereArgs[] = {hallName};
        long result = db.update("neweventdata", cv, whereClause, whereArgs);

        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean updateHallData(String name, String address, int capacity, int reviews, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_12, name);
        cv.put(col_13, address);
        cv.put(col_14, capacity);
        cv.put(col_15, reviews);
        cv.put(col_16, rating);

        String whereClause = "hallname=?";
        String whereArgs[] = {name};
        long result = db.update(TABLE_HALL, cv, whereClause, whereArgs);

        if (result == -1)
            return false;
        else
            return true;
    }




}


