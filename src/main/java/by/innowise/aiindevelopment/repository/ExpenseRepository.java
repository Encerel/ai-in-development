package by.innowise.aiindevelopment.repository;

import by.innowise.aiindevelopment.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e ORDER BY e.amount DESC LIMIT 3")
    List<Expense> findTop3Expenses();

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpenses();
}
