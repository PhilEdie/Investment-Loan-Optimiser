import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	private List<Asset> assets;
	
	public Node(List<Asset> assets) {
		this.assets = assets;
	}
	
	

	public Node getParent() {
		return parent;
	}
	
	public List<Node> getChildren() {
		return children;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	public void addChild(Node childNode) {
		this.children.add(childNode);
		childNode.setParent(this);
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
	
	public Set<Node> getAllLeafNodes() {
	    Set<Node> leafNodes = new HashSet<Node>();
	    if (this.children.isEmpty()) {
	        leafNodes.add(this);
	    } else {
	        for (Node child : this.children) {
	            leafNodes.addAll(child.getAllLeafNodes());
	        }
	    }
	    return leafNodes;
	}
	
	@Override
	public String toString() {
		return "Node [assets=" + assets + "]";
	}
}
