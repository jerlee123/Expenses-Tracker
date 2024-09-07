package com.example.expensestracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class IncomeActivity extends AppCompatActivity {

    private EditText tddate;
    private ImageButton btnDate;
    private EditText etAmount;
    private int year1, month1, day;
    private int year2, month2, day2;

    DatabaseReference databaseIncome;
    Button btnExpense;
    ImageButton btnClose;
    String[] item = {"Salary", "Investment"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_income);

        // Initialize Firebase
        databaseIncome = FirebaseDatabase.getInstance("https://my-app-ha4f1a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Income");

        // Initialize Views
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(IncomeActivity.this, "Item: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnExpense = findViewById(R.id.btn_expense);
        btnExpense.setOnClickListener(view -> {
            Intent intent = new Intent(IncomeActivity.this, ExpenseActivity.class);
            startActivity(intent);
        });

        etAmount = findViewById(R.id.tn_amount_income);
        tddate = findViewById(R.id.td_date_income);
        btnDate = findViewById(R.id.img_btn_date_income);

        btnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            year1 = calendar.get(Calendar.YEAR);
            month1 = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(IncomeActivity.this, (view, year, month, dayOfMonth) -> {
                year1 = year;
                month1 = month;
                day = dayOfMonth;

                tddate.setText(day + " - " + (month + 1) + " - " + year1);
            }, year1, month1, day);
            dialog.show();
        });

        tddate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            year2 = calendar.get(Calendar.YEAR);
            month2 = calendar.get(Calendar.MONTH);
            day2 = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(IncomeActivity.this, (view, year, month, dayOfMonth) -> {
                year2 = year;
                month2 = month;
                day2 = dayOfMonth;

                tddate.setText(day2 + " - " + (month2 + 1) + " - " + year2);
            }, year2, month2, day2);
            dialog.show();
        });

        btnClose = findViewById(R.id.img_btn_close);
        btnClose.setOnClickListener(view -> {
            Intent intent = new Intent(IncomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Add save button functionality
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(view -> saveIncomeData());
    }

    private void saveIncomeData() {
        String amount = etAmount.getText().toString();
        String date = tddate.getText().toString();
        String category = autoCompleteTextView.getText().toString();

        if (TextUtils.isEmpty(amount) || TextUtils.isEmpty(date) || TextUtils.isEmpty(category)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        IncomeDataModel incomeData = new IncomeDataModel(amount, date, category);
        String key = databaseIncome.push().getKey();  // Generate a unique key
        if (key != null) {
            databaseIncome.child(key).setValue(incomeData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(IncomeActivity.this, "Income saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(IncomeActivity.this, "Failed to save income", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}