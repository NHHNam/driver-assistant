package com.example.driverassistant.Login;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =  {Account.class}, version = 1, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {
    private static AccountDatabase singleton;

    public static AccountDatabase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AccountDatabase.class, "account.db")
                .allowMainThreadQueries()
                .build();
    }

    public abstract AccountDAO accountDAO();
}
