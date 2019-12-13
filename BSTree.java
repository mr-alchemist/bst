
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
	
	public void remove(int x) {//������� ������ ��������� ���� �� ��������� value
		remove(searchNode(x));
	}
	
	public boolean search(int x) {
		if(searchNode(x) != null)
			return true;
		
		return false;
	}
	
	
	private BTNode searchNode(int value) {
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
		
		if( node.left == null || node.right == null ) {//� ���������� ���� node ���� �������/��������� ��� ������ ��� ��������
			//������� ���� node, "����������" ��� ������������� �������(���� �� ����) � �������� ����������
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
		//�������� ����, �������� ���� ����� � ������
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
	
	
}
