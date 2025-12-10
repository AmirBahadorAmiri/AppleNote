package com.amirbahadoramiri.applenotebook.tools.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.amirbahadoramiri.applenotebook.models.Note;

@Database(version = 1, exportSchema = false, entities = {Note.class})
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB roomDB;

    public static RoomDB getRoomDB(Context context) {
        if (roomDB == null) {
            roomDB = Room.databaseBuilder(context, RoomDB.class, "room_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration(false)
                    .build();
        }
        return roomDB;
    }

    public abstract NoteDao getNoteDao();

}
