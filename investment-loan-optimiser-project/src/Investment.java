import java.util.*;

public class Investment extends Asset {


	public Investment(double interestRate, double balance) {
		super(interestRate, balance);
	}

	@Override
	public String toString() {
		return "Investment [interestRate=" + this.interestRate + ", balance=$" + String.format("%.2f", balance) + "]";
	}

	@Override
	public Asset afterPaymentAndInterest(double payment) {
		double newBalance = (this.balance + payment) * this.interestRate;
		return new Investment(this.interestRate, newBalance);
	}
	
}