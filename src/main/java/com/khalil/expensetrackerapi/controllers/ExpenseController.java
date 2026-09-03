package com.khalil.expensetrackerapi.controllers;

import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.ExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;
import com.khalil.expensetrackerapi.services.ExpenseServiceImpl;
import com.khalil.expensetrackerapi.shared.GlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseServiceImpl expenseService;

    @PostMapping
    public ResponseEntity<GlobalResponse<ExpenseResponse>> createExpense(
            @Valid @RequestBody CreateExpenseRequest request
    ) {


        return GlobalResponse.success(
                expenseService.createExpense(request),
                "Expense created successfully",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<ExpenseResponse>>> getAllExpenses() {
        return GlobalResponse.success(
                expenseService.getAllExpenses(),
                "Expenses retrieved successfully",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<ExpenseResponse>> getOneExpense(
            @PathVariable Long id
    ) {
        return GlobalResponse.success(
                expenseService.getOneExpense(id),
                "Expense retrieved successfully",
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GlobalResponse<ExpenseResponse>> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExpenseRequest request
    ) {
        return GlobalResponse.success(
                expenseService.updateExpense(id, request),
                "Expense updated successfully",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> deleteExpense(
            @PathVariable Long id
    ) {
        expenseService.deleteExpense(id);
        return GlobalResponse.success(
                null,
                "Expense deleted successfully",
                HttpStatus.OK
        );
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<GlobalResponse<SummaryExpenseResponse>> getSummary(@PathVariable Long userId) {
        return GlobalResponse.success(
                expenseService.summaryByUser(userId),
                "Expense summary retrieved successfully",
                HttpStatus.OK
        );
    }
}
