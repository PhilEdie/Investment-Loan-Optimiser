import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class AccountForm {
	
	

	private Class<? extends Account> type = Investment.class;

	private String name = "";
	private boolean validName = false;

	private String formattedBalance = "$0.00";
	private double balanceValue = 0;
	private boolean validBalance = false;

	private String formattedInterestRate = "0.00%";
	private double interestRateValue = 1;
	private boolean validInterestRate = false;

	private String formattedMinimumPayment = "$0.00";
	private double minimumPaymentValue = 0;
	private boolean validMinimumPayment = false;

	public Class<? extends Account> getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isValidName() {
		return validName;
	}

	public String getFormattedBalance() {
		return formattedBalance;
	}

	public double getBalanceValue() {
		return balanceValue;
	}

	public boolean isValidBalance() {
		return validBalance;
	}

	public String getFormattedInterestRate() {
		return formattedInterestRate;
	}

	public double getInterestRateValue() {
		return interestRateValue;
	}

	public boolean isValidInterestRate() {
		return validInterestRate;
	}

	public String getFormattedMinimumPayment() {
		return formattedMinimumPayment;
	}

	public double getMinimumPaymentValue() {
		return minimumPaymentValue;
	}

	public boolean isValidMinimumPayment() {
		return validMinimumPayment;
	}
	
	//==============================================
	//	SETTERS
	//==============================================



	public void setName(String name) {
		if(name.matches(".*[a-zA-Z].*")) {	
			this.name = name;
			this.validName = true;
		} else {
			this.name = "default";
			this.validName = false;
		}
	}
	
	public void setType(Class<? extends Account> type) {
		this.type = type;
	}


	public void setBalance(String balance) {
		if(type.equals(Investment.class) && validatePositiveNumber(balance)) {
			this.formattedBalance = convertToDollarFormat(balance);
			this.balanceValue = Double.parseDouble(balance);
			this.validBalance = true;
		} else if(type.equals(Loan.class) && validateNumber(balance)) {
			this.balanceValue = Math.abs(Double.parseDouble(balance));
			this.balanceValue *= -1;
			this.formattedBalance = convertToDollarFormat(""+balanceValue);
			this.validBalance = true;
		} else {
			this.formattedBalance = "$0.00";
			this.balanceValue = 0;
			this.validBalance = false;
		}
	}
		

	public void setInterestRate(String interestRate) {
		if(validatePositiveNumber(interestRate)) {
			this.formattedInterestRate = convertToPercentageFormat(interestRate);
			this.interestRateValue = 1 + (Double.parseDouble(interestRate)/100);
			this.validInterestRate = true;
		} else {
			this.formattedInterestRate = "0.00%";
			this.interestRateValue = 1;
			this.validInterestRate = false;
		}	
	}
	
	public void setMinimumPayment(String minimumPayment) {
		if(validatePositiveNumber(minimumPayment)) {
			this.formattedMinimumPayment = convertToDollarFormat(minimumPayment);
			this.minimumPaymentValue = Double.parseDouble(minimumPayment);
			this.validMinimumPayment = true;
		} else {
			this.formattedMinimumPayment = "$0.00";
			this.minimumPaymentValue = 0;
			this.validMinimumPayment = false;
		}
		
	}

	public boolean validateEntries() {
		if(type.equals(Investment.class)) {
			return validName && validBalance && validInterestRate;
		}
		return validName && validBalance && validInterestRate && validMinimumPayment;
	}

	public boolean validateNumber(String text) {
		try {
			double i = Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public boolean validatePositiveNumber(String text) {
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
}
