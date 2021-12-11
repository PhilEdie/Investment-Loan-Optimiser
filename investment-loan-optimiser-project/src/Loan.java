import java.util.*;

public class Loan extends Account {

	private double minimumPayment;
	private boolean paidOff = false;

	public Loan(String accountName, double interestRate, double balance, double minimumPayment) {
		super(accountName, interestRate, balance);
		if (balance > 0) {
			throw new IllegalArgumentException("Error, a balance on a Loan object should be negative.");
		}
		this.minimumPayment = minimumPayment;
	}
	
	public Loan(Loan toCopy) {
		super(toCopy.getAccountName(), toCopy.getInterestRate(), toCopy.getBalance());
		this.minimumPayment = toCopy.getMinimumPayment();
	}

	/**
	 * Adds the payment to the account balance. 
	 * If balance + payment is greater than zero, return the extra cash
	 * as "change". 
	 * 
	 * @return	Any leftover money which would make the overall balance greater
	 * 			than zero. 
	 */
	@Override
	public double makePayment(double payment) {
		//We shouldn't be making payments when the loan is already paid off. 
		assert isPaidOff() == false;
		
		double originalBalance = getBalance();
		
		if (getBalance() + payment > 0) {
			setPaymentForPeriod(getPaymentForPeriod() + Math.abs(originalBalance));
			setBalance(0);
			this.paidOff = true;
			return originalBalance + payment;
		} else {
			setPaymentForPeriod(getPaymentForPeriod() + payment);
			setBalance(getBalance() + payment);			
			return 0.0;
		}
	}

	public double makeMinimumPayment() {
		return makePayment(this.minimumPayment);
	}

	public double getMinimumPayment() {
		return this.minimumPayment;
	}
	
	public boolean isPaidOff() {
		return this.paidOff;
	}

	@Override
	public String toString() {
		return getAccountName() + "[interestRate=" + getInterestRate() + ", balance=$" + String.format("%.2f", getBalance())
				+ ", minimumPayment=$" + String.format("%.2f", getMinimumPayment()) + "]";
	}

}