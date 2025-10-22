package vbonilha.expense.service;

import vbonilha.expense.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto expenseDto);

    ExpenseDto getExpenseById(Long id);

    List<ExpenseDto> getAllExpenses();

    ExpenseDto updateExpense(Long id, ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
