package com.WTT.ExpenseTrackingAppBE.services;

import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.repos.ExpenseRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.WTT.ExpenseTrackingAppBE.entities.Expense;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepo expenseRepo;
    /**
     * @param expenseDto
     * @return
     */
    @Override
    public Expense postExpense(ExpenseDto expenseDto) {
        return saveExpense(new Expense(), expenseDto);
    }

    /**
     * @return
     */
    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    /**
     * @param expense
     * @param expenseDto
     */
    @Override
    public Expense saveExpense(Expense expense, ExpenseDto expenseDto) {
        expense.setTitle(expenseDto.getTitle());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        //expense
        return expenseRepo.save(expense);

    }



    /**
     * @param id
     * @return
     */
    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
        if(optionalExpense.isPresent()){
            return optionalExpense.get();

        }else {
            throw new EntityNotFoundException("Expense with id "+id
                    +" was not found");
        }

    }

    /**
     * @param id
     * @param expenseDto
     * @return
     */
    @Override
    public Expense updateExpense(Long id, ExpenseDto expenseDto) {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
        if(optionalExpense.isPresent()){
            return saveExpense(optionalExpense.get(), expenseDto);

        }else {
            throw new EntityNotFoundException("Expense with id "+id
                    +" was not found");
        }
    }

    /**
     * @param id
     */
    @Override
    public void deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
        if(optionalExpense.isPresent()){
            expenseRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense with id "+id
                    +" was not found");
        }

    }
}