package com.capgemini.bankapp.exceptions;

public class LowBalanceException extends Exception
{

	public LowBalanceException()
	{
	}

	public LowBalanceException(String message)
	{
		super(message);
	}

}
