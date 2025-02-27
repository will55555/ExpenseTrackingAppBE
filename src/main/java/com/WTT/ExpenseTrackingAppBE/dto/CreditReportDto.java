package com.WTT.ExpenseTrackingAppBE.dto;

import lombok.Data;

@Data
public class CreditReportDto {
    private Long id;
    private int creditScore;
    private String reportDetails;
}