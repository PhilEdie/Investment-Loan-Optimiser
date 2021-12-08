import java.util.ArrayList;
import java.util.List;

public class Program {

	public Program() {

		List<Asset> assets = new ArrayList<Asset>();
		
		
		assets.add(new Investment(1.08, 5000));
		assets.add(new Investment(1.04, 20000));
		
		double income = 10000;
		int totalMonths = 12;
		
		Node root = new Node(null, assets);
		
		constructTree(root, income, 1, totalMonths);

	}
	
	public Node constructTree(Node currentNode, double income, int currentMonth, int totalMonths) {
		
		//We shouldn't add more children since we are already finished. 
		if(currentMonth > totalMonths) {
			System.out.println("Completed." + currentNode.toString());
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
			Node childNode = new Node(currentNode, futureAssets);
			
			//Move to the next stage in the tree. 
			constructTree(childNode, income, ++currentMonth, totalMonths);
		}
		
		//You should never reach this point. 
		return null;
		
	}
	
	

	public static void main(String[] args) {
		new Program();
	}
}