package com.WTT.ExpenseTrackingAppBE.services;

import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExpenseService {
    public List<Expense> getAllExpenses();
    public Expense saveExpense(Expense expense, ExpenseDto expenseDto);
    public Expense postExpense(ExpenseDto expenseDto);
    public Expense getExpenseById(Long id);
    public void deleteExpense(Long id);
}