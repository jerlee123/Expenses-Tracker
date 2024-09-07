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

public class ExpenseActivity extends AppCompatActivity {

    private EditText tddate;
    private ImageButton btnDate;
    private EditText etAmount;  // Added to get the amount
    private Button btnSave;     // Added to save the expense data

    private int year1, month1, day;
    private int year2, month2, day2;

    Button btnIncome;
    ImageButton btnClose;

    String[] item = {"Shopping", "Subscription", "Food"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    DatabaseReference databaseExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expense);

        // Initialize Firebase Database
        databaseExpenses = FirebaseDatabase.getInstance("https://my-app-ha4f1a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Expenses");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedItem = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(ExpenseActivity.this, "Item: " + selectedItem, Toast.LENGTH_SHORT).show();
        });

        btnIncome = findViewById(R.id.btn_income_pg1);
        btnIncome.setOnClickListener(view -> {
            Intent intent = new Intent(ExpenseActivity.this, IncomeActivity.class);
            startActivity(intent);
        });

        etAmount = findViewById(R.id.tn_amount_expense); // Make sure this EditText exists in your XML
        tddate = findViewById(R.id.td_date_expense);
        btnDate = findViewById(R.id.img_btn_date_expense);
        btnSave = findViewById(R.id.btn_save_expenses);  // Initialize the save button

        btnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            year1 = calendar.get(Calendar.YEAR);
            month1 = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(ExpenseActivity.this, (view, year, month, dayOfMonth) -> {
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

            DatePickerDialog dialog = new DatePickerDialog(ExpenseActivity.this, (view, year, month, dayOfMonth) -> {
                year2 = year;
                month2 = month;
                day2 = dayOfMonth;

                tddate.setText(day2 + " - " + (month2 + 1) + " - " + year2);
            }, year2, month2, day2);
            dialog.show();
        });

        btnClose = findViewById(R.id.img_btn_close);
        btnClose.setOnClickListener(view -> {
            Intent intent = new Intent(ExpenseActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Set up the save button
        btnSave.setOnClickListener(view -> saveExpenseData());
    }

    private void saveExpenseData() {
        String amount = etAmount.getText().toString();
        String date = tddate.getText().toString();
        String category = autoCompleteTextView.getText().toString();

        if (TextUtils.isEmpty(amount) || TextUtils.isEmpty(date) || TextUtils.isEmpty(category)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        ExpensesDataModel expenseData = new ExpensesDataModel(amount, date, category);
        String key = databaseExpenses.push().getKey();  // Generate a unique key
        if (key != null) {
            databaseExpenses.child(key).setValue(expenseData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ExpenseActivity.this, "Expense saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExpenseActivity.this, "Failed to save expense", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
