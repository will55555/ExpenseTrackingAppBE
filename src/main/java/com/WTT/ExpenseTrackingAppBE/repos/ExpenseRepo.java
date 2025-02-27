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
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e")// to call sum function
    Double amountSum();

    Optional<Expense> findFirstByOrderByDateDesc();


}