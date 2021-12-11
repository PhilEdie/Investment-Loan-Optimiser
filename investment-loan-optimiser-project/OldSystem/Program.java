import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Program {

	public Program() {

		List<Account> accounts = new ArrayList<Account>();
		
		
		accounts.add(new Investment(1.08, 5000));
		accounts.add(new Investment(1.04, 20000));
		//accounts.add(new Loan(1.16, -1000, 100));
		
		double income = 10000;
		int totalMonths = 12;
		
		Node root = new Node(accounts);
		
		constructTree(root, income, 1, totalMonths);
		
		Node highestNetWorth = findHighestNetWorthNode(root.getAllLeafNodes());
		System.out.println("Highest Net Worth: ");
		System.out.println(highestNetWorth);
		
		Stack<Node> investmentPath = findInvestmentPath(highestNetWorth);
		int month = 0;
		while(!investmentPath.isEmpty()) {
			System.out.println("Month:" + ++month);
			System.out.println(investmentPath.pop());
		}
		
	}
	
	public Node constructTree(Node currentNode, double incomeForPeriod, int currentMonth, int totalMonths) {
		
		//We shouldn't add more children since we are already finished. 
		if(currentMonth >= totalMonths) {
			return currentNode;
		}
		
		//When constructing the tree, we are adding children to currentNode. The 
		//current node shouldn't already have children. 
		
		assert currentNode.getChildren().size() == 0;

		
		for(Account toInvest : currentNode.getAccounts()) {
			
			double remainingIncome = incomeForPeriod;
			
			//Create a copy of the currentNode's accounts.
			List<Account> futureAccounts = new ArrayList<Account>();
			
			//Make a copy of each account object so changes won't occur in current Node. 
			for(Account account : currentNode.getAccounts()) {
				//Skip toInvest, we will handle this differently later.
				if(account == toInvest) {
					continue;
				}
				if(account instanceof Loan) {
					futureAccounts.add(new Loan((Loan) account));
				} else if(account instanceof Investment) {
					futureAccounts.add(new Investment((Investment) account));
				}
			}

			//Pay minimums into each loan:
			for(Account account : futureAccounts) {
				if(account instanceof Loan){
					Loan loan = (Loan) account;
					double change = loan.makeMinimumPayment();
					remainingIncome = remainingIncome - loan.getMinimumPayment() + change;
				}
			}
			
			//There should still be some money left over to invest after all minimum payments.
			assert remainingIncome > 0;
			
			//Apply only interest to all other accounts.
			for(Account account : futureAccounts) {
				account.applyInterest();
			}
			
			//Apply interest and payment to toInvest, then add it to futureaccounts.
			if(toInvest instanceof Loan) {
				Loan invested = new Loan((Loan) toInvest);
				remainingIncome = invested.makePayment(remainingIncome);
				invested.applyInterest();
				futureAccounts.add(invested);
				
			} else if(toInvest instanceof Investment) {
				Investment invested = new Investment((Investment) toInvest);
				remainingIncome = invested.makePayment(remainingIncome);
				futureAccounts.add(invested);
			}
			
			//Create a childNode with the interest applied accounts. 
			Node childNode = new Node(futureAccounts);
			currentNode.addChild(childNode);
			
			//Move to the next stage in the tree. 
			assert remainingIncome == 0;
			constructTree(childNode, incomeForPeriod, (currentMonth + 1), totalMonths);
		}
		
		//You should never reach this point. 
		return null;
		
	}
	
	/**
	 * Calculate the net worth for each leaf node (Summing up the leaf's account balances).
	 * Check to see if the current leaf nodes net worth is higher than one seen previously.
	 * 
	 * Return the leaf node with the highest net worth. 
	 * @param leafs
	 * @return
	 */
	public Node findHighestNetWorthNode(Set<Node> leafs) {
		
		assert leafs.size() != 0 && leafs != null;
		
		double currentHighestNetWorth = Double.NEGATIVE_INFINITY;
		Node currentBest = null;
		
		for(Node leaf : leafs) {
			double netWorth = 0;
			//Sum up the balances of the leaf's accounts. 
			for(Account account : leaf.getAccounts()) {
				netWorth += account.getBalance();
			}
			
			//Check to see if the sum is higher than the current highest net worth.
			if(netWorth > currentHighestNetWorth) {
				
				//Found a better leaf node. Update the variables. 
				currentHighestNetWorth = netWorth;
				currentBest = leaf;
			}
		}
		return currentBest;
	}
	
	public Stack<Node> findInvestmentPath(Node end){
		
		Stack<Node> path = new Stack<Node>();
		
		Node current = end;
		
		//Ensure end node has parents. 
		assert end.getParent() != null;

		path.push(current);
		
		while(current.getParent() != null) {
			current = current.getParent();
			path.push(current);	
		}	
		
		return path;
	}
	
	
	public static void main(String[] args) {
		new Program();
	}
}