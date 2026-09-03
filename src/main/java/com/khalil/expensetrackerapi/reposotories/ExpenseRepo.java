package com.khalil.expensetrackerapi.reposotories;

import com.khalil.expensetrackerapi.entities.Expense;
import com.khalil.expensetrackerapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findAllByUserId(Long userId);

    boolean existsByIdAndUser_Id(Long expenseId, Long userId);
}
