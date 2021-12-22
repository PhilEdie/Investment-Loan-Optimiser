import java.util.List;
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

}
