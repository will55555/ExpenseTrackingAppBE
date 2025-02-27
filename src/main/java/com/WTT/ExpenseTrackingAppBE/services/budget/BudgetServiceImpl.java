package com.WTT.ExpenseTrackingAppBE.services.budget;

import com.WTT.ExpenseTrackingAppBE.dto.BudgetDto;
import com.WTT.ExpenseTrackingAppBE.entities.Budget;
import com.WTT.ExpenseTrackingAppBE.repos.BudgetRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService{
    private final BudgetRepo budgetRepo;

    /**
     * @return
     */
    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepo.findAll().stream()
                .sorted(Comparator.comparing(Budget::getId).reversed())
                .collect(Collectors.toList());
    }

    public Budget saveBudget(Budget budget, BudgetDto budgetDto) {
        budget.setTitle(budgetDto.getTitle());
        budget.setCategory(budgetDto.getCategory());
        budget.setBudgetLimit(budgetDto.getBudgetLimit());
        return budgetRepo.save(budget);
    }

    public Budget getBudgetById(Long id) {
        Optional<Budget> optionalBudget = budgetRepo.findById(id);
        if(optionalBudget.isPresent()){
            return optionalBudget.get();

        }else {
            throw new EntityNotFoundException("Budget with id "+id
                    +" was not found");
        }
    }

    /**
     * @param budgetDto
     * @return
     */
    @Override
    public Budget postBudget(BudgetDto budgetDto) {

        return saveBudget(new Budget(),budgetDto);
    }

    /**
     * @param id
     * @param budgetDto
     * @return
     */
    @Override
    public Budget updateBudget(Long id, BudgetDto budgetDto) {
        Budget existingBudget = getBudgetById(id);
        return saveBudget(existingBudget, budgetDto);
    }

    /**
     * @param id
     */
    @Override
    public void deleteBudget(Long id) {
        Optional<Budget> optionalBudget = budgetRepo.findById(id);
        if(optionalBudget.isPresent()){
            budgetRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense with id "+id
                    +" was not found");
        }

    }

    }