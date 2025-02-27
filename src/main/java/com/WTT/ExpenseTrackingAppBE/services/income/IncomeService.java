package com.WTT.ExpenseTrackingAppBE.services.income;



import com.WTT.ExpenseTrackingAppBE.dto.IncomeDto;
import com.WTT.ExpenseTrackingAppBE.entities.Income;

import java.util.List;

public interface IncomeService {
    public List<Income> getAllIncomes();
    public Income saveIncome(Income income, IncomeDto incomeDto);
    public Income postIncome(IncomeDto incomeDto);
    public Income getIncomeById(Long id);
    public Income updateIncome(Long id, IncomeDto incomeDto);
    public void deleteIncome(Long id);
}