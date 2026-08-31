package com.khalil.expensetrackerapi.mappers;

import com.khalil.expensetrackerapi.dtos.expense.CreateExpenseRequest;
import com.khalil.expensetrackerapi.dtos.expense.SummaryExpenseResponse;
import com.khalil.expensetrackerapi.dtos.expense.UpdateExpenseRequest;
import com.khalil.expensetrackerapi.entities.Expense;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toEntity(CreateExpenseRequest request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(
            UpdateExpenseRequest request,
            @MappingTarget Expense expense
    );
}
