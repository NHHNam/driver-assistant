package com.example.driverassistant.Home.UserData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.driverassistant.Login.Account;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user WHERE user = :user")
    List<User> getListHistory(String user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(User data);
}
