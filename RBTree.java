
public class RBTree extends BSTree {

	public RBTree() {
		
	}
	public RBTree(BTNode top) {
		super(top);
	}
	
	@Override
	public BTNode insert(int x) {
		BTNode node = super.insert(x);
		rebalanceFrom(node);
		return node;
	}
	
	private void rebalanceFrom(BTNode node) {
		
		if(node == top) {
			node.color = RBTNodeColor.BLACK;
			return;
		}
		
		BTNode parent = node.parent;
		if( BTNode.isBlack(parent) )
			return;
		
		//если "папа" не черный(а красный), то значит есть черный "дедушка"
		BTNode gparent = parent.parent;
		BTNode uncle = (gparent.left == parent)? 
							gparent.right :
							gparent.left;
		
		//case 1 (дядя красный):
		if(BTNode.isRed(uncle) ) {
			parent.color = RBTNodeColor.BLACK;
			uncle.color = RBTNodeColor.BLACK;
			gparent.color = RBTNodeColor.RED;
			rebalanceFrom(gparent);
			return;
		}
		//case 2/3 (дядя черный)
		if(gparent.left == parent) {
			if(parent.right == node) {//case 2
				parent =  smallLeftRotation(parent);
				node = parent.left;
			}
			//case 3:
			smallRightRotation(gparent);
		}
		else {
			if(parent.left == node) {//case 2
				parent = smallRightRotation(parent);
				node = parent.right;
			}
			//case 3:
			smallLeftRotation(gparent);
		}
		gparent.color = RBTNodeColor.RED;
		parent.color = RBTNodeColor.BLACK;
		
	}
	

	
}
