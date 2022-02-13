package Controller;

import Model.Account;
import Model.Loan;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A selection of static methods useful across many classes.
 * 
 * @author Phil Edie
 *
 */
public class Utilities {

	/**
	 * Checks a string to ensure if matches the default name regex.
	 * eg: "Loan1", "Investment10".
	 *
	 * @param name		Name to check.
	 * @return			True if all accounts are loans, false if not.
	 */
	public static boolean isDefaultName(String name) {
		Pattern toMatch = Pattern.compile("[Loan\\|Investment]+\\d");
		Matcher matcher = toMatch.matcher(name);
		return matcher.find();
	}

	/**
	 * Checks a list of accounts to ensure all objects are Loans.
	 *
	 * @param accounts	Accounts to check.
	 * @return			True if all accounts are loans, false if not.
	 */
	public static boolean containsAllLoans(List<Account> accounts) {
		for (Account account : accounts) {
			if (!(account instanceof Loan)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks all accounts to ensure all loans are paid off.
	 *
	 * @param accounts	Accounts to check.
	 * @return			True if all loans are paid off, false if at least one loan isn't paid off.
	 */
	public static boolean allLoansPaidOff(List<Account> accounts) {
		for (Account account : accounts) {
			if (account instanceof Loan && !((Loan) account).isPaidOff()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Calculates and returns the sum of net worth within a list of accounts.
	 *
	 * @param accounts	Accounts to check.
	 * @return			The sum of net worth within a list of accounts as in Double format.
	 */
	public static double getNetWorth(List<Account> accounts) {
		double networth = 0;
		for (Account account : accounts) {
			networth += account.getBalance();
		}
		return networth;
	}

	/**
	 * Calculates and returns the sum of net worth within a list of accounts as a String..
	 *
	 * @param accounts	Accounts to check.
	 * @return			The sum of net worth within a list of accounts as a String
	 */
	public static String getNetWorthAsString(List<Account> accounts) {
		double networth = 0;
		for (Account account : accounts) {
			networth += account.getBalance();
		}
		return convertToDollarFormat(networth);
	}

	/**
	 * Calculates and returns the sum of interest accumulated within a list of accounts.
	 *
	 * @param accounts	Accounts to check.
	 * @return			The sum of interest accumulated within a list of accounts in Double format. 
	 */
	public static double getTotalInterest(List<Account> accounts) {
		double totalInterest = 0;
		for (Account account : accounts) {
			totalInterest += account.getInterestForPeriod();
		}
		return totalInterest;
	}

	/**
	 * Calculates and returns the sum of interest accumulated within a list of accounts in String format.
	 *
	 * @param accounts	Accounts to check.
	 * @return			The sum of interest accumulated within a list of accounts in String format. 
	 */
	public static String getTotalInterestAsString(List<Account> accounts) {
		double totalInterest = 0;
		for (Account account : accounts) {
			totalInterest += account.getInterestForPeriod();
		}
		return convertToDollarFormat(totalInterest);
	}

	/**
	 * 	Returns the difference in net worth between two lists of accounts.
	 * 	
	 * @param before	The previous list of accounts.
	 * @param after		The current list of accounts.
	 * @return			Returns the difference in net worth in Double format. 
	 */
	public static double getChangeInNetWorth(List<Account> before, List<Account> after) {
		double beforeNetWorth = getNetWorth(before);
		double afterNetWorth = getNetWorth(after);
		return afterNetWorth - beforeNetWorth;
	}

	/**
	 * 	Calculates the difference in net worth between two lists of accounts.
	 * 	Returns the difference in String format. 
	 * @param before	The previous list of accounts.
	 * @param after		The current list of accounts.
	 * @return			Returns the difference in net worth in String format. 
	 */
	public static String getChangeInNetWorthAsString(List<Account> before, List<Account> after) {
		double beforeNetWorth = getNetWorth(before);
		double afterNetWorth = getNetWorth(after);
		return convertToDollarFormat(afterNetWorth - beforeNetWorth);
	}

	/**
	 * Searches a list of accounts for paid off loans. Returns a comma separated
	 * string with all paid off loan names. eg: [Loan1, Loan2, Investment1] ->
	 * "Loan1, Loan2"
	 * 
	 * @param accounts	The accounts to search.		
	 * @return 			A comma separated string with all paid off loan names.
	 */
	public static String getPaidOffLoanNames(List<Account> accounts) {
		List<String> names = new ArrayList<String>();
		for (Account account : accounts) {
			if (account instanceof Loan && ((Loan) account).isPaidOff()) {
				names.add(account.getAccountName());
			}
		}
		if (names.isEmpty()) {
			return "N/A";
		}
		return String.join(", ", names);
	}

	/**
	 * Attempts to convert a string to a double, then converts the string back into
	 * dollar format. ("1.00") -> (1.0) -> ("$1.00")
	 * 
	 * @param number	The String to convert.
	 * @return 			The converted string.
	 */
	public static String convertToDollarFormat(String number) {
		Locale locale = Locale.US;
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
		return dollarFormat.format(Double.parseDouble(number));
	}

	/**
	 * Converts a double (1.0) to a string in dollar format ("$1.00")
	 * 
	 * @param 		number The double to convert.
	 * @return 		The converted string.
	 */
	public static String convertToDollarFormat(double number) {
		Locale locale = Locale.US;
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
		return dollarFormat.format(number);
	}

	/**
	 * Attempts to convert a string to a double, then converts the string back into
	 * a percentage format. ("1.00") -> (1.0) -> ("1.00%")
	 * 
	 * @param interestRate
	 * @return The converted string.
	 */
	public static String convertToPercentageFormat(String interestRate) {
		return String.format("%.2f", Double.parseDouble(interestRate)) + "%";
	}

	/**
	 * Converts a double from a double (1.0) to a string in percentage format
	 * ("1.00%")
	 * 
	 * @param interestRate The double to convert.
	 * @return The converted string.
	 */
	public static String convertToPercentageFormat(double interestRate) {
		return String.format("%.2f", interestRate) + "%";
	}

	/**
	 * Checks to see if the provided String contains a double.
	 * 
	 * @param text The string to check.
	 * @return True if the provided String contains a double.
	 */
	public static boolean validateNumber(String text) {
		try {
			double testNumber = Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks to see if the provided String contains a positive double.
	 * 
	 * @param text The string to check.
	 * @return True if the provided String contains a positive double.
	 */
	public static boolean validatePositiveNumber(String text) {
		try {
			double testNumber = Double.parseDouble(text);
			if (testNumber < 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
