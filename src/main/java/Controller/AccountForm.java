package Controller;

import Model.Account;
import Model.Investment;
import Model.Loan;

public class AccountForm {

    private Class<? extends Account> type = Investment.class;

    private String name = "Investment1";
    private boolean validName = true;

    private String formattedBalance = "$0.00";
    private double balanceValue = 0;
    private boolean validBalance = false;

    private String formattedInterestRate = "0.00%";

    /*
     * An interest rate value of 1 is equivalent to an actual interest rate of 0,
     * since we apply interest by multiplying the balance by the interest rate.
     * ie. $100 * 1.25 = $125	(25% interest)
     * ie. $100 * 1.00 = $100	(0% interest)
     */
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


    public void setName(String name) {
        if (name.matches(".*[a-zA-Z].*")) {
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


    /**
     * Sets the account balance based on the contents of the balanceEntry text field.
     * The balance should always be positive if it is an investment.
     * If the type of account is a loan, the balance will always be considered negative.
     * If the input isn't valid, default the balance value to zero and set the validBalance flag to false.
     *
     * @param balance The contents of the balanceEntry text field.
     */
    public void setBalance(String balance) {
        if (type.equals(Investment.class) && Utilities.validatePositiveNumber(balance, 100000000000.0)) {
            this.formattedBalance = Utilities.convertToDollarFormat(balance);
            this.balanceValue = Double.parseDouble(balance);
            this.validBalance = true;
        } else if (type.equals(Loan.class) && Utilities.validateNumber(balance, 100000000000.0)) {
            this.balanceValue = Math.abs(Double.parseDouble(balance));
            this.balanceValue *= -1;
            this.formattedBalance = Utilities.convertToDollarFormat("" + balanceValue);
            this.validBalance = true;
        } else {
            this.formattedBalance = "$0.00";
            this.balanceValue = 0;
            this.validBalance = false;
        }
    }


    /**
     * Sets the interest rate based on the contents of the interestRateEntry text field.
     * The interest rate should always be positive.
     * If the provided string cannot be converted to a double, default the interest rate to 0 and set the
     * validInterestRate flag to false.
     *
     * @param interestRate The contents of the interestRateEntry text field.
     */
    public void setInterestRate(String interestRate) {
        if (Utilities.validatePositiveNumber(interestRate, 100)) {
            this.formattedInterestRate = Utilities.convertToPercentageFormat(interestRate);
            this.interestRateValue = 1 + (Double.parseDouble(interestRate) / 100);
            this.validInterestRate = true;
        } else {
            this.formattedInterestRate = "0.00%";
            this.interestRateValue = 1;
            this.validInterestRate = false;
        }
    }

    /**
     * Sets the minimum payment based on the contents of the minimumPaymentEntry text field.
     * The minimum payment should always be positive.
     * If the provided string cannot be converted to a double, default the minimum payment to 0 and set the
     * validMinimumPayment flag to false.
     *
     * @param minimumPayment the contents of the minimumPaymentEntry text field.
     */
    public void setMinimumPayment(String minimumPayment) {
        if (Utilities.validatePositiveNumber(minimumPayment, 1000000.0)) {
            this.formattedMinimumPayment = Utilities.convertToDollarFormat(minimumPayment);
            this.minimumPaymentValue = Double.parseDouble(minimumPayment);
            this.validMinimumPayment = true;
        } else {
            this.formattedMinimumPayment = "$0.00";
            this.minimumPaymentValue = 0;
            this.validMinimumPayment = false;
        }
    }

    /**
     * Sets the income based on the contents of the incomeEntry text field.
     * The income should always be positive.
     * If the provided string cannot be converted to a double, default the income to 0 and set the
     * validIncome flag to false.
     *
     * @param income the contents of the incomeEntry text field.
     */
    public void setIncome(String income) {
        if (Utilities.validatePositiveNumber(income, 100000000000.0)) {
            this.formattedAvailableFunds = Utilities.convertToDollarFormat(income);
            this.availableFundsValue = Double.parseDouble(income);
            this.validAvailableFunds = true;
        } else {
            this.formattedAvailableFunds = "$0.00";
            this.availableFundsValue = 0;
            this.validAvailableFunds = false;
        }
    }


    /**
     * Sets the total periods based on the contents of the totalPeriodsEntry text field.
     * The total periods should should always be greater than 0.
     * If the provided string cannot be converted to an integer, default the total periods to 1 and set the
     * validTotalPeriods flag to false.
     *
     * @param periods the contents of the totalPeriodsEntry text field.
     */
    public void setTotalPeriods(String periods) {
        try {
            if (Utilities.validatePositiveNumber(periods, 1500)) {
                this.totalPeriods = Integer.parseInt(periods);
                this.validTotalPeriods = true;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            this.totalPeriods = 1;
            this.validTotalPeriods = false;
        }
    }


    /**
     * Check all fields to see if they are valid.
     * This is useful when we are creating instances of the Model.Account class from the information
     * stored in the Controller.AccountForm object.
     *
     * @return True if all relevant fields are valid. False if any relevant field is invalid.
     */
    public boolean validateEntriesBeforeAdd() {
        if (type.equals(Investment.class)) {
            return validName && validBalance && validInterestRate;
        }
        return validName && validBalance && validInterestRate && validMinimumPayment;
    }

    /**
     * Check to ensure valid available funds and total periods values are provided.
     *
     * @return True if both are valid, false if either are invalid.
     */
    public boolean validateEntriesBeforeConfirm() {
        return validAvailableFunds && validTotalPeriods;
    }


}
