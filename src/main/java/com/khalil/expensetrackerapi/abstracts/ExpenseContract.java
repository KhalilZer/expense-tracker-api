package com.khalil.expensetrackerapi.abstracts;

import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.ExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;

import java.util.List;

public interface ExpenseContract {
    ExpenseResponse createExpense(CreateExpenseRequest expenseRequest);

    List<ExpenseResponse> getAllExpenses();

    ExpenseResponse getOneExpense(Long id);

    ExpenseResponse updateExpense(Long expenseId, UpdateExpenseRequest expenseRequest);

    void deleteExpense(Long id);

    SummaryExpenseResponse summaryByUser(Long userId);
}
