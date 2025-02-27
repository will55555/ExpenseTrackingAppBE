package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.BudgetDto;
import com.WTT.ExpenseTrackingAppBE.dto.IncomeDto;
import com.WTT.ExpenseTrackingAppBE.entities.Budget;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import com.WTT.ExpenseTrackingAppBE.services.budget.BudgetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;
    @PostMapping
    public ResponseEntity<?> postBudget(@RequestBody BudgetDto dto){
        Budget createdBudget = budgetService.postBudget(dto);
        if(createdBudget!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBudget);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBudgets()
    {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBudgetById (@PathVariable Long id){
        try {
            return ResponseEntity.ok(budgetService.getBudgetById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something ain't right");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable Long id, @RequestBody BudgetDto dto)
    {
        try{
            return ResponseEntity.ok(budgetService.updateBudget(id, dto));
        } catch (EntityNotFoundException ex) {
            System.out.println("Budget not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hold up...");
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
        try {
            budgetService.deleteBudget(id);
            return ResponseEntity.ok(null);
        } catch (EntityNotFoundException ex) {
            System.out.println("Budget not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hold up...");
        }
    }

}