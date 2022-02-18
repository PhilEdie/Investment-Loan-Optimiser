package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AccountsModel {

    private final List<Account> startingAccounts = new ArrayList<Account>();
    /*
     * The history stack stores the state of each Model.Account object in each payment period.
     */
    private Stack<List<Account>> history = new Stack<List<Account>>();

    public AccountsModel() {
    }

    public List<Account> getStartingAccounts() {
        return startingAccounts;
    }

    public Stack<List<Account>> getHistory() {
        return history;
    }

    public void addStartingAccount(Account toAdd) {
        this.startingAccounts.add(toAdd);
    }

    public void addToHistory(List<Account> toAdd) {
        this.history.add(toAdd);
    }

    public void clearHistory() {
        this.history = new Stack<List<Account>>();
    }

    public void removeStartingAccount(Account toRemove) {
        this.startingAccounts.remove(toRemove);
    }

    public void printHistory() {
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        System.out.printf(" %14s | %20s | %12s | %12s | %14s | %20s | %20s |", "Payment Period", "Model.Account Name",
                "Model.Account Type", "Balance", "Interest Rate", "Payments Made", "Interest For Period");
        System.out.println();
        System.out.println(
                "------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < this.history.size(); i++) {
            for (Account account : this.history.get(i)) {
                System.out.format(" %14s | %20s | %12s | %12s | %14s | %20s | %20s |", i, account.getAccountName(),
                        account.getClass().getSimpleName(), String.format("%.2f", account.getBalance()),
                        account.getInterestRate(), String.format("%.2f", account.getPaymentForPeriod()),
                        String.format("%.2f", account.getInterestForPeriod()));
                System.out.println();
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------");
        }
    }
}
