package com.khalil.expensetrackerapi.services;

import com.khalil.expensetrackerapi.abstracts.ExpenseContract;
import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;
import com.khalil.expensetrackerapi.entities.User;
import com.khalil.expensetrackerapi.exceptions.ResourceNotFound;
import com.khalil.expensetrackerapi.mappers.ExpenseMapper;
import com.khalil.expensetrackerapi.reposotories.ExpenseRepo;
import com.khalil.expensetrackerapi.reposotories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseContract {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;


    @Override
    public Expense createExpense(CreateExpenseRequest expenseRequest) {
        Expense expense = expenseMapper.toEntity(expenseRequest);
        expenseRepo.save(expense);
        return expense;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    @Override
    public Expense getOneExpense(Long id) {
        return expenseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Expense not Found"));

    }

    @Override
    public Expense updateExpense(Long expenseId, UpdateExpenseRequest expenseRequest) {
        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFound("Expense not Found"));
        expenseMapper.updateEntity(expenseRequest, expense);
        return expenseRepo.save(expense);

    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Expense not Found"));

        expenseRepo.delete(expense);

    }

    @Override
    public SummaryExpenseResponse summaryByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User with ID: " + userId + " not found"));

        List<Expense> expensesByUser = user.getExpenseList();

        double totalExpenses = expensesByUser.stream()
                .map(Expense::getAmount).reduce(0.0, Double::sum);

        int countExpenses = expensesByUser.size();
        return new SummaryExpenseResponse(countExpenses, totalExpenses);
    }
}
