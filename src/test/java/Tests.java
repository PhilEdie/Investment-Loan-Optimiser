import Controller.AccountController;
import Controller.Utilities;
import Model.Account;
import Model.AccountsModel;
import Model.Investment;
import Model.Loan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests {

	@Test
	void payOffLoanWithPayment() {
		Loan loan = new Loan("Model.Loan 1", 1.05, -500, 100);
		double leftoverIncome = loan.makePayment(1000);
		assertEquals(500.0, leftoverIncome);
		assertEquals(loan.getPaymentForPeriod(), 500);
	}
	
	@Test
	void payOffLoanWithMinimumPayment() {
		Loan loan = new Loan("Model.Loan 1", 1.05, -100, 200);
		double leftoverIncome = loan.makeMinimumPayment();
		assertEquals(100.0, leftoverIncome);
		assertEquals(loan.getPaymentForPeriod(), 100);
	}

	@Test 
	void sortingAccounts1() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Loan("Model.Loan 1", 1.25, -300, 50));
		toSort.add(new Loan("Model.Loan 2", 1.1, -1500, 100));
		toSort.add(new Investment("Model.Investment 1", 1.08, 5000));
		toSort.add(new Investment("Model.Investment 2", 1.04, 20000));
		
		List<Account> expected = new ArrayList<Account>(toSort);
		Collections.sort(toSort);
		assertEquals(expected, toSort);
	}
	
	@Test 
	void sortingAccounts2() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Loan("Model.Loan 1", 1.25, -300, 50));
		toSort.add(new Investment("Model.Investment 1", 1.25, 5000));
		
		List<Account> expected = new ArrayList<Account>(toSort);
		Collections.sort(toSort);
		assertEquals(expected, toSort);
	}
	
	@Test 
	void sortingAccounts3() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Loan("Model.Loan 1", 1.26, -300, 50));
		toSort.add(new Loan("Model.Loan 2", 1.25, -300, 50));
		toSort.add(new Investment("Model.Investment 1", 1.25, 5000));
		
		List<Account> expected = new ArrayList<Account>(toSort);
		Collections.sort(toSort);
		assertEquals(expected, toSort);
	}
	
	@Test
	void sortingEqualInterestRateAccounts() {
		List<Account> toSort = new ArrayList<Account>();
		toSort.add(new Investment("Model.Investment 2", 1.08, 5000));
		toSort.add(new Investment("Model.Investment 1", 1.1, 5000));
		toSort.add(new Loan("Model.Loan 1", 1.1, -10000, 100));
		toSort.add(new Loan("Model.Loan 2", 1.08, -10000, 100));
		Collections.sort(toSort);
		
		List<Account> expected = new ArrayList<Account>();
		
		expected.add(new Loan("Model.Loan 1", 1.1, -10000, 100));
		expected.add(new Investment("Model.Investment 1", 1.1, 5000));
		expected.add(new Loan("Model.Loan 2", 1.08, -10000, 100));
		expected.add(new Investment("Model.Investment 2", 1.08, 5000));
		
		assert toSort.toString().equals(expected.toString());
		
	}
	
	@Test
	void accumulatingInterest() {

		AccountController p = new AccountController();
		p.getAccountsModel().addStartingAccount(new Loan("Model.Loan 1", 1.05, -1000, 100));
		p.run(1, 100);
		assertEquals(-945, p.getAccountsModel().getHistory().peek().get(0).getBalance());

	}
	
	@Test
	void distributeAcrossLoans1() {

		AccountController p = new AccountController();
		AccountsModel m = p.getAccountsModel();
		m.addStartingAccount(new Loan("Model.Loan 1", 1.05, -200, 100));
		m.addStartingAccount(new Loan("Model.Loan 2", 1.05, -200, 100));
		m.addStartingAccount(new Investment("Model.Investment 1", 1.05, 0));
		p.run(1, 500);
		
		List<Account> topOfStack = m.getHistory().peek();
		System.out.println(topOfStack);
		
		assertEquals(
				  "[Model.Loan 1[interestRate=1.05, balance=$0.00, minimumPayment=$100.00], "
				+  "Model.Loan 2[interestRate=1.05, balance=$0.00, minimumPayment=$100.00], "
				+  "Model.Investment 1[interestRate=1.05, balance=$105.00]]",
				topOfStack.toString() 
				);
	}
	
	@Test
	void distributeAcrossLoans2() {

		AccountController p = new AccountController();
		AccountsModel m = p.getAccountsModel();
		m.addStartingAccount(new Loan("Model.Loan 1", 1.05, -200, 100));
		m.addStartingAccount(new Loan("Model.Loan 2", 1.05, -200, 100));
		m.addStartingAccount(new Investment("Model.Investment 1", 1.05, 0));
		p.run(10, 500);
	}
	
	@Test
	void defaultNameRegex() {
		assert Utilities.isDefaultName("Loan1");
		assert Utilities.isDefaultName("Investment1");
		assert Utilities.isDefaultName("Loan10");
		assert Utilities.isDefaultName("Investment10");
		assert !Utilities.isDefaultName("Model.Loan");
		assert !Utilities.isDefaultName("Model.Investment");
		assert !Utilities.isDefaultName("loan1");
		assert !Utilities.isDefaultName("investment1");
		assert !Utilities.isDefaultName("loan10");
		assert !Utilities.isDefaultName("investment10");
	}
}
