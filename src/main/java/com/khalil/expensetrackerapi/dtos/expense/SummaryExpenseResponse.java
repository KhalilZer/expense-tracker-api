package com.khalil.expensetrackerapi.dtos.expense;

public record SummaryExpenseResponse(
        long expenseCount,
        double totalExpenses
) {
}
