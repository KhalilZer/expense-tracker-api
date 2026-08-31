package com.khalil.expensetrackerapi.services;

import com.khalil.expensetrackerapi.abstracts.ExpenseContract;
import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseContract {
    @Override
    public Expense createExpense(CreateExpenseRequest expenseRequest) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return List.of();
    }

    @Override
    public Expense getOneExpense(Long id) {
        return null;
    }

    @Override
    public Expense updateExpense(Long expenseId, UpdateExpenseRequest expenseRequest) {
        return null;
    }

    @Override
    public void deleteExpense(Long id) {

    }

    @Override
    public SummaryExpenseResponse summaryByUser(Long userId) {
        return null;
    }
}
