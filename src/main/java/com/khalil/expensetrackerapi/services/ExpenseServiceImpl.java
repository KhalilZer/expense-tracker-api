package com.khalil.expensetrackerapi.services;

import com.khalil.expensetrackerapi.abstracts.ExpenseContract;
import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.ExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;
import com.khalil.expensetrackerapi.entities.User;
import com.khalil.expensetrackerapi.exceptions.ResourceNotFound;
import com.khalil.expensetrackerapi.mappers.ExpenseMapper;
import com.khalil.expensetrackerapi.reposotories.ExpenseRepo;
import com.khalil.expensetrackerapi.reposotories.UserRepo;
import com.khalil.expensetrackerapi.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseContract {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;


    @Override
    public ExpenseResponse createExpense(CreateExpenseRequest expenseRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        assert userDetails != null;
        String email = userDetails.getUsername();

        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));


        Expense expense = expenseMapper.toEntity(expenseRequest);
        expense.setUser(user);
        expenseRepo.save(expense);
        return expenseMapper.toResponse(expense);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        assert userDetails != null;
        return expenseRepo.findAllByUserId(userDetails.getId())
                .stream()
                .map((expense) -> expenseMapper.toResponse(expense))
                .toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @expenseSecurity.isOwner(#expenseId,principal)")

    public ExpenseResponse getOneExpense(Long expenseId) {
        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFound("Expense not Found"));

        return expenseMapper.toResponse(expense);


    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @expenseSecurity.isOwner(#expenseId,principal)")

    public ExpenseResponse updateExpense(Long expenseId, UpdateExpenseRequest expenseRequest) {
        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFound("Expense not Found"));

        expenseMapper.updateEntity(expenseRequest, expense);


        return expenseMapper.toResponse(expenseRepo.save(expense));

    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or @expenseSecurity.isOwner(#expenseId,principal)")
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepo.findById(expenseId)
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
