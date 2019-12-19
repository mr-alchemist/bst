
public class BSTree {
	BTNode top;
	
	public BSTree() {
		this.top = null;
	}
	
	public BSTree(BTNode top) {
		this.top = top;
	}
	
	public BTNode insert(int x) {
		if(top == null) {
			top = new BTNode();
			top.value = x;
			return top;
		}
		BTNode cur = top;
		
		while(true) {
			if( x > cur.value) {//go to right
				if(cur.right == null) {
					BTNode node = new BTNode(cur);
					node.value = x;
					cur.right = node;
					return node;
				}
				cur = cur.right;
			}
			else {//go to left
				if(cur.left == null) {
					BTNode node = new BTNode(cur);
					node.value = x;
					cur.left = node;
					return node;
				}
				cur = cur.left;
			}
		}
	}
	
	public void remove(int x) {//удал€ет первый найденный узел со значением value
		remove(searchNode(x));
	}
	
	public boolean search(int x) {
		if(searchNode(x) != null)
			return true;
		
		return false;
	}
	
	
	BTNode searchNode(int value) {
		BTNode cur = top;
		while(cur != null) {
			if(value > cur.value)
				cur = cur.right;
			else {
				if(cur.value == value)
					return cur;
				cur = cur.left;
			}
		}
		return null;
	}
	
	
	BTNode getMinNode(BTNode node) {
		if(node == null)
			return null;
		
		while(true) {
			if(node.left == null)
				return node;
			node = node.left;
		}
	}
	
	BTNode getMaxNode(BTNode node) {
		if(node == null)
			return null;
		
		while(true) {
			if(node.right == null)
				return node;
			node = node.right;
		}
	}
	
	
	public void remove(BTNode node) {
		if(node == null)
			return;
		
		BTNode nodeParent = node.parent;
		
		if( node.left == null || node.right == null ) {//у удал€емого узла node один потомок/поддерево или совсем нет потомков
			//удал€ем узел node, "подключаем" его единственного потомка(если он есть) к родителю удал€емого
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
			return;
		}
		//удаление узла, имеющего узлы слева и справа
		//BTNode replacer = getMinNode(node.right);
		BTNode replacer = getMaxNode(node.left);
		
		remove(replacer);
		
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
		if(top == node) top = replacer;
	}
	
	//
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
		
		return b;
	}
	
}
