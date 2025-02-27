package com.WTT.ExpenseTrackingAppBE.entities;

import jakarta.persistence.Entity;
import lombok.Data;

//@Entity
@Data
public class CreditReport {
    private Long id;
    private int creditScore;
    private String reportDetails;
}