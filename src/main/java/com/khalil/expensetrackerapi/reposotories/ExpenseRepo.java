package com.khalil.expensetrackerapi.reposotories;

import com.khalil.expensetrackerapi.entities.Expense;
import com.khalil.expensetrackerapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
