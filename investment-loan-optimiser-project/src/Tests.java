import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void payOffLoanWithPayment() {
		Loan loan = new Loan("Loan 1", 1.05, -500, 100);
		double leftoverIncome = loan.makePayment(1000);
		assert(leftoverIncome == 500.0);
	}
	
	@Test
	void payOffLoanWithMinimumPayment() {
		Loan loan = new Loan("Loan 1", 1.05, -100, 200);
		double leftoverIncome = loan.makeMinimumPayment();
		assert(leftoverIncome == 100.0);
	}
	
	@Test 
	void sortingAccounts1() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Loan("Loan 1", 1.25, -300, 50));
		toSort.add(new Loan("Loan 2", 1.1, -1500, 100));
		toSort.add(new Investment("Investment 1", 1.08, 5000));
		toSort.add(new Investment("Investment 2", 1.04, 20000));
		
		List<Account> expected = new ArrayList<Account>(toSort);
		Collections.sort(toSort);
		assertEquals(expected, toSort);
	}
	
	@Test 
	void sortingAccounts2() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Loan("Loan 1", 1.25, -300, 50));
		toSort.add(new Investment("Investment 1", 1.25, 5000));
		
		List<Account> expected = new ArrayList<Account>(toSort);
		Collections.sort(toSort);
		assertEquals(expected, toSort);
	}
	
	@Test 
	void sortingAccounts3() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Loan("Loan 1", 1.26, -300, 50));
		toSort.add(new Loan("Loan 2", 1.25, -300, 50));
		toSort.add(new Investment("Investment 1", 1.25, 5000));
		
		List<Account> expected = new ArrayList<Account>(toSort);
		Collections.sort(toSort);
		assertEquals(expected, toSort);
	}
	
	@Test
	void sortingEqualInterestRateAccounts() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Investment("Investment 2", 1.08, 5000));
		toSort.add(new Investment("Investment 1", 1.1, 5000));
		toSort.add(new Loan("Loan 1", 1.1, -10000, 100));
		toSort.add(new Loan("Loan 2", 1.08, -10000, 100));
		Collections.sort(toSort);
		
		List<Account> expected = new ArrayList<Account>();
		
		expected.add(new Loan("Loan 1", 1.1, -10000, 100));
		expected.add(new Investment("Investment 1", 1.1, 5000));
		expected.add(new Loan("Loan 2", 1.08, -10000, 100));
		expected.add(new Investment("Investment 2", 1.08, 5000));
		
		assert toSort.toString().equals(expected.toString());
		
	}
	
	@Test
	void accumulatingInterest() {
		
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Loan("Loan 1", 1.05, -1000, 100));
		MainProgram p = new MainProgram(accounts, 100, 1);
		assert(p.getHistory().peek().get(0).getBalance() == -945);	
	}
	
	@Test
	void distributeAcrossLoans() {
		
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Loan("Loan 1", 1.05, -200, 100));
		accounts.add(new Loan("Loan 2", 1.05, -200, 100));
		accounts.add(new Investment("Investment 1", 1.05, 0));
		MainProgram p = new MainProgram(accounts, 500, 1);
		
		List<Account> topOfStack = p.getHistory().peek();
		System.out.println(topOfStack);
		
		assertEquals(
				  "[Loan 1[interestRate=1.05, balance=$0.00, minimumPayment=$100.00], "
				+  "Loan 2[interestRate=1.05, balance=$0.00, minimumPayment=$100.00], " 
				+  "Investment 1[interestRate=1.05, balance=$105.00]]",
				topOfStack.toString() 
				);
		
	}
	
	

}
