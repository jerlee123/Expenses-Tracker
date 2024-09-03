package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start LoginActivity
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);

        // Optional: Finish MainActivity if you don't want the user to return to it
        finish();  // Make sure the layout file 'activity_main.xml' exists in the 'res/layout' folder
    }
}