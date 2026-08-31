package com.khalil.expensetrackerapi.entities;

import com.khalil.expensetrackerapi.enums.CategoriesExpenseEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    private CategoriesExpenseEnum category;

    @Column(nullable = false)
    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate timestamps;
}
