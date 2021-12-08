import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	private List<Asset> assets;
	
	public Node(Node parent, List<Asset> assets) {
		this.parent = parent;
		this.assets = assets;
	}

	public Node getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return "Node [assets=" + assets + "]";
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
	

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}	
}
