package vbonilha.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vbonilha.expense.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
