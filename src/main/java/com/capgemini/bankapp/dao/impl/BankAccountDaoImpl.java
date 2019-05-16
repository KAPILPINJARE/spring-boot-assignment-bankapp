package com.capgemini.bankapp.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.model.BankAccount;

@Repository
public class BankAccountDaoImpl implements BankAccountDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;	
	
	public void setJdbc(JdbcTemplate jdbcTemplate)
    	{
        	this.jdbcTemplate = jdbcTemplate;
    	}
	
	
	@Override
	public double getBalance(long accountId)
	{
		double result = -1;
		try{
		result = jdbcTemplate.queryForObject("select account_balance from bankaccounts where account_id = ?",new Object[] {accountId},Double.class);
		}catch(Exception e){}
		return result;
	}


	@Override
	public int updateBalance(long accountId, double newBalance)
	{
		String query = "UPDATE BANKACCOUNTS SET ACCOUNT_BALANCE = '"+newBalance+"' WHERE ACCOUNT_ID = '"+accountId+"' ";
		return jdbcTemplate.update(query);
	}

	
	@Override
	public boolean deleteBankAccount(long accountId)
	{
		String query = "DELETE FROM BANKACCOUNTS WHERE ACCOUNT_ID = " + accountId;
		if(jdbcTemplate.update(query) == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account)
	{
		String query = "INSERT INTO BANKACCOUNTS (CUSTOMER_NAME,ACCOUNT_TYPE,ACCOUNT_BALANCE) VALUES ('"+account.getAcccoutHolderName()+"','"+account.getAccountType()+"','"+account.getAccountBalance()+"')";
		if(jdbcTemplate.update(query) == 1)
			return true;
		else
			return false;  
	}

	@Override
	public List<BankAccount> findAllBankAccounts() 
	{
		return jdbcTemplate.query("select * from bankaccounts",( rs,rowNum) -> {return getBankAccount(rs);});        
	}


	@Override
	public BankAccount searchForAccount(long accountId)
	{
		BankAccount account = null;
		try
		{
			account = jdbcTemplate.queryForObject("select * from bankaccounts where account_id = ?", new Object[] {accountId},(rs,rowNum) -> {return getBankAccount(rs);});
		}catch(Exception e){}
		return account;
	}
	
	@Override
	public boolean updateAccount(BankAccount account)
	{
		String query = "UPDATE BANKACCOUNTS SET CUSTOMER_NAME = '"+account.getAcccoutHolderName()+"',ACCOUNT_TYPE = '"+account.getAccountType()+"' WHERE ACCOUNT_ID = '"+account.getAccountId()+"'";
		if(jdbcTemplate.update(query) == 1)
			return true;
		else
			return false;
	}
	
	
	public BankAccount getBankAccount(ResultSet rs)
	{
		BankAccount account = new BankAccount();
                		try{
				account.setAccountId(rs.getLong(1));
                		account.setAcccoutHolderName(rs.getString(2));
				account.setAccountType(rs.getString(3));
				account.setAccountBalance(rs.getDouble(4));
		}catch(Exception e){}
                	return account;	
	}

}
