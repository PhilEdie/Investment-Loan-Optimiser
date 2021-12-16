import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

@SuppressWarnings("deprecation")
public class MainProgram extends Observable {
	
	private Stack<List<Account>> history = new Stack<List<Account>>();
	private double income;
	private double totalMonths;
	
	public MainProgram(List<Account> startAccounts, double income, double totalMonths) {
		this.history.add(startAccounts);
		this.income = income;
		this.totalMonths = totalMonths;
	}
	
	public void run(Stack<List<Account>> history, double income, int iterations) {
		for(int i = 0; i < iterations; i++) {
			runOnce(history, income);
		}
	}
	
	
	
	public void runOnce(Stack<List<Account>> history, double income) {
		double remainingIncome = income;
		List<Account> accounts = createCopyOfAccounts(this.history.peek());
		
		//Sort accounts so high priority accounts will be paid first.
		Collections.sort(accounts);
		
		//Pay minimums into each loan:
		remainingIncome = payMinimumsOnLoans(accounts, remainingIncome);
		
		//Pay highest priority accounts first, then distribute remaining funds to following accounts.
		while(remainingIncome > 0) {
			for(Account account : accounts) {
				remainingIncome = account.makePayment(remainingIncome);
			}
		}
		
		//Once done, apply interest.
		applyInterestToAll(accounts);
		
		history.add(accounts);
	}
	
	public double payMinimumsOnLoans(List<Account> accounts, double income) {
		double incomeAfterMinimums = income;
		for(Account account : accounts) {
			if(account instanceof Loan){
				Loan loan = (Loan) account;
				double change = loan.makeMinimumPayment();
				incomeAfterMinimums = incomeAfterMinimums - loan.getMinimumPayment() + change;
			}
		}
		
		//There should still be some money left over to invest after all minimum payments.
		assert incomeAfterMinimums >= 0;
		
		return incomeAfterMinimums;
		
	}
	
	public void applyInterestToAll(List<Account> accounts) {
		for(Account account : accounts) {
			account.applyInterest();
		}
	}
	
	public List<Account> createCopyOfAccounts(List<Account> toCopy) {
		List<Account> copied = new ArrayList<Account>();
		
		for(Account account : history.peek()) {
			if(account instanceof Loan) {
				copied.add(new Loan((Loan) account));
			} else if(account instanceof Investment) {
				copied.add(new Investment((Investment) account));
			}
		}
		
		return copied;
	}
	
	public Stack<List<Account>> getHistory(){
		return this.history;
	}
	
	public void printHistory(Stack<List<Account>> history) {
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.printf(" %14s | %20s | %12s | %12s | %14s | %20s | %20s |", "Payment Period", "Account Name", "Account Type", "Balance", "Interest Rate", "Payments Made", "Interest For Period");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < history.size(); i++) {
			for(Account account : history.get(i)) {
				System.out.format(" %14s | %20s | %12s | %12s | %14s | %20s | %20s |",
						i,
						account.getAccountName(), 
						account.getClass().getSimpleName(), 
						String.format("%.2f", account.getBalance()),
						account.getInterestRate(),
						String.format("%.2f", account.getPaymentForPeriod()),
						String.format("%.2f", account.getInterestForPeriod()));
				System.out.println();
			}
			System.out.println("------------------------------------------------------------------------------------------------------");
		}
		
		
	}
	
	
	public static void main(String[] args) {
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Loan("Loan 1", 1.1, -1500, 100));
		accounts.add(new Loan("Loan 2", 1.25, -300, 50));
		accounts.add(new Investment("Investment 1", 1.08, 5000));
		accounts.add(new Investment("Investment 2", 1.04, 20000));
		double income = 5000;
		int totalMonths = 12;
		
		new MainProgram(accounts, income, totalMonths);
	}

}
