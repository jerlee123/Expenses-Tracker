package com.example.expensestracker.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensestracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class transaction_main extends AppCompatActivity {

    FloatingActionButton fabAddTransMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_main);

        fabAddTransMain = (FloatingActionButton) findViewById(R.id.transaction_main_fab);

        fabAddTransMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to register club activity
//                Intent intent = new Intent(transaction_main.this, NewTransaction.class);//Page niena expense (new expense/income)
//                startActivity(intent);
            }
        });



    }
}