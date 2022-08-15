package com.josiahhernandez.savetravels.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josiahhernandez.savetravels.models.Expense;
import com.josiahhernandez.savetravels.services.ExpenseService;

@RestController
@RequestMapping("/api")
public class ExpenseApi {
	
	// can use option 1 or option 2 to import file (check service)
	@Autowired
	private ExpenseService expenseService;
	
	
	//----------GET ALL----------
	
	@RequestMapping("/expenses") // Get: /expenses
	public List<Expense> findAllExpenses(){
		return expenseService.allExpenses();
	}

	
	//----------CREATE-------------
	 @PostMapping("/expenses") // post: /expenses
	 public Expense creatExpense(
			 @RequestParam("expenseName") String expenseName,
			 @RequestParam("vendor") String vendor,
			 @RequestParam("amount") Double amount
			 ) {
		 Expense newExpense = new Expense(expenseName, vendor, amount);
		 return expenseService.createExpense(newExpense);
	 }
	 
	 //----------FIND ONE (/expenses/<id>)-----
	 
	 @GetMapping("/expenses/{id}")
	 public Expense oneExpense(@PathVariable("id") Long id) {
		 return expenseService.oneExpense(id);
	 }
	 
	 //-----------EDIT (FIND ONE + CREATE)--------
	 // 1. id from path variables
	 // 2. info from RequestParam / body
	 @PutMapping("/expenses/{id}") // needs to be called "ID", no variations
	 public Expense editExpense(
			 @PathVariable("id") Long id,
			 @RequestParam("expenseName") String expenseName,
			 @RequestParam("vendor") String vendor,
			 @RequestParam("amount") Double amount
			 ) {
		 
		 return expenseService.updateExpense2(id, expenseName, vendor, amount);
		 
	 }
	 
	 // -------------------DELETE------------------
	 @DeleteMapping("/expenses/{id}")
	 public void removeExpense(@PathVariable("id") Long id){
		 expenseService.deleteExpense(id);
	 }
}
