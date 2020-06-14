package com.example.and1exam.room_Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.and1exam.room_Database.Entity.Question;

@Database(entities = {Question.class}, version = 3, exportSchema = false)

public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase instance;
    public abstract databaseDao databaseDao();

    public static synchronized LocalDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class,"local_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

