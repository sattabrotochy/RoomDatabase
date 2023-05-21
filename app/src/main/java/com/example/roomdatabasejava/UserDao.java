package com.example.roomdatabasejava;


import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDao {

    @Insert
    void insertAll(User users);
}
