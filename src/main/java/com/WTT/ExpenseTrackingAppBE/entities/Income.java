package com.WTT.ExpenseTrackingAppBE.entities;

import com.WTT.ExpenseTrackingAppBE.dto.IncomeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class Income {
    @Id // to make it a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private double amount;
    private String description;
    private LocalDate date;
    public Income(){
    }
    /*public IncomeDto getIncomeDto(){
        IncomeDto incomeDto= new IncomeDto();
        incomeDto.setId(id);
        incomeDto.setTitle(title);
        incomeDto.setCategory(category);
        incomeDto.setAmount(amount);
        incomeDto.setDescription(description);
        incomeDto.setDate(date);

        return incomeDto;
    }*/

}