package com.WTT.ExpenseTrackingAppBE.repos;

import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

}