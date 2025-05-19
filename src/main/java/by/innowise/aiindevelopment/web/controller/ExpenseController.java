package by.innowise.aiindevelopment.web.controller;

import by.innowise.aiindevelopment.model.Expense;
import by.innowise.aiindevelopment.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String showExpenses(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        Double total = expenseService.getTotalExpenses();
        Double averageDaily = expenseService.getAverageDailyExpense();
        List<Expense> top3 = expenseService.getTop3Expenses();

        model.addAttribute("expenses", expenses);
        model.addAttribute("total", total);
        model.addAttribute("averageDaily", averageDaily);
        model.addAttribute("top3", top3);
        model.addAttribute("newExpense", new Expense());

        return "expenses";
    }

    @PostMapping
    public String addExpense(@ModelAttribute Expense newExpense) {
        expenseService.addExpense(newExpense);
        return "redirect:/expenses";
    }
}
