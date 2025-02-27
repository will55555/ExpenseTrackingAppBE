package com.WTT.ExpenseTrackingAppBE.dto;

import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import lombok.Data;

@Data
public class StatsDto {

    private Double totalIncome;
    private Double totalExpense;
    private Income recentIncome;
    private Expense recentExpense;
    private Double balance;
    private Double minIncome;
    private Double maxIncome;
    private Double minExpense;
    private Double maxExpense;
    private Double minBudget;
    private Double maxBudget;




}