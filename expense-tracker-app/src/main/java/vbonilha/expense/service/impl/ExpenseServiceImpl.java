package vbonilha.expense.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vbonilha.expense.dto.ExpenseDto;
import vbonilha.expense.entity.Category;
import vbonilha.expense.entity.Expense;
import vbonilha.expense.exceptions.ResourceNotFoundException;
import vbonilha.expense.mapper.ExpenseMapper;
import vbonilha.expense.repository.CategoryRepository;
import vbonilha.expense.repository.ExpenseRepository;
import vbonilha.expense.service.ExpenseService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class ExpenseServiceImpl implements ExpenseService {
    // Inject ExpenseRepository using constructor based DI
    private ExpenseRepository expenseRepository;

    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        // Convert ExpenseDto to Expense entity
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);

        // Save expense object into database table - expenses
        Expense savedExpense = expenseRepository.save(expense);

        // Convert savedExpense to ExpenseDto
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {

        // get expense entity from the database by expense id
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        // convert expense entity to ExpenseDto
        return ExpenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map((expense) -> ExpenseMapper.mapToExpenseDto(expense))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        // update expense amount
        expense.setAmount(expenseDto.amount());

        // update expense date
        expense.setExpenseDate(expenseDto.expenseDate());

        // update expense category
        if(expenseDto.categoryDto() != null){

            // get the category entity by id
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + expenseDto.categoryDto().id()));

            expense.setCategory(category);
        }

        // update the expense entity into  database
        Expense updatedExpense = expenseRepository.save(expense);

        // convert expense entity into ExpenseDto
        return ExpenseMapper.mapToExpenseDto(updatedExpense);

    }

    @Override
    public void deleteExpense(Long id) {

        // get expense from the database by expense id. If it is not exists
        // then throw an exception
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        expenseRepository.delete(expense);
    }
}
