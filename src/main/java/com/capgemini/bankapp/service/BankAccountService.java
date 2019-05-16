package com.capgemini.bankapp.service;

import java.util.List;

import com.capgemini.bankapp.exceptions.AccountNotFoundException;
import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountService
{
	public double checkBalance(long accountId) throws AccountNotFoundException;
	public double withdraw(long accountId,double amount)throws LowBalanceException, AccountNotFoundException;
	public double deposit(long accountId,double amount) throws AccountNotFoundException;
	public boolean deleteBankAccount(long accountId) throws AccountNotFoundException;
	public double fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException, AccountNotFoundException;
	public boolean addNewBankAccount(BankAccount account);
	public List<BankAccount> findAllBankAccount();
	public BankAccount searchForAccount(long accountId) throws AccountNotFoundException;
	public boolean updateAccount(BankAccount account);
	
	}
