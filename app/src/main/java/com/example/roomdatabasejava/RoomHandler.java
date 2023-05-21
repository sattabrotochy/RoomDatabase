package com.example.roomdatabasejava;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

public class RoomHandler extends Thread {
    private static RoomHandler instance; // Singleton instance
    private Context context;
    private User user;

    private RoomHandler(Context context) {
        this.context = context;
    }

    public static synchronized RoomHandler getInstance(Context context, User user) {
        if (instance == null) {
            instance = new RoomHandler(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void run() {
        super.run();

        // Access the application context to build the Room database
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "database-name").build();


        UserDao userDao = db.userDao();
        userDao.insertAll(user);
        Toast.makeText(context, "Data Save Successfully", Toast.LENGTH_SHORT).show();
        // Perform other tasks with the database or any other logic

        // Clean up resources if needed
    }
}
