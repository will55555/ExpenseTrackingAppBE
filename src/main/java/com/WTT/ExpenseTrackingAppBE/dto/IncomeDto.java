package com.WTT.ExpenseTrackingAppBE.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {
    private Long id;
    private String title;
    private String category;
    private double amount;
    private String description;
    private LocalDate date;
}