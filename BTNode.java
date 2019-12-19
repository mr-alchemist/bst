
public class BTNode {
	public BTNode left;
	public BTNode right;
	public BTNode parent;
	public int value;
	
	int height;
	
	RBTNodeColor color;
	
	public BTNode() {
		this(null);
	}
	
	public BTNode(BTNode parent) {
		this.parent = parent;
		left = null;
		right = null;
		height = 0;
		color = RBTNodeColor.RED;
	}
	
	static int getHeight(BTNode node) {
		if(node == null)
			return -1;
		return node.height;
	}
	
	public void updateHeight() {
		height = calcHeight();
	}
	
	public int calcHeight() {
		return Math.max(
					getHeight(left), 
					getHeight(right)
				)
				+ 1;
		
	}
	int balance() {
		return getHeight(left) - getHeight(right);
	}
	
	static boolean isBlack(BTNode node) {
		if(node == null || node.color == RBTNodeColor.BLACK) 
			return true;
		return false;
	}
	
	static boolean isRed(BTNode node) {
		return !isBlack(node);
	}
	
	
}

enum RBTNodeColor{
	RED,
	BLACK
}
