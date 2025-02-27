package com.WTT.ExpenseTrackingAppBE.services.budget;

import com.WTT.ExpenseTrackingAppBE.dto.BudgetDto;
import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.entities.Budget;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;

import java.util.List;

public interface BudgetService {

    List<Budget> getAllBudgets();
    Budget saveBudget(Budget budget, BudgetDto budgetDto);
    Budget getBudgetById(Long id);
    Budget postBudget(BudgetDto budgetDto);
    Budget updateBudget(Long id, BudgetDto budgetDto);
    void deleteBudget(Long id);


}