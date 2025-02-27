package com.WTT.ExpenseTrackingAppBE.services.expense;

import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;

import java.util.List;


public interface ExpenseService {
    List<Expense> getAllExpenses();
    Expense saveExpense(Expense expense, ExpenseDto expenseDto);
    Expense postExpense(ExpenseDto expenseDto);
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, ExpenseDto expenseDto);
    void deleteExpense(Long id);
}