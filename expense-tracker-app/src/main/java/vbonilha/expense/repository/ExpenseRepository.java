package vbonilha.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vbonilha.expense.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
