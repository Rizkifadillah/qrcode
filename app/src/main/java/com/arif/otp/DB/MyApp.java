package com.arif.otp.DB;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by Angga Kristiono on 08/06/2019.
 */

public class MyApp extends Application {

    public static AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Sekolah").allowMainThreadQueries().build();
    }
}
