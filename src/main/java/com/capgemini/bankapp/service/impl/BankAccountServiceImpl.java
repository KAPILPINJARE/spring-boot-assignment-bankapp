package com.capgemini.bankapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.exceptions.AccountNotFoundException;
import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

@Service
@EnableTransactionManagement
public class BankAccountServiceImpl implements BankAccountService
{
	@Autowired
	private BankAccountDao bankAccountDao;
	
	public BankAccountServiceImpl(BankAccountDao bankAccountDao)
	{
		this.bankAccountDao = bankAccountDao;
	}

	@Override
	public double checkBalance(long accountId) throws AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance >= 0)
			return balance;
		else 
			throw new AccountNotFoundException("BankAccount doesnt exists...");
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException, AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance < 0)
			throw new AccountNotFoundException("BankAccount doesnt exists...");
		else if (balance - amount >= 0)
		{
			balance = balance - amount;
			bankAccountDao.updateBalance(accountId, balance);
			return balance;
		} else 
			throw new LowBalanceException("account balance not sufficient..");
	}

	@Override
	public double deposit(long accountId, double amount) throws AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance < 0)
			throw new AccountNotFoundException("BankAccount doesnt exists...");
		balance = balance + amount;
		bankAccountDao.updateBalance(accountId, balance);
		return balance;
	}

	@Override
	public boolean deleteBankAccount(long accountId) throws AccountNotFoundException
	{
		boolean result = bankAccountDao.deleteBankAccount(accountId);
		if(result)
		{
			return result;
		}
			else
			throw new AccountNotFoundException("BankAccount doesnt exists...");
	}

	
	@Override
	@Transactional
	public double fundTransfer(long fromAccount, long toAccount, double amount)
			throws LowBalanceException, AccountNotFoundException
	{
		double balance = 0;
		try
		{
			balance = withdraw(fromAccount, amount);
			deposit(toAccount, amount);
		} catch (LowBalanceException | AccountNotFoundException e)
		{
			throw e;
		}
		return balance;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account)
	{
		boolean result = bankAccountDao.addNewBankAccount(account);
			return result;
	}

	@Override
	public List<BankAccount> findAllBankAccount()
	{
		return bankAccountDao.findAllBankAccounts();
	}

	@Override
	public BankAccount searchForAccount(long accountId) throws AccountNotFoundException
	{
		BankAccount account = (BankAccount) bankAccountDao.searchForAccount(accountId);
		if(account == null)
			throw new AccountNotFoundException("account doesnot exists..");
		return account;
	}

	@Override
	public boolean updateAccount(BankAccount account)
	{
		boolean result = bankAccountDao.updateAccount(account);
		return result;
	}


	public double withdrawForFundTransfer(long accountId, double amount)
			throws LowBalanceException, AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance < 0)
			throw new AccountNotFoundException("BankAccount doesnt exists...");
		else if (balance - amount >= 0)
		{
			balance = balance - amount;
			bankAccountDao.updateBalance(accountId, balance);
			return balance;
		} else 
			throw new LowBalanceException("account balance not sufficient..");
	}

}
