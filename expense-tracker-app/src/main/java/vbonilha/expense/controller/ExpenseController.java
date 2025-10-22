package vbonilha.expense.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vbonilha.expense.dto.ExpenseDto;
import vbonilha.expense.service.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    // Inject the ExpenseService using constructor based DI
    private ExpenseService expenseService;

    // Build create expense REST API
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {

        ExpenseDto savedExpense = expenseService.createExpense(expenseDto);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // Build get expense by expenseId REST API
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("id") Long expenseId) {

        ExpenseDto expense = expenseService.getExpenseById(expenseId);

        return ResponseEntity.ok(expense);
    }

    @GetMapping
    // Build get all expenses REST API
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    //Build update expense REST API
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("id") Long expenseId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto updatedExpense = expenseService.updateExpense(expenseId, expenseDto);
        return ResponseEntity.ok(updatedExpense);
    }

    // Build delete expense REST API
    public ResponseEntity<String> deleteExpense(@PathVariable("id") Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok("Expense deleted successfully!");
    }
}
