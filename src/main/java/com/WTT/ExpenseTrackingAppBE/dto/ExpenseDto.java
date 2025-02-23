package com.WTT.ExpenseTrackingAppBE.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long id;
    private String title;
    private String category;
    private String description;
    private double amount;
    private LocalDate date;
}