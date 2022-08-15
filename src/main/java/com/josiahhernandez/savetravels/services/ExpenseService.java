package com.josiahhernandez.savetravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.josiahhernandez.savetravels.models.Expense;
import com.josiahhernandez.savetravels.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	
	// OPTION 1
	private final ExpenseRepository expenseRepo;
	
	public ExpenseService(ExpenseRepository expenseRepo) {
		this.expenseRepo = expenseRepo;
	}
	
	// OPTION 2
//	@Autowired
//	private ExpenseRepository expenseRepo;
	
	
	// find all expenses
	
	public List<Expense> allExpenses(){
		return expenseRepo.findAll();
	}
	
	// create
	
	public Expense createExpense(Expense expense) {
		return expenseRepo.save(expense);
	}
	
	// find one
	public Expense oneExpense (Long id) {
		Optional<Expense> optionalExpense = expenseRepo.findById(id);
		if(optionalExpense.isPresent()) { // if the expense is present, true
			return optionalExpense.get();
		}else { // the expense of the id is not available
			return null;
		}
	}
	
	// update one
	public Expense updateExpense(Expense expense) {
		return expenseRepo.save(expense);
	}
	
	
	// ONLY FOR API
	public Expense updateExpense2(Long id, String expenseName, String vendor, Double amount) {
		Expense foundExpense = this.oneExpense(id);
		foundExpense.setExpenseName(expenseName);
		foundExpense.setVendor(vendor);
		foundExpense.setAmount(amount);
		return expenseRepo.save(foundExpense);
	}
	
	// delete
	public void deleteExpense(long id) {
		expenseRepo.deleteById(id);
	}
}
