package Model;

/**
 * A Model.Loan object includes a minimum payment field.
 * Model.Loan objects should always have a balance less than or equal to 0.
 *
 * @author Phil Edie
 */
public class Loan extends Account {

    private final double minimumPayment;
    private boolean paidOff = false;

    public Loan(String accountName, double interestRate, double balance, double minimumPayment) {
        super(accountName, interestRate, balance, 1);
        if (balance > 0) {
            throw new IllegalArgumentException("Error, a balance on a Model.Loan object should be negative.");
        }
        this.minimumPayment = minimumPayment;
    }

    public Loan(Loan toCopy) {
        super(toCopy.getAccountName(), toCopy.getInterestRate(), toCopy.getBalance(), 1);
        this.minimumPayment = toCopy.getMinimumPayment();
        this.paidOff = toCopy.isPaidOff();
    }

    /**
     * Adds the payment to the account balance.
     * If balance + payment is greater than zero, return the extra cash
     * as "change".
     *
     * @return Any leftover money which would make the overall balance greater
     * than zero.
     */
    @Override
    public double makePayment(double payment) {
        //We shouldn't be making payments when the loan is already paid off.
        if (isPaidOff()) {
            throw new IllegalStateException("Error. We cannot make a payment on an already paid off loan.");
        }

        double originalBalance = getBalance();

        if (getBalance() + payment >= 0) {
            setPaymentForPeriod(getPaymentForPeriod() + Math.abs(originalBalance));
            setBalance(0);
            this.paidOff = true;
            return originalBalance + payment;
        } else {
            setPaymentForPeriod(getPaymentForPeriod() + payment);
            setBalance(getBalance() + payment);
            return 0.0;
        }
    }

    @Override
    public void setBalance(double balance) {
        if (balance > 0) {
            throw new IllegalArgumentException("Error. The balance on a Loan object should not be positive.");
        }
        super.setBalance(balance);
    }

    public double makeMinimumPayment() {
        return makePayment(this.minimumPayment);
    }

    public double getMinimumPayment() {
        return this.minimumPayment;
    }

    public boolean isPaidOff() {
        return this.paidOff;
    }

    @Override
    public String toString() {
        return getAccountName() + "[interestRate=" + getInterestRate() + ", balance=$" + String.format("%.2f", getBalance())
                + ", minimumPayment=$" + String.format("%.2f", getMinimumPayment()) + "]";
    }

}