package com.khalil.expensetrackerapi.mappers;

import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.ExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toEntity(CreateExpenseRequest request);

    @Mapping(source = "user.id", target = "userId")
    ExpenseResponse toResponse(Expense expense);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(
            UpdateExpenseRequest request,
            @MappingTarget Expense expense
    );
}
