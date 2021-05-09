package com.example.driverassistant.Home.UserData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;

import com.example.driverassistant.Login.Account;
import com.example.driverassistant.Login.AccountDAO;
import com.example.driverassistant.Login.AccountDatabase;

@Database(entities =  {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase singleton;

    public static UserDatabase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                UserDatabase.class, "user.db")
                .allowMainThreadQueries()
                .build();
    }

    public abstract UserDAO userDAO();
}
