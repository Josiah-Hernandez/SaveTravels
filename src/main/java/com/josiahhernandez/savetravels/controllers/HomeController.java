package com.josiahhernandez.savetravels.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.josiahhernandez.savetravels.models.Expense;
import com.josiahhernandez.savetravels.services.ExpenseService;

@Controller
public class HomeController {
	
	@Autowired
	private ExpenseService expenseService;
	
	
	//------------ DASHBOARD ------------
	@GetMapping("/expenses")
	public String dashboard(Model model) {
		// get the list of expenses from the service (from db)
		List<Expense> expenses = expenseService.allExpenses();
		// add the list to the jsp (Model model)
		model.addAttribute("expenseList", expenses);
		return "dashboard.jsp";
	}
	
	//------------ DETAIL PAGE ------------
	@GetMapping("/expenses/{id}")
	public String oneExpense(@PathVariable("id") Long id, Model model) {
		// get the specific expenses from the service (from db)
		Expense foundExpense = expenseService.oneExpense(id);
		// add the expense to the jsp (Model model)
		model.addAttribute("oneExpense", foundExpense);
		return "details.jsp";
		
	}
	
	// ------------- CREATE ------------
	// Render the form
	@GetMapping("/expenses/add")
	public String renderCreateForm(Model model) {
		model.addAttribute("expense", new Expense());
		return "createPage.jsp";
	}
	
	// Process the form
	@PostMapping("/expenses")
	public String processCreate(@Valid @ModelAttribute("expense")Expense expense, BindingResult result 
			) {
		if(result.hasErrors()) { // if the result has error, the modelAttribute expense will have error field
			return "createPage.jsp";
		}else {
			expenseService.createExpense(expense);
			return "redirect:/expenses";
		}
		

	}
	
	// ------------- EDIT (findOne, create)------------
	// Render the form (use the id from path, to find that donation)
	@GetMapping("/expenses/edit/{id}")
	public String renderEditForm(@PathVariable("id")Long id, Model model) {
		Expense foundExpense = expenseService.oneExpense(id);
		model.addAttribute("expense", foundExpense);
		return "editPage.jsp";
	}
	
	// Process the form
	@PutMapping("/expenses/edit/{id}")
	public String processEdit(@Valid @ModelAttribute("expense")Expense expense, BindingResult result) {
		if(result.hasErrors()) {
			return "editPage.jsp";
		}else {
			expenseService.updateExpense(expense);
			return "redirect:/expenses";
		}
	}
	
	// ------------- DELETE ------------
	@DeleteMapping("/expenses/{id}")
	public String processDelete(@PathVariable("id")Long id) {
		expenseService.deleteExpense(id);
		return "redirect:/";
	}
	
	
	// -------------- MAIN with DASHBOARD and CREATE ---------
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("expenseList", expenseService.allExpenses());
		model.addAttribute("expense", new Expense());
		return "main.jsp";
	}
	
	
	// Process the form
	@PostMapping("/")
	public String processMainPageCreate(@Valid @ModelAttribute("expense")Expense expense, BindingResult result, Model model
			) {
		if(result.hasErrors()) {
			// render the main.jsp, which includes the form (taken care of by modelAttribute) + dashboard
			model.addAttribute("expenseList", expenseService.allExpenses());
			return "main.jsp";
		}else {
			expenseService.createExpense(expense);
			return "redirect:/";
		}
		

	}
	
}


