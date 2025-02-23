package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import com.WTT.ExpenseTrackingAppBE.services.ExpenseService;
import com.WTT.ExpenseTrackingAppBE.services.ExpenseServiceImpl;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    private ExpenseService expenseService;
    //private ExpenseServiceImpl expenseServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDto dto){
        Expense createdExpense = expenseService.postExpense(dto);
        if(createdExpense!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpense(@RequestBody ExpenseDto dto)
    {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById (@PathVariable Long id){
        try {
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        } catch (EntityNotFoundException ex) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something ain't right");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto dto)
    {
        try{
            return ResponseEntity.ok(expenseService.updateExpense(id, dto));
        } catch (EntityNotFoundException ex) {
        System.out.println("Expense not found: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hold up...");
    }


}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(null);
        } catch (EntityNotFoundException ex) {
            System.out.println("Expense not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hold up...");
        }
    /*@GetMapping("api/expenses")
    public String landingPage(Model model)
    {
        List< Expense> expenses= expenseServiceImpl.getAllExpenses();


        return "";
    }*/
    }
}