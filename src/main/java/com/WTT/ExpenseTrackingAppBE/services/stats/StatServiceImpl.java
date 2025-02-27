package com.WTT.ExpenseTrackingAppBE.services.stats;

import com.WTT.ExpenseTrackingAppBE.dto.GraphDto;
import com.WTT.ExpenseTrackingAppBE.dto.StatsDto;
import com.WTT.ExpenseTrackingAppBE.entities.Budget;
import com.WTT.ExpenseTrackingAppBE.entities.Expense;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import com.WTT.ExpenseTrackingAppBE.repos.BudgetRepo;
import com.WTT.ExpenseTrackingAppBE.repos.ExpenseRepo;
import com.WTT.ExpenseTrackingAppBE.repos.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final IncomeRepo incomeRepo;
    private final ExpenseRepo expenseRepo;
    private final BudgetRepo budgetRepo;

    public GraphDto getChartData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);
        Double budgetLimit = budgetRepo.findAll().stream()
                .findFirst()  // Get any budget (adjust logic as needed)
                .map(Budget::getBudgetLimit)
                .orElse(0.0); // Default value if no budget is found

        GraphDto graphDto = new GraphDto();
        graphDto.setExpenseList(expenseRepo.findByDateBetween(startDate, endDate));
        graphDto.setIncomeList(incomeRepo.findByDateBetween(startDate, endDate));
        graphDto.setBudgetList(budgetRepo.findByBudgetLimit(budgetLimit));

        return graphDto;
    }

    public StatsDto getStats() {
        Double totalIncome = Optional.ofNullable(incomeRepo.amountSum()).orElse(0.0);
        Double totalExpense = Optional.ofNullable(expenseRepo.amountSum()).orElse(0.0);

        StatsDto statsDto = new StatsDto();
        statsDto.setTotalIncome(totalIncome);
        statsDto.setTotalExpense(totalExpense);

        incomeRepo.findFirstByOrderByDateDesc().ifPresent(statsDto::setRecentIncome);
        expenseRepo.findFirstByOrderByDateDesc().ifPresent(statsDto::setRecentExpense);


        statsDto.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepo.findAll();
        List<Expense> expenseList = expenseRepo.findAll();
        List<Budget> budgetList = budgetRepo.findAll();

        OptionalDouble minIncome= incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome= incomeList.stream().mapToDouble(Income::getAmount).max();

        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);

        OptionalDouble minExpense= expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense= expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);

        OptionalDouble minBudget= budgetList.stream().mapToDouble(Budget::getBudgetLimit).min();
        OptionalDouble maxBudget= budgetList.stream().mapToDouble(Budget::getBudgetLimit).max();

        statsDto.setMinBudget(minBudget.isPresent() ? minBudget.getAsDouble() : null);
        statsDto.setMaxBudget(maxExpense.isPresent() ? maxBudget.getAsDouble() : null);



        return statsDto;
    }
}