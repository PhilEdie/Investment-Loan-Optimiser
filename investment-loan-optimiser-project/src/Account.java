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
	
	public void setInterestForPeriod(double interestForPeriod) {
		this.interestForPeriod = interestForPeriod;
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
		
		//Handling same classes, different interest rates.
		if(this.getClass().equals(other.getClass())) {
			if(this.getInterestRate() > other.getInterestRate()) {
				return -1;
			}
				return 1;
		}
				
		//Handling different classes, same interest rate.
		if(this.getInterestRate() == other.getInterestRate()) {
			if(this instanceof Investment) {
				return 1;
			}
			return -1;
		}
		
		//Same class, same interest rate. Return in alphabetical order.
		if(this.getAccountName().compareTo(other.getAccountName()) < 0) {
			return -1;
		} else if(this.getAccountName().compareTo(other.getAccountName()) > 0) {
			return 1;
		}
		return 0;
	}
	
	
	
}