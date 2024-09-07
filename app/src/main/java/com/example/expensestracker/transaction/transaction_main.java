package com.example.expensestracker.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensestracker.MainActivity;
import com.example.expensestracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class transaction_main extends AppCompatActivity {

    LinearLayout backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_main);

        backbtn = (LinearLayout) findViewById(R.id.main_transaction_back_btn);

        backbtn.setOnClickListener(view -> {
            Intent intent = new Intent(transaction_main.this, MainActivity.class);
            startActivity(intent);
        });
    }
}