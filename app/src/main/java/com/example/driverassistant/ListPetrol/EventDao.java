package com.example.driverassistant.ListPetrol;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(Event event);

    @Query("SELECT * FROM event")
    List<Event> getALl();

    @Query("SELECT * FROM event WHERE name = :username")
    List<Event> checkEvent(String username);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);

    @Query("DELETE FROM event")
    void deleteAllevent();

    @Query("SELECT * FROM event WHERE name LIKE '%' || :name || '%'")
    List<Event> searchEvent(String name);
}
