package com.WTT.ExpenseTrackingAppBE.dto;

import lombok.Data;

@Data
public class BudgetDto {
    private Long id;
    private String title;
    private String category;
    private Double budgetLimit;
}