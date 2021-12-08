import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Program {

	public Program() {

		List<Asset> assets = new ArrayList<Asset>();
		
		
		assets.add(new Investment(1.08, 5000));
		assets.add(new Investment(1.04, 20000));
		assets.add(new Loan(1.16, -1000, 100));
		
		double income = 10000;
		int totalMonths = 12;
		
		Node root = new Node(assets);
		
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
	
	public Node constructTree(Node currentNode, double income, int currentMonth, int totalMonths) {
		
		//We shouldn't add more children since we are already finished. 
		if(currentMonth >= totalMonths) {
			//System.out.println("Completed." + currentNode.toString());
			return currentNode;
		}
		
		//When constructing the tree, we are adding children to currentNode. The 
		//current node shouldn't already have children. 
		
		if(currentNode.getChildren().size() > 0) {
			throw new IllegalArgumentException("Error. Root already has children.");
		}
		
		
		for(Asset toInvest : currentNode.getAssets()) {
			
			//Create a copy of the currentNode's assets.
			List<Asset> futureAssets = new ArrayList<Asset>();
			
			//Apply only interest to all other assets.
			for(Asset asset : currentNode.getAssets()) {
				if(asset != toInvest) {
					futureAssets.add(asset.afterPaymentAndInterest(0));
				}
			}
			
			//Apply interest and payment to toInvest, then add it to futureAssets.
			futureAssets.add(toInvest.afterPaymentAndInterest(income));
			
			//Create a childNode with the interest applied assets. 
			Node childNode = new Node(futureAssets);
			currentNode.addChild(childNode);
			
			//Move to the next stage in the tree. 
			constructTree(childNode, income, (currentMonth + 1), totalMonths);
		}
		
		//You should never reach this point. 
		return null;
		
	}
	
	/**
	 * Calculate the net worth for each leaf node (Summing up the leaf's asset balances).
	 * Check to see if the current leaf nodes net worth is higher than one seen previously.
	 * 
	 * Return the leaf node with the highest net worth. 
	 * @param leafs
	 * @return
	 */
	public Node findHighestNetWorthNode(Set<Node> leafs) {
		
		if(leafs.size() == 0 || leafs == null) {
			throw new IllegalArgumentException("Error. leafs cannot be empty or null.");
		}
		
		double currentHighestNetWorth = Double.NEGATIVE_INFINITY;
		Node currentBest = null;
		
		for(Node leaf : leafs) {
			double netWorth = 0;
			//Sum up the balances of the leaf's assets. 
			for(Asset asset : leaf.getAssets()) {
				netWorth += asset.balance;
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
		
		if(end.getParent() == null) {
			throw new IllegalArgumentException("Error. end node has no parent.");
		}
		
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