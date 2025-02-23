package com.WTT.ExpenseTrackingAppBE.services;

import com.WTT.ExpenseTrackingAppBE.dto.ExpenseDto;
import com.WTT.ExpenseTrackingAppBE.repos.ExpenseRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.WTT.ExpenseTrackingAppBE.entities.Expense;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private final ExpenseRepo expenseRepo;
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
        return expenseRepo.findAll();
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
        return expenseRepo.findById(id).orElse(null);
    }

    /**
     * @param id
     */
    @Override
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);

    }
}