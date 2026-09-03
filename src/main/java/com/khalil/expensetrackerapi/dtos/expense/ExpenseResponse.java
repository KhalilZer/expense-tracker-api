package com.khalil.expensetrackerapi.dtos.expense;

import com.khalil.expensetrackerapi.enums.CategoriesExpenseEnum;

import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String title,
        double amount,
        CategoriesExpenseEnum category,
        LocalDate expenseDate,
        Long userId
) {
}
