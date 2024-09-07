package com.example.expensestracker;

public class ExpensesDataModel {
    private String amount;
    private String date;
    private String category;

    public ExpensesDataModel() {
    }

    public ExpensesDataModel(String amount, String date, String category) {
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
