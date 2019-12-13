
public class AVLTree extends BSTree {
	
	@Override
	public BTNode insert(int x) {
		BTNode node = super.insert(x);
		BTNode startBalNode = node.parent;
		rebalanceFrom(startBalNode);
		return node;
	}
	
	private void rebalanceFrom(BTNode node) {
		BTNode cur = node;
		while(cur != null) {
			
			int oldHeight = cur.height;
			
			int newHeight = -1;
			switch(cur.balance()) {
				case 2:
					//smallRight
					int hC = BTNode.getHeight(cur.left.right);
					int hL = BTNode.getHeight(cur.left.left);
					if(hC <= hL) 
						cur = smallRightRotation(cur);
					else 
						cur = bigRightRotation(cur);
					
					newHeight = cur.height;
					break;
				case -2:
					//smallLeft
					int hC2 = BTNode.getHeight(cur.right.left);
					int hR = BTNode.getHeight(cur.right.right);
					if(hC2 <= hR)
						cur = smallLeftRotation(cur);
					else
						cur = bigLeftRotation(cur);
					
					newHeight = cur.height;
					break;
				default:
					newHeight = cur.calcHeight();
					cur.height = newHeight;
					break;
			}
			if(oldHeight == newHeight)
				break;
			
			cur = cur.parent;//идем вверх
		}
	}
	
	private BTNode removeNode(BTNode node) {
		if(node == null)
			return null;
		
		BTNode nodeParent = node.parent;
		
		if( node.left == null || node.right == null ) {//у удаляемого узла node один потомок/поддерево или совсем нет потомков
			//удаляем узел node, "подключаем" его единственного потомка(если он есть) к родителю удаляемого
			BTNode nodeChild = (node.left != null)? node.left: node.right;
			
			if(nodeParent != null) {
				if(nodeParent.left == node )
					nodeParent.left = nodeChild;
				else 
					nodeParent.right = nodeChild;
			}
			if(nodeChild != null)
				nodeChild.parent = nodeParent;
			
			if(top == node) top = nodeChild;
			return nodeParent;
		}
		//удаление узла, имеющего узлы слева и справа
		//BTNode replacer = getMinNode(node.right);
		BTNode replacer = getMaxNode(node.left);
		
		BTNode replParent = removeNode(replacer);
		
		replacer.left = node.left;
		if(node.left != null)
			node.left.parent = replacer;
		
		replacer.right = node.right;
		if(node.right != null)
			node.right.parent = replacer;
		
		replacer.parent = nodeParent;
		if(nodeParent != null) {
			if(nodeParent.left == node )
				nodeParent.left = replacer;
			else 
				nodeParent.right = replacer;
		}
		
		replacer.height = node.height;
		
		if(top == node) top = replacer;
		
		if(replParent == node)replParent = replacer;
		
		return replParent;
	}
	
	
	@Override
	public void remove(BTNode node) {
		BTNode startBalNode = removeNode(node);
		rebalanceFrom(startBalNode);
	}
	
	public void smallRightRotation() {
		smallRightRotation(top);
	}
	
	public BTNode smallRightRotation(BTNode node) {
		if(node == null)
			return null;
		if(node.left == null)
			return node;
		
		BTNode a = node;
		BTNode b = node.left;
		BTNode c = b.right;
		
		BTNode parent = node.parent;
		
		//top = b;
		b.parent = parent;
		if(parent != null) {
			if(parent.left == node) 
				parent.left = b;
			else 
				parent.right = b;
		}
		
		b.right = a;
		a.parent = b;
		
		a.left = c;
		if(c != null)
			c.parent = a;
		
		if(a == top) top = b;
		
		a.updateHeight();
		b.updateHeight();
		
		return b;
	}
	
	public void smallLeftRotation() {
		smallLeftRotation(top);
	}
	
	public BTNode smallLeftRotation(BTNode node) {
		if(node == null)
			return null;
		if(node.right == null)
			return node;
		
		BTNode a = node;
		BTNode b = node.right;
		BTNode c = b.left;
		
		BTNode parent = node.parent;
		
		//top = b;
		b.parent = parent;
		if(parent != null) {
			if(parent.left == node) 
				parent.left = b;
			else 
				parent.right = b;
		}
		
		b.left = a;
		a.parent = b;
		
		a.right = c;
		if(c != null)
			c.parent = a;
		
		if(a == top) top = b;
		
		a.updateHeight();
		b.updateHeight();
		
		return b;
	}
	
	public void bigRightRotation() {
		bigRightRotation(top);
	}
	
	public BTNode bigRightRotation(BTNode node) {
		if(node == null)
			return null;
		if(node.left == null)
			return node;
		if(node.left.right == null)
			return node;
		
		BTNode a = node;
		BTNode b = node.left;
		BTNode c = b.right;
		
		smallLeftRotation(b);
		smallRightRotation(a);
		
		return c;
	}
	
	public void bigLeftRotation() {
		bigLeftRotation(top);
	}
	
	public BTNode bigLeftRotation(BTNode node) {
		if(node == null)
			return null;
		if(node.right == null)
			return node;
		if(node.right.left == null)
			return node;
		
		BTNode a = node;
		BTNode b = node.right;
		BTNode c = b.left;
		
		smallRightRotation(b);
		smallLeftRotation(a);
		
		return c;
		
	}
	
}
