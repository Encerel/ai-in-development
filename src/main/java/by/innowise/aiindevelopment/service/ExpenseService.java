package by.innowise.aiindevelopment.service;

import by.innowise.aiindevelopment.model.Expense;
import by.innowise.aiindevelopment.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        expense.setCreatedAt(LocalDateTime.now());
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Double getTotalExpenses() {
        return expenseRepository.getTotalExpenses();
    }

    public Double getAverageDailyExpense() {
        Double total = getTotalExpenses();
        return total != null ? total / 30 : 0;
    }

    public List<Expense> getTop3Expenses() {
        return expenseRepository.findTop3Expenses();
    }
}