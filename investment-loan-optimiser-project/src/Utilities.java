import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
	
	public static boolean isDefaultName(String name) {
		Pattern toMatch = Pattern.compile("[Loan\\|Investment]+\\d");
		Matcher matcher = toMatch.matcher(name);
		return matcher.find();
	}
	
	public static boolean containsAllLoans(List<Account> accounts) {
		for (Account account : accounts) {
			if (!(account instanceof Loan)) {
				return false;
			}
		}
		return true;
	}

	public static boolean allLoansPaidOff(List<Account> accounts) {
		for (Account account : accounts) {
			if (account instanceof Loan && !((Loan) account).isPaidOff()) {
				return false;
			}
		}
		return true;
	}
	
	public static double getNetWorth(List<Account> accounts) {
		double networth = 0;
		for(Account account : accounts) {
			networth += account.getBalance();
		}
		return networth;
	}
	
	public static String getNetWorthAsString(List<Account> accounts) {
		double networth = 0;
		for(Account account : accounts) {
			networth += account.getBalance();
		}
		return convertToDollarFormat(networth);
	}
	
	public static double getTotalInterest(List<Account> accounts) {
		double totalInterest = 0;
		for(Account account : accounts) {
			totalInterest += account.getInterestForPeriod();
		}
		return totalInterest;
	}
	
	public static String getTotalInterestAsString(List<Account> accounts) {
		double totalInterest = 0;
		for(Account account : accounts) {
			totalInterest += account.getInterestForPeriod();
		}
		return convertToDollarFormat(totalInterest);
	}
	
	public static double getChangeInNetWorth(List<Account> before, List<Account> after) {
		double beforeNetWorth = getNetWorth(before);
		double afterNetWorth = getNetWorth(after);
		return afterNetWorth - beforeNetWorth;
	}
	
	public static String getChangeInNetWorthAsString(List<Account> before, List<Account> after) {
		double beforeNetWorth = getNetWorth(before);
		double afterNetWorth = getNetWorth(after);
		return convertToDollarFormat(afterNetWorth - beforeNetWorth);
	}
	
	public static String getPaidOffLoanNames(List<Account> accounts) {
		List<String> names = new ArrayList<String>();
		for(Account account : accounts) {
			if(account instanceof Loan && ((Loan) account).isPaidOff()) {
				names.add(account.getAccountName());
			}
		}
		if(names.isEmpty()) {
			return "N/A";
		}
		return String.join(", ", names);
	}
	
	public static String convertToDollarFormat(String number) {
		Locale locale = Locale.US;
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
		return dollarFormat.format(Double.parseDouble(number));
	}
	
	public static String convertToDollarFormat(double number) {
		Locale locale = Locale.US;
		NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);
		return dollarFormat.format(number);
	}
	
	public static String convertToPercentageFormat(String interestRate) {
		return String.format("%.2f", Double.parseDouble(interestRate)) + "%";
	}
	
	public static String convertToPercentageFormat(double interestRate) {
		return String.format("%.2f", interestRate) + "%";
	}
	
	public static boolean validateNumber(String text) {
		try {
			double i = Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean validatePositiveNumber(String text) {
		try {
			double i = Double.parseDouble(text);
			if(i < 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
