package com.arif.otp.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by khoiron on 23/01/18.
 */
// class abstrack untuk di extend ke roomdatabase
@Database(entities = {Sekolah.class}, version = 1, exportSchema = false)
//@TypeConverters({Sekolah.DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract sekolahDAO userDao();
}
