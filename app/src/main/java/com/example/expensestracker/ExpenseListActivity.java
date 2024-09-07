//package com.example.expensestracker;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExpenseListActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private ExpenseAdapter adapter;
//    private List<ExpensesDataModel> expenseList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.card_recycler_view);
//
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        expenseList = new ArrayList<>();
//        // Add data to the list
//        adapter = new ExpenseAdapter(expenseList);
//        recyclerView.setAdapter(adapter);
//    }
//}