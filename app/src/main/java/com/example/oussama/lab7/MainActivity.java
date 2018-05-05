package com.example.oussama.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,phone;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        dbHandler = new MyDBHandler(this);
    }

    public void addnewRecord(View view) {
        String naStr = name.getText().toString();
        String phStr = phone.getText().toString();
        dbHandler.addRecord(naStr,phStr);
        name.setText("");
        phone.setText("");
    }

    public void printDatabaseData(View view) {
       String databasedata =  dbHandler.databaseToString();
        Toast.makeText(this, databasedata, Toast.LENGTH_LONG).show();
    }


}
