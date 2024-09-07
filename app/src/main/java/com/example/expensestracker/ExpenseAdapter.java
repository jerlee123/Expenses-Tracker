//package com.example.expensestracker;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
//
//    private List<ExpensesDataModel> expenseList;
//    private Context context;
//
//    public ExpenseAdapter(Context context, List<ExpensesDataModel> expenseList) {
//        this.context = context;
//        this.expenseList = expenseList;
//    }
//
//    public ExpenseAdapter(List<ExpensesDataModel> expenseList) {
//    }
//
//    @NonNull
//    @Override
//    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_view, parent, false);
//        return new ExpenseViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
//        ExpensesDataModel expense = expenseList.get(position);
//        holder.tvCategory.setText(expense.getCategory());
//        holder.tvAmount.setText(String.format("- $%s", expense.getAmount()));
//        holder.tvTime.setText(expense.getDate());
//    }
//
//    @Override
//    public int getItemCount() {
//        return expenseList.size();
//    }
//
//    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvCategory;
//        public TextView tvDescription;
//        public TextView tvAmount;
//        public TextView tvTime;
//
//        public ExpenseViewHolder(View itemView) {
//            super(itemView);
//            tvCategory = itemView.findViewById(R.id.category_name);
//            tvAmount = itemView.findViewById(R.id.amount);
//            tvTime = itemView.findViewById(R.id.time);
//        }
//    }
//}
