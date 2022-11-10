package com.example.covid_19alertapp.roomdatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VisitedLocationsDao {

    @Insert
    void insertLocations(VisitedLocations visitedLocations);

    @Query("UPDATE visitedlocations SET count = count+1 WHERE conatainerDateTimeComposite = :primaryKey")
    void update(String primaryKey);

    @Query("SELECT * FROM visitedlocations")
    List<VisitedLocations> fetchAll();

    @Query("DELETE FROM visitedlocations WHERE conatainerDateTimeComposite LIKE :sqlFormatsevenDayAgoDate")
    void deleteSevenDaysAgoVisitedLocations(String sqlFormatsevenDayAgoDate);

    @Query("DELETE FROM visitedlocations WHERE conatainerDateTimeComposite = :primaryKey")
    void deletebyPrimaryKey(String primaryKey);

    @Delete
    void deleteLocation(VisitedLocations visitedLocations);

}
