package com.WTT.ExpenseTrackingAppBE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// Budget entity
@Entity
@Data
public class Budget {
    @Id // to make it a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private Double budgetLimit;
    public Budget()
    {}


}