package Model;

public abstract class Account implements Comparable<Account> {

    private final String accountName;
    private double balance;
    private final double interestRate;
    private double interestForPeriod = 0;
    private double paymentForPeriod = 0;
    private final int priority;    // Used in the compareTo method, when two accounts have the same interest rate.


    public Account(String accountName, double interestRate, double balance, int priority) {
        if (accountName.isBlank()) {
            throw new IllegalArgumentException("Error. accountName should not be empty.");
        }

        if (interestRate < 1) {
            throw new IllegalArgumentException("Error, interestRate should be greater or equal to 1.");
        }

        this.accountName = accountName;
        this.interestRate = interestRate;
        this.balance = balance;
        this.priority = priority;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getInterestForPeriod() {
        return this.interestForPeriod;
    }

    public double getPaymentForPeriod() {
        return this.paymentForPeriod;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPaymentForPeriod(double paymentForPeriod) {
        if (paymentForPeriod < 0) {
            throw new IllegalArgumentException("Error. paymentForPeriod should not be less than 0.");
        }
        this.paymentForPeriod = paymentForPeriod;
    }

    public abstract double makePayment(double payment);

    public void applyInterest() {
        double beforeBalance = balance;
        setBalance(getBalance() * getInterestRate());
        double afterBalance = balance;
        this.interestForPeriod = afterBalance - beforeBalance;
    }

    public int compareTo(Account other) {

        //First prioritise higher interest rate accounts.
        if (this.getInterestRate() > other.getInterestRate()) {
            return -1;
        } else if (this.getInterestRate() < other.getInterestRate()) {
            return 1;
        } else {

            //The interest rates are equal. Choose the higher priority account
            if (this.getPriority() < other.getPriority()) {
                return -1;
            } else if (this.getPriority() > other.getPriority()) {
                return 1;
            }
        }

        //Same class, same interest rate. Return in alphabetical order.
        if (this.getAccountName().compareTo(other.getAccountName()) < 0) {
            return -1;
        } else if (this.getAccountName().compareTo(other.getAccountName()) > 0) {
            return 1;
        }

        //Both accounts are identical.
        return 0;
    }
}