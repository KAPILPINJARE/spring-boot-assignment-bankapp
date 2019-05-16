package com.capgemini.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapp.exceptions.AccountNotFoundException;
import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

@Controller
@RequestMapping("/bank")
public class BankAppController {
	
	@Autowired
	private BankAccountService service;
	
	@RequestMapping("/newAccount")
	public String addAccount(@RequestParam("customer_name") String name,
							@RequestParam("account_type") String type,@RequestParam("account_balance") double balance) {
		service.addNewBankAccount(new BankAccount(name, type, balance));
		return "success";
	}
	
	@RequestMapping("/withdraw")
	public String withdraw(@RequestParam("account_id") Long id,@RequestParam("amount") double amount,Model model){
		try {
			double result = service.withdraw(id, amount);
			model.addAttribute("message", "Balance after withdraw " + result);
		} catch (LowBalanceException | AccountNotFoundException e) {
			// TODO Auto-generated catch block
			model.addAttribute("message", e.getMessage());
		}
		return "withdrawsuccess";
	}
	
	@RequestMapping("/deposit")
	public String deposit(@RequestParam("account_id") Long id,@RequestParam("amount") double amount,Model model){
		try {
			double result = service.deposit(id, amount);
			model.addAttribute("message","Balance after despsit " + result);
		} catch (AccountNotFoundException e) {
			model.addAttribute("message",e.getMessage());
		}
		return "withdrawsuccess";
	}
	
	@RequestMapping("/fund")
	public String fundTransfer(@RequestParam("sender_account_id") Long senderId,
			@RequestParam("receiver_account_id") Long receiverId,@RequestParam("amount") double amount,Model model) {
		try {
			double result = service.fundTransfer(senderId, receiverId, amount);
			model.addAttribute("message","Balance after transfer " + result);
		} catch (LowBalanceException | AccountNotFoundException e) {
			model.addAttribute("message", e.getMessage());
		}
		return "withdrawsuccess";
	}
	
	@RequestMapping("/searchAccount")
	public String searchAccount(@RequestParam("account_id") long id,Model model) {
		try {
			BankAccount account = service.searchForAccount(id);
			model.addAttribute("accounts",account);
		} catch (AccountNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "withdrawsuccess";
		}
		return "searchAccount";
	}
	
	@RequestMapping("/checkBalance")
	public String checkBalance(@RequestParam("account_id") long id,Model model) {
		try {
			double result = service.checkBalance(id);
			model.addAttribute("message","Your Balance is " + result);
		} catch (AccountNotFoundException e) {
			model.addAttribute("message",e.getMessage());
		}
		return "withdrawsuccess";
	}
	
	@RequestMapping("/displayAll")
	public String displayAll(Model model) {
		List<BankAccount> accounts = service.findAllBankAccount();
		model.addAttribute("accounts" , accounts);
		return "display";
	}
	
	@RequestMapping("/delete")
	public String deleteAccount(@RequestParam("account_id") long id,Model model) {
		try {
			boolean result = service.deleteBankAccount(id);
			if(result)
				model.addAttribute("message","Account Deleted Successfully...");
			else
				model.addAttribute("message","Failed To Delete Account...");
		} catch (AccountNotFoundException e) {
			model.addAttribute("message",e.getMessage());
		}
		return "withdrawsuccess";
	}
	
	@RequestMapping("/updateGetId")
	public String checkForUpdate(@RequestParam("account_id") long id,Model model) {
		try {
			BankAccount account = service.searchForAccount(id);
			model.addAttribute("accounts",account);
		} catch (AccountNotFoundException e) {
			model.addAttribute("message",e.getMessage());
			return "withdrawsuccess";
		}
		return "updateMyAccount";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("account_id") long id,
			@RequestParam("customer_name") String name,@RequestParam("account_type") String type,
			@RequestParam("account_balance") double balance,Model model)
	{
		BankAccount account = new BankAccount(id,name,type,balance);
		if(service.updateAccount(account))
			model.addAttribute("message","Account Updated Successfully...");
		else
			model.addAttribute("message","Failed To Update Account...");
		return "withdrawsuccess";
	}
}
