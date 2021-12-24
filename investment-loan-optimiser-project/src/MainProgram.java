import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainProgram {

	private List<Account> startingAccounts = new ArrayList<Account>();
	/*
	 * The history stack stores the state of each Account object in each payment period. 
	 */
	private Stack<List<Account>> history = new Stack<List<Account>>();

	public MainProgram() {
	}

	public void run(int totalIterations, double availableFunds) {
		assert totalIterations > 0;
		assert !this.startingAccounts.isEmpty();
		assert availableFunds > 0;

		this.history = new Stack<List<Account>>();
		this.history.add(startingAccounts);
		for (int i = 0; i < totalIterations; i++) {
			runOnce(history, availableFunds);
		}
	}

	/**
	 * Represents the steps taken within one payment period.
	 * Steps:
	 * 1. Create a copy of the previous payment period, we will be updating these.
	 * 2. Remove all paid off loans.
	 * 3. Sort the payment period so that higher priority accounts will be paid first. 
	 * 4. Pay the minimums on each of the Loan obejcts.
	 * 5. Pay the remaining available funds into the highest priority account,
	 * 	  continue down the list until no funds remain.
	 * 6. Apply interest to all accounts. 
	 * 
	 * Once the payment period is complete, a new list of accounts is added to the 
	 * top of the history stack. 
	 * 
	 * @param history	
	 * @param availableFunds
	 */
	public void runOnce(Stack<List<Account>> history, double availableFunds) {
		
		//
		if(history.peek().isEmpty()) {
			return;
		}
		
		assert availableFunds > 0;
		double remainingIncome = availableFunds;
		List<Account> accounts = createCopyOfAccounts(history.peek());
		
		//Remove paid off loans from working list.
		List<Account> paidOffLoans = new ArrayList<Account>();
		for(Account account : accounts) {
			if(account instanceof Loan && ((Loan) account).isPaidOff()) {
				paidOffLoans.add(account);
				System.out.println("Found paid off account.");
			}
		}
		System.out.println("size before: " + accounts.size());
		accounts.removeAll(paidOffLoans);
		System.out.println("size after: " + accounts.size());
		
		// Sort accounts so high priority accounts will be paid first.
		Collections.sort(accounts);

		// Pay minimums into each loan:
		remainingIncome = payMinimumsOnLoans(accounts, remainingIncome);

		// Pay highest priority accounts first, then distribute remaining funds to
		// following accounts.

		while (remainingIncome > 0) {
			for (Account account : accounts) {
				if (account instanceof Loan && ((Loan) account).isPaidOff()) {
					continue; // Skip paid off loans.
				}
				remainingIncome = account.makePayment(remainingIncome);
			}

			
			if (Utilities.containsAllLoans(accounts) && Utilities.allLoansPaidOff(accounts)) {
				break;
			}
		}

		// Once done, apply interest.
		applyInterestToAll(accounts);

		history.add(accounts);
	}

	public double payMinimumsOnLoans(List<Account> accounts, double availableFunds) {
		assert availableFunds > 0;
		double incomeAfterMinimums = availableFunds;
		for (Account account : accounts) {
			if (account instanceof Loan && !((Loan) account).isPaidOff()) {
				Loan loan = (Loan) account;
				double change = loan.makeMinimumPayment();
				incomeAfterMinimums = incomeAfterMinimums - loan.getMinimumPayment() + change;
			}
		}

		// There should still be some money left over to invest after all minimum
		// payments.
		assert incomeAfterMinimums >= 0;

		return incomeAfterMinimums;

	}

	public void applyInterestToAll(List<Account> accounts) {
		for (Account account : accounts) {
			account.applyInterest();
		}
	}

	public List<Account> createCopyOfAccounts(List<Account> toCopy) {
		
		assert !toCopy.isEmpty();
		List<Account> copied = new ArrayList<Account>();

		for (Account account : toCopy) {
			if (account instanceof Loan) {
				copied.add(new Loan((Loan) account));
			} else if (account instanceof Investment) {
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
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.printf(" %14s | %20s | %12s | %12s | %14s | %20s | %20s |", "Payment Period", "Account Name",
				"Account Type", "Balance", "Interest Rate", "Payments Made", "Interest For Period");
		System.out.println();
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < history.size(); i++) {
			for (Account account : history.get(i)) {
				System.out.format(" %14s | %20s | %12s | %12s | %14s | %20s | %20s |", i, account.getAccountName(),
						account.getClass().getSimpleName(), String.format("%.2f", account.getBalance()),
						account.getInterestRate(), String.format("%.2f", account.getPaymentForPeriod()),
						String.format("%.2f", account.getInterestForPeriod()));
				System.out.println();
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------");
		}
	}

	public boolean addAccount(Account toAdd) {
		return startingAccounts.add(toAdd);
	}

	public boolean removeAccount(Account toRemove) {
		return startingAccounts.remove(toRemove);
	}

	public boolean hasAccountWithName(String name) {
		for (Account account : startingAccounts) {
			if (account.getAccountName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public double getTotalMinimumPayments() {
		assert this.startingAccounts.size() > 0;
		double sum = 0;
		for (Account account : this.startingAccounts) {
			if (account instanceof Loan) {
				sum += ((Loan) account).getMinimumPayment();
			}
		}
		return sum;
	}

	

	public boolean removeAccount(String accountName) {
		for (Account account : startingAccounts) {
			if (account.getAccountName().equals(accountName)) {
				startingAccounts.remove(account);
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAccountWithName(String accountName) {
		for (Account account : startingAccounts) {
			if (account.getAccountName().equals(accountName)) {
				return true;
			}
		}
		return false;
	}
	
	public String getDefaultAccountName(Class<? extends Account> class1) {
		String name = class1.getSimpleName();
		int suffix = 1;
		while(containsAccountWithName(name + suffix)) {
			suffix++; 
		}
		return (name + suffix);
	}
	
	
	
	

}
