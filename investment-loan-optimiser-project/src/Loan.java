/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

import java.util.*;

public class Loan extends Asset {

	public final double minimumPayment;

	public Loan(double interestRate, double balance, double minimumPayment) {
		super(interestRate, balance);
		this.minimumPayment = minimumPayment;
	}

	public double getMinimumPayment() {
		return minimumPayment;
	}
	

	@Override
	public String toString() {
		return "Loan [interestRate=" + this.interestRate
				+ ", balance=$" + String.format("%.2f", this.balance) +", minimumPayment=$" 
				+ String.format("%.2f", minimumPayment) + "]";
	}

	@Override
	public Asset afterPaymentAndInterest(double payment) {
		double newBalance = (this.balance + payment) * this.interestRate;
		return new Loan(this.interestRate, newBalance, this.minimumPayment);
	}
	
}