package com.example.driverassistant.Login;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AccountDAO {
    @Query("SELECT * FROM account WHERE username = :username AND password = :password")
    Account verifyAccount(String username, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAccount(Account account);
}
