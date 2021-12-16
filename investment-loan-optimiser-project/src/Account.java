import java.util.*;

public abstract class Account implements Comparable<Account>{


	private double interestRate;
	private double balance;
	private String accountName;
	private double interestForPeriod = 0;
	private double paymentForPeriod = 0;

	
	public Account(String accountName, double interestRate, double balance) {
		this.accountName = accountName;
		this.interestRate = interestRate;
		this.balance = balance;
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestForPeriod() {
		return this.interestForPeriod;
	}

	public double getPaymentForPeriod() {
		return this.paymentForPeriod;
	}

	protected void setBalance(double newBalance) {
		this.balance = newBalance;
	}
	

	public void setPaymentForPeriod(double paymentForPeriod) {
		this.paymentForPeriod = paymentForPeriod;
	}

	public abstract double makePayment(double payment);
	
	public void applyInterest() {
		double beforeBalance = balance;
		setBalance(getBalance() * getInterestRate());
		double afterBalance = balance;
		this.interestForPeriod = afterBalance - beforeBalance;
	}
	
	public int compareTo(Account other) {
		
		//First prioritise higher interest rate accounts.
		if(this.getInterestRate() > other.getInterestRate()) {
			return -1;
		} else if (this.getInterestRate() < other.getInterestRate()) {
			return 1;
		} else {
			
			//The interest rates are equal. Prioritise loans over investments. 
			if(this instanceof Loan && other instanceof Investment) {
				return -1;
			} else if(this instanceof Investment && other instanceof Loan) {
				return 1;
			}
		}

		//Same class, same interest rate. Return in alphabetical order.
		if(this.getAccountName().compareTo(other.getAccountName()) < 0) {
			return -1;
		} else if(this.getAccountName().compareTo(other.getAccountName()) > 0) {
			return 1;
		}
		
		//Both accounts are the same. 
		return 0;
	}
	
	
	
}