package com.example.expensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InsightsAvtivity extends AppCompatActivity {
    LinearLayout btnClose, btnExpense, btnIncome;
    private RecyclerView recyclerView;
    private List<ExpensesDataModel> expenseList;

    private DatabaseReference databaseReference; // Firebase database reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insights_avtivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnClose = findViewById(R.id.btn_close_insight);
        btnIncome = findViewById(R.id.btn_insight_income);
        btnExpense = findViewById(R.id.btn_insight_expense);

        btnClose.setOnClickListener(view -> {
            Intent intent = new Intent(InsightsAvtivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnIncome.setOnClickListener(view -> {
            Intent intent = new Intent(InsightsAvtivity.this, IncomeActivity.class);
            startActivity(intent);
        });

        btnExpense.setOnClickListener(view -> {
            Intent intent = new Intent(InsightsAvtivity.this, ExpenseActivity.class);
            startActivity(intent);
        });

//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        expenseList = new ArrayList<>();
//        adapter = new ExpenseAdapter(expenseList);
//        recyclerView.setAdapter(adapter);
//
//        // Initialize Firebase database reference
//        databaseReference = FirebaseDatabase.getInstance("https://my-app-ha4f1a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Expenses");
//
//        // Retrieve data from Firebase
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                expenseList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ExpensesDataModel expense = snapshot.getValue(ExpensesDataModel.class);
//                    if (expense != null) {
//                        expenseList.add(expense);
//                    }
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Handle possible errors.
//            }
//        });
    }
}