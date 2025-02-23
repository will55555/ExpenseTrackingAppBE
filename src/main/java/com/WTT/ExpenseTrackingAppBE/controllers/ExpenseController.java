package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import com.WTT.ExpenseTrackingAppBE.services.ExpenseService;
import com.WTT.ExpenseTrackingAppBE.services.ExpenseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {
    //@Autowired
    //private ExpenseServiceImpl expenseServiceImpl;
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto dto){
        Expense createdExpense = expenseService.postExpense(dto);
        if(createdExpense!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    /*@GetMapping("api/expenses")
    public String landingPage(Model model)
    {
        List< Expense> expenses= expenseServiceImpl.getAllExpenses();


        return "";
    }*/
}