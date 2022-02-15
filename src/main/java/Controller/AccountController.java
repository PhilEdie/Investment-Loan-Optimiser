package Controller;

import Model.Account;
import Model.AccountsModel;
import Model.Investment;
import Model.Loan;

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
public class AccountController {



	private final AccountsModel accountsModel;

	public AccountController() {
		this.accountsModel = new AccountsModel();
	}

	/**
	 * Calls runOnce() for a given number of iterations. 
	 * @param totalIterations	The number of times to call runOnce()
	 * @param availableFunds	The total amount of funds to distribute within each payment period. 
	 */
	public void run(int totalIterations, double availableFunds) {
		if(totalIterations <= 0){
			throw new IllegalArgumentException("Error. totalIterations should be greater than 0.");
		}
		if(accountsModel.getStartingAccounts().isEmpty()){
			throw new IllegalStateException("Error. Starting accounts should not be empty.");
		}

		if(availableFunds <= 0){
			throw new IllegalArgumentException("Error. availableFunds should be greater than 0.");
		}

		accountsModel.clearHistory();
		accountsModel.addToHistory(accountsModel.getStartingAccounts());
		for (int i = 0; i < totalIterations; i++) {
			runOnce(accountsModel.getHistory(), availableFunds);
		}
	}

	/**
	 * Represents the steps taken within one payment period.
	 * Steps:
	 * 1. Create a copy of the previous payment period, we will be updating these.
	 * 2. Remove all paid off loans.
	 * 3. Sort the payment period so that higher priority accounts will be paid first. 
	 * 4. Pay the minimums on each of the Model.Loan obejcts.
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

		if(availableFunds <= 0){
			throw new IllegalArgumentException("Error. availableFunds should be greater than 0.");
		}

		if(history.peek().isEmpty()) {
			throw new IllegalArgumentException("Error. availableFunds should be greater than 0.");
		}

		double remainingIncome = availableFunds;
		List<Account> accounts = createCopyOfAccounts(history.peek());
		
		//Remove paid off loans from working list.

		List<Account> paidOffLoans = new ArrayList<Account>();
		for(Account account : accounts) {
			if(account instanceof Loan && ((Loan) account).isPaidOff()) {
				paidOffLoans.add(account);

			}
		}
		accounts.removeAll(paidOffLoans);
		
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
		if(availableFunds <= 0){
			throw new IllegalArgumentException("Error. Available funds should be greater than 0.");
		}

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
		if(remainingFunds < 0){
			throw new IllegalArgumentException("Error. Remaining funds shouldn't be negative after paying minimums.");
		}

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

		if(toCopy.isEmpty()){
			throw new IllegalArgumentException("Error. toCopy shouldn't be empty.");
		}

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

	/**
	 * Calculate the sum of all minimum payments within startingAccounts.
	 * @return	The sum. 
	 */
	public double getTotalMinimumPayments() {

		if(accountsModel.getStartingAccounts().size() == 0){
			throw new IllegalStateException("Error. accountsModel should have at least one account.");
		}

		double sum = 0;
		for (Account account : accountsModel.getStartingAccounts()) {
			if (account instanceof Loan) {
				sum += ((Loan) account).getMinimumPayment();
			}
		}
		return sum;
	}

	

	/**
	 * Searches startingAccounts for an account matching the given name.
	 * removes the matching account. 
	 * @param accountName    The account to search for.
	 */
	public void removeAccount(String accountName) {
		for (Account account : accountsModel.getStartingAccounts()) {
			if (account.getAccountName().equals(accountName)) {
				accountsModel.removeStartingAccount(account);
				return;
			}
		}
	}
	
	/**
	 * Checks startingAccounts to see if an account with the provided name exists.
	 * @param accountName	The name to search for.
	 * @return				True if an account exists, false if not. 
	 */
	public boolean containsAccountWithName(String accountName) {
		for (Account account : accountsModel.getStartingAccounts()) {
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

	public AccountsModel getAccountsModel() {
		return accountsModel;
	}
}
