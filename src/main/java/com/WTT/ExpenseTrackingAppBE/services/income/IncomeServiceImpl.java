package com.WTT.ExpenseTrackingAppBE.services.income;

import com.WTT.ExpenseTrackingAppBE.dto.IncomeDto;
import com.WTT.ExpenseTrackingAppBE.entities.Income;
import com.WTT.ExpenseTrackingAppBE.repos.IncomeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepo incomeRepo;
    /**
     * @return
     */
    @Override
    public List<Income> getAllIncomes() {
        return incomeRepo.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .collect(Collectors.toList());
    }

    /**
     * @param income
     * @param incomeDto
     * @return
     */
    @Override
    public Income saveIncome(Income income, IncomeDto incomeDto) {
        income.setTitle(incomeDto.getTitle());
        income.setCategory(incomeDto.getCategory());
        income.setAmount(incomeDto.getAmount());
        income.setDescription(incomeDto.getDescription());
        income.setDate(incomeDto.getDate());
        return incomeRepo.save(income);
    }

    /**
     * @param incomeDto
     * @return
     */
    @Override
    public Income postIncome(IncomeDto incomeDto) {
        return saveIncome(new Income(), incomeDto);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Income getIncomeById(Long id) {
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        if(optionalIncome.isPresent()){
            return optionalIncome.get();

        }else {
            throw new EntityNotFoundException("IncomeIncome with id "+id
                    +" was not found");
        }

    }

    /**
     * @param id
     * @param incomeDto
     * @return
     */
    @Override
    public Income updateIncome(Long id, IncomeDto incomeDto) {
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        if(optionalIncome.isPresent()){
            return saveIncome(optionalIncome.get(), incomeDto);

        }else {
            throw new EntityNotFoundException("Expense with id "+id
                    +" was not found");
        }
    }

    /**
     * @param id
     */
    @Override
    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        if(optionalIncome.isPresent()){
            incomeRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException("Income with id "+id
                    +" was not found");
        }

    }


}