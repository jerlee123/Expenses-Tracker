package com.example.expensestracker.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensestracker.R;
import com.example.expensestracker.profile.Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class transaction_home extends AppCompatActivity {

    FloatingActionButton fabAddTransHome;
    LinearLayout btnProfileTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_home);

        fabAddTransHome = (FloatingActionButton) findViewById(R.id.transaction_home_fab);

        fabAddTransHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to register club activity
//                Intent intent = new Intent(transaction_home.this, NewTransaction.class);//Page niena expense (new expense/income)
//                startActivity(intent);
            }
        });

        btnProfileTrans = (LinearLayout) findViewById(R.id.account_profile);

        btnProfileTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to register profile activity
                Intent intent = new Intent(transaction_home.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}