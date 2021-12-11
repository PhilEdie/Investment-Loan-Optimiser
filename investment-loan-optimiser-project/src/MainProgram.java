import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MainProgram {
	
	private Stack<List<Account>> history = new Stack<List<Account>>();
	
	public MainProgram(List<Account> startAccounts, double income, double totalMonths) {
		this.history.add(startAccounts);
		
		for(int i = 0; i < totalMonths; i++) {
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
