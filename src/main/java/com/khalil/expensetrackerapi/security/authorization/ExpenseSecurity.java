package com.khalil.expensetrackerapi.security.authorization;

import com.khalil.expensetrackerapi.reposotories.ExpenseRepo;
import com.khalil.expensetrackerapi.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("expenseSecurity")
@RequiredArgsConstructor
public class ExpenseSecurity {
    private final ExpenseRepo expenseRepo;

    public boolean isOwner(Long expenseId, CustomUserDetails userDetails) {
        return expenseRepo.existsByIdAndUser_Id(expenseId, userDetails.getId());
    }
}
