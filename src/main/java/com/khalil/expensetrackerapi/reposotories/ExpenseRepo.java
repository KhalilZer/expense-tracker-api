package com.khalil.expensetrackerapi.reposotories;

import com.khalil.expensetrackerapi.entities.Expense;
import com.khalil.expensetrackerapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findAllByUserId(Long userId);
}
