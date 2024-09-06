package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensestracker.profile.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    Button btnProfile;
    LinearLayout btnInsights, btnIncome, account_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure the layout is set

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            // Redirect to login page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optional: Finish MainActivity to prevent returning to it
            return;
        }

        // Initialize button
        account_profile = findViewById(R.id.account_profile); // Make sure this ID exists in your XML layout

        // Use lambda for click listener
        account_profile.setOnClickListener(view -> {
            // Go to register profile activity
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        });

        btnInsights = findViewById(R.id.corners_income);
        btnIncome = findViewById(R.id.corner_expense);

        btnInsights.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsightsAvtivity.class);
                startActivity(intent);
            }
        });

        btnIncome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,IncomeActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null) {
            // Redirect to login page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Optional: Finish MainActivity to prevent returning to it
        }
    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_logout) {
//            // Sign out the user
//            mFirebaseAuth.signOut();
//
//            // Redirect to LoginActivity
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//
//            // Clear the activity stack so the user cannot navigate back to the MainActivity
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            startActivity(intent);
//            return true; // Return true to indicate that the action has been handled
//        }
//        return super.onOptionsItemSelected(item);
//    }
}