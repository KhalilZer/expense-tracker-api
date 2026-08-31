package com.khalil.expensetrackerapi.dtos.expense;

import com.khalil.expensetrackerapi.enums.CategoriesExpenseEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateExpenseRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than 0")
        double amount,

        @NotNull(message = "Category is required")
        CategoriesExpenseEnum category,

        @NotNull(message = "Expense date is required")
        LocalDate expenseDate

) {}