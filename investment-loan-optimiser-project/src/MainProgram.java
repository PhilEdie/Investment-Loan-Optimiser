import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

public class MainProgram  {
	
	private List<Account> startingAccounts = new ArrayList<Account>();
	private Stack<List<Account>> history = new Stack<List<Account>>();
	
	public MainProgram() {}
	
	public void run(int totalIterations, double income) {
		assert totalIterations > 0;
		assert !this.startingAccounts.isEmpty();
		assert income > 0;
		
		this.history = new Stack<List<Account>>();
		this.history.add(startingAccounts);
		for(int i = 0; i < totalIterations; i++) {
			runOnce(history, income);
		}
		printHistory(this.history);
	}
	
	public void runOnce(Stack<List<Account>> history, double income) {
		assert !history.peek().isEmpty();
		assert income > 0;
		double remainingIncome = income;
		List<Account> accounts = createCopyOfAccounts(history.peek());
		
		//Sort accounts so high priority accounts will be paid first.
		Collections.sort(accounts);
		
		//Pay minimums into each loan:
		remainingIncome = payMinimumsOnLoans(accounts, remainingIncome);
		
		//Pay highest priority accounts first, then distribute remaining funds to following accounts.
		while(remainingIncome > 0) {
			for(Account account : accounts) {
				if(account instanceof Loan && ((Loan) account).isPaidOff()) {
					continue;	//Skip paid off loans.
				}
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
			if(account instanceof Loan && !((Loan) account).isPaidOff()){
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
		
		for(Account account : toCopy) {
			if(account instanceof Loan) {
				copied.add(new Loan((Loan) account));
			} else if(account instanceof Investment) {
				copied.add(new Investment((Investment) account));
			}
		}
		
		return copied;
	}
	
	public List<Account> getStartingAccounts() {
		return startingAccounts;
	}
	
	public Stack<List<Account>> getHistory() {
		return history;
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
	
	public boolean addAccount(Account toAdd) {
		return startingAccounts.add(toAdd);
	}
	
	public boolean removeAccount(Account toRemove) {
		return startingAccounts.remove(toRemove);
	}
	
	public boolean removeAccount(String accountName) {
		for(Account account : startingAccounts) {
			if(account.getAccountName().equals(accountName)) {
				startingAccounts.remove(account);
				return true;
			}
		}
		return false;
	}

}
