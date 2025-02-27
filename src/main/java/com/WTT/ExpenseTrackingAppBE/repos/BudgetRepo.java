package com.WTT.ExpenseTrackingAppBE.repos;

import com.WTT.ExpenseTrackingAppBE.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {
        List<Budget> findByBudgetLimit(Double budgetLimit);
    }