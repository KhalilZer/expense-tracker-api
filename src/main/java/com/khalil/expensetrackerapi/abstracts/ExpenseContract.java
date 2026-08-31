package com.khalil.expensetrackerapi.abstracts;

import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;

import java.util.List;

public interface ExpenseContract {
    Expense createExpense(CreateExpenseRequest expenseRequest);
    List<Expense> getAllExpenses();
    Expense getOneExpense(Long id);
    Expense updateExpense(Long expenseId,UpdateExpenseRequest expenseRequest);
    void deleteExpense(Long id);
    SummaryExpenseResponse summaryByUser(Long userId);
}
