import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class AccountForm {
	
	

	private Class<? extends Account> type = Investment.class;

	private String name = "Investment1";
	private boolean validName = true;

	private String formattedBalance = "$0.00";
	private double balanceValue = 0;
	private boolean validBalance = false;

	private String formattedInterestRate = "0.00%";
	private double interestRateValue = 1;
	private boolean validInterestRate = false;

	

	private String formattedMinimumPayment = "$0.00";
	private double minimumPaymentValue = 0;
	private boolean validMinimumPayment = false;
	
	private String formattedAvailableFunds = "$0.00";
	private double availableFundsValue = 0;
	private boolean validAvailableFunds = false;
	
	
	private int totalPeriods = 1;
	private boolean validTotalPeriods = false;
	

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
	
	public String getFormattedIncome() {
		return formattedAvailableFunds;
	}

	public double getInterestRateValue() {
		return interestRateValue;
	}

	public boolean isValidInterestRate() {
		return validInterestRate;
	}
	
	public double getIncomeValue() {
		return availableFundsValue;
	}

	public int getTotalPeriods() {
		return totalPeriods;
	}
	
	public boolean isValidIncome() {
		return validAvailableFunds;
	}
	

	public boolean isValidTotalPeriods() {
		return validTotalPeriods;
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
			this.name = "";
			this.validName = false;
		}
	}
	
	public void setType(Class<? extends Account> type) {
		this.type = type;
	}


	public void setBalance(String balance) {
		if(type.equals(Investment.class) && validatePositiveNumber(balance)) {
			this.formattedBalance = Utilities.convertToDollarFormat(balance);
			this.balanceValue = Double.parseDouble(balance);
			this.validBalance = true;
		} else if(type.equals(Loan.class) && validateNumber(balance)) {
			this.balanceValue = Math.abs(Double.parseDouble(balance));
			this.balanceValue *= -1;
			this.formattedBalance = Utilities.convertToDollarFormat(""+balanceValue);
			this.validBalance = true;
		} else {
			this.formattedBalance = "$0.00";
			this.balanceValue = 0;
			this.validBalance = false;
		}
	}
		

	public void setInterestRate(String interestRate) {
		if(validatePositiveNumber(interestRate)) {
			this.formattedInterestRate = Utilities.convertToPercentageFormat(interestRate);
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
			this.formattedMinimumPayment = Utilities.convertToDollarFormat(minimumPayment);
			this.minimumPaymentValue = Double.parseDouble(minimumPayment);
			this.validMinimumPayment = true;
		} else {
			this.formattedMinimumPayment = "$0.00";
			this.minimumPaymentValue = 0;
			this.validMinimumPayment = false;
		}
	}
	
	public void setIncome(String income) {
		if(validatePositiveNumber(income)) {
			this.formattedAvailableFunds = Utilities.convertToDollarFormat(income);
			this.availableFundsValue = Double.parseDouble(income);
			this.validAvailableFunds = true;
		} else {
			this.formattedAvailableFunds = "$0.00";
			this.availableFundsValue = 0;
			this.validAvailableFunds = false;
		}
	}
	

	public void setTotalPeriods(String periods) {
		System.out.println("Periods: " + periods);
		if(validatePositiveNumber(periods) && Integer.parseInt(periods) != 0) {
			System.out.println("Reached1");
			this.totalPeriods = Integer.parseInt(periods);
			this.validTotalPeriods = true;
		} else {
			System.out.println("Reached2");
			this.totalPeriods = 1;
			this.validTotalPeriods = false;
		}
	}
	

	public boolean validateEntriesBeforeAdd() {
		if(type.equals(Investment.class)) {
			return validName && validBalance && validInterestRate;
		}
		return validName && validBalance && validInterestRate && validMinimumPayment;
	}
	
	public boolean validateEntriesBeforeConfirm() {
		return validAvailableFunds && validTotalPeriods;
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
	
	
	
	
}
