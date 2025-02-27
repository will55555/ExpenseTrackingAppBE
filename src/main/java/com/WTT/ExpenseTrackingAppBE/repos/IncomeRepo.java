package com.WTT.ExpenseTrackingAppBE.repos;

import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Long > {

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(i.amount) FROM Income i")// to call sum function
    Double amountSum();

    Optional<Income> findFirstByOrderByDateDesc();
}