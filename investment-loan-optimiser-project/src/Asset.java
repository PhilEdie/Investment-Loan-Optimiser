import java.util.*;

public abstract class Asset {


	public final double interestRate;
	public final double balance;

	public Asset(double interestRate, double balance) {
		this.interestRate = interestRate;
		this.balance = balance;
	}
	
	public abstract Asset afterPaymentAndInterest(double payment);
}