import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Contains information on each account.
 * Responsible for applying payment period operations to each account, then
 * keeps a history of all payment periods.
 * 
 * @author Phil Edie
 *
 */
public class AccountManager {

	private final List<Account> startingAccounts = new ArrayList<Account>();
	/*
	 * The history stack stores the state of each Account object in each payment period. 
	 */
	private Stack<List<Account>> history = new Stack<List<Account>>();

	public AccountManager() {
	}

	/**
	 * Calls runOnce() for a given number of iterations. 
	 * @param totalIterations	The number of times to call runOnce()
	 * @param availableFunds	The total amount of funds to distribute within each payment period. 
	 */
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

	/**
	 * Loops through each account, pays the minimum payment for each loan within the list.
	 * Returns the remaining funds. 
	 * @param accounts			List of accounts to pay.
	 * @param availableFunds	The pool of funds which is used to pay the minimum payments.	
	 * @return					The remaining funds after all minimum payments are paid.
	 */
	public double payMinimumsOnLoans(List<Account> accounts, double availableFunds) {
		assert availableFunds > 0;
		double remainingFunds = availableFunds;
		for (Account account : accounts) {
			if (account instanceof Loan && !((Loan) account).isPaidOff()) {
				Loan loan = (Loan) account;
				double change = loan.makeMinimumPayment();
				remainingFunds = remainingFunds - loan.getMinimumPayment() + change;
			}
		}

		// There should still be some money left over to invest after all minimum
		// payments.
		assert remainingFunds >= 0;

		return remainingFunds;

	}

	/**
	 * Loops through each account in the list and calls applyInterest().
	 * @param accounts	The accounts to call applyInterest() on. 
	 */
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


	/**
	 * Calculate the sum of all minimum payments within startingAccounts.
	 * @return	The sum. 
	 */
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

	

	/**
	 * Searches startingAccounts for an account matching the given name.
	 * removes the matching account. 
	 * @param accountName	The account to search for. 
	 * @return				True if an account is successfully removed, false if not. 
	 */
	public boolean removeAccount(String accountName) {
		for (Account account : startingAccounts) {
			if (account.getAccountName().equals(accountName)) {
				startingAccounts.remove(account);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks startingAccounts to see if an account with the provided name exists.
	 * @param accountName	The name to search for.
	 * @return				True if an account exists, false if not. 
	 */
	public boolean containsAccountWithName(String accountName) {
		for (Account account : startingAccounts) {
			if (account.getAccountName().equals(accountName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns unique default account name depending on the provided type.
	 * Eg: "Loan1", or "Investment1".
	 * 
	 * @param type	The type of account.
	 * @return		A unique account name. 
	 */
	public String getDefaultAccountName(Class<? extends Account> type) {
		String name = type.getSimpleName();	//Either "Loan" or "Investment".
		int suffix = 1;	//The number following the account name. 
		
		//If an account with that default name already exists,
		//increase the suffix by one. 
		while(containsAccountWithName(name + suffix)) {
			suffix++; 
		}
		return (name + suffix);
	}
	
	
	
	

}
