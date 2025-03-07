package com.WTT.ExpenseTrackingAppBE.controllers;

import com.WTT.ExpenseTrackingAppBE.dto.IncomeDto;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import com.WTT.ExpenseTrackingAppBE.services.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    //private IncomeServiceImpl incomeServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto dto){
        Income createdIncome = incomeService.postIncome(dto);
        if(createdIncome!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncomes()
    {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById (@PathVariable Long id){
        try {
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something ain't right");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDto dto)
    {
        try{
            return ResponseEntity.ok(incomeService.updateIncome(id, dto));
        } catch (EntityNotFoundException ex) {
            System.out.println("Income not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hold up...");
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok(null);
        } catch (EntityNotFoundException ex) {
            System.out.println("Income not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hold up...");
        }
    }
}