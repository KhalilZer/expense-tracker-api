package com.khalil.expensetrackerapi.dtos.expense;

import com.khalil.expensetrackerapi.enums.CategoriesExpenseEnum;
import jakarta.validation.constraints.Positive;

public record UpdateExpenseRequest(
        String title,

        @Positive(message = "Amount must be greater than 0")
        double amount,

        CategoriesExpenseEnum category

        ) {
}
