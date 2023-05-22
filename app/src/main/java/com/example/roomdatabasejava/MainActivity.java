package com.example.roomdatabasejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText editTxtFirstName, editTxtLastName;
    Button submitBtn;

    DataBaseHelper dataBaseHelper;
    ArrayList<Expense> arrayList;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtFirstName = findViewById(R.id.editTxtFirstName);
        editTxtLastName = findViewById(R.id.editTextLastName);
        submitBtn = findViewById(R.id.submit_btn);
        arrayList=new ArrayList<>();

        dataBaseHelper=DataBaseHelper.getDB(this);
        submitBtn.setOnClickListener(view -> {
            String firstName = editTxtFirstName.getText().toString();
            String lastName = editTxtLastName.getText().toString();

            dataBaseHelper.expenseDao().addTx(new Expense(firstName,lastName));

            arrayList= (ArrayList<Expense>) dataBaseHelper.expenseDao().getAllExpense();
            for (int i=0;i<arrayList.size();i++){
                Log.d("TAG", " firstName"+arrayList.get(i).getFirstName() +"lastName"+arrayList.get(i).getLastName());
            }

        });
    }

}