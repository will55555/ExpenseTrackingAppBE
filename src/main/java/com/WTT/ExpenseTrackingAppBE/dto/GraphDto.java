package com.WTT.ExpenseTrackingAppBE.dto;

import com.WTT.ExpenseTrackingAppBE.entities.Budget;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    private List<Expense> expenseList;
    private List<Income> incomeList;
    private List<Budget> budgetList;
}