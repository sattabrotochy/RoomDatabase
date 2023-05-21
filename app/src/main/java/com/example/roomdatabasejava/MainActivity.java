package com.example.roomdatabasejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editTxtFirstName, editTxtLastName;
    Button submitBtn;
    RoomHandler roomHandler;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtFirstName = findViewById(R.id.editTxtFirstName);
        editTxtLastName = findViewById(R.id.editTextLastName);
        submitBtn = findViewById(R.id.submit_btn);


        submitBtn.setOnClickListener(view -> {
            String firstName = editTxtFirstName.getText().toString();
            String lastName = editTxtLastName.getText().toString();

            id++;
            // Create a User object with the entered data
            User user = new User(id, firstName, lastName);

            // Create RoomHandler instance passing the application context and User object
            roomHandler = RoomHandler.getInstance(getApplicationContext(), user);

            // Start the database logic in a separate thread
            roomHandler.start();
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Clean up resources if needed
        if (roomHandler != null) {
            roomHandler.interrupt();
            roomHandler = null;
        }
    }
}