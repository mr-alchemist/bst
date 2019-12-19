
import storage.*;

public class Program {

	public static void main(String[] args) {
		Program pr = new Program();
		pr.run();
	}
	
	
	void run() {
		perfomanceTest();
		test20();
		//test30();
		test40();
	}
	
	void perfomanceTest() {
		
		System.out.println("ТЕСТ ПРОИЗВОДИТЕЛЬНОСТИ");
		System.out.println("");
		
		System.out.println("BSTree");
		System.out.println(";Заполнение в сл.порядке;;;;Заполнение в пор.возрастания;;;;");
		System.out.println("N;insert;search;search*;remove;insert;search;search*;remove;");
		
		testSimpleBST(1_000);
		testSimpleBST(10_000);
		testSimpleBST(100_000);
		testSimpleBST(1_000_000);
		testSimpleBST(10_000_000);
		
		System.out.println("");
		System.out.println("AVLTree");
		System.out.println(";Заполнение в сл.порядке;;;;;;Заполнение в пор.возрастания;;;;;;");
		System.out.println("N;insert;сбалансировано;search;search*;remove;сбалансировано;insert;сбалансировано;search;search*;remove;сбалансировано;");
		testAVLTree(1_000);
		testAVLTree(10_000);
		testAVLTree(100_000);
		testAVLTree(1_000_000);
		testAVLTree(10_000_000);
		//testAVLTree(20_000_000);
		
		System.out.println("");
		System.out.println("RBTree");
		System.out.println(";Заполнение в сл.порядке;;;;;;Заполнение в пор.возрастания;;;;;;");
		System.out.println("N;insert;является КЧ деревом;search;search*;remove;является КЧ деревом;insert;является КЧ деревом;search;search*;remove;является КЧ деревом;");
		testRBTree(1_000);
		testRBTree(10_000);
		testRBTree(100_000);
		testRBTree(1_000_000);
		testRBTree(10_000_000);
		
	}
	
	void test20() {
		System.out.println("");
		System.out.println("Визуализация AVLTree:");
		
		BSTree tree = new AVLTree();
		
		tree.insert(10);
		tree.insert(15);
		tree.insert(8);
		tree.insert(4);
		tree.insert(3);
		tree.insert(6);
		tree.insert(17);
		tree.insert(14);
		tree.insert(5);
		tree.insert(7);
		tree.insert(9);
		
		print(tree);
		
		System.out.println("Принудительно выполняем малое правое вращение. Результат:");
		tree.smallRightRotation();
		print(tree);
		
		System.out.println("Принудительно выполняем малое левое вращение. Результат:");
		tree.smallLeftRotation();
		
		print(tree);
		
		System.out.println("Удаляем элемент 10. Результат:");
		tree.remove(10);
		print(tree);
		
		visitIterative(tree.top);
		//System.out.print(isBST(tree)?"yes;":"no;" );
	}
	
	void test30() {
		System.out.println("");
		System.out.println("Визуализация AVLTree:");
		AVLTree tree = new AVLTree();
		
		for(int i = 1; i <= 16;i++) {
			tree.insert(i);
			print(tree);
		}
		/*tree.insert(10);
		print(tree);
		tree.insert(4);
		print(tree);
		tree.insert(12);
		print(tree);
		tree.insert(2);
		print(tree);
		tree.insert(5);
		print(tree);
		tree.insert(13);
		print(tree);
		tree.insert(7);
		print(tree);
		tree.insert(15);
		print(tree);
		tree.insert(9);
		print(tree);
		tree.insert(1);
		print(tree);
		tree.insert(11);
		print(tree);
		tree.insert(3);
		print(tree);
		tree.insert(6);
		print(tree);
		tree.insert(14);
		print(tree);
		tree.insert(8);
		print(tree);
		
		//--------
		tree.insert(24);
		print(tree);
		tree.insert(32);
		print(tree);
		tree.insert(-1);
		print(tree);
		tree.insert(18);
		print(tree);
		tree.insert(20);
		print(tree);
		tree.insert(-5);
		print(tree);
		tree.insert(100);
		print(tree);
		
		*/
		
	}
	
	void test40() {
		System.out.println("");
		System.out.println("Визуализация RBTree:");
		
		BSTree tree = new RBTree();
		
		tree.insert(10);
		tree.insert(15);
		tree.insert(8);
		tree.insert(4);
		tree.insert(3);
		tree.insert(6);
		tree.insert(17);
		tree.insert(14);
		tree.insert(5);
		tree.insert(7);
		tree.insert(9);
		
		print(tree, true);
		
		System.out.println("Удаляем элемент 10. Результат:");
		tree.remove(10);
		print(tree, true);
		
		visitIterative(tree.top);
		//System.out.print(isBST(tree)?"yes;":"no;" );
	}
	
	void testSimpleBST(int N){
		testBST(N, 1);
	}
	
	void testAVLTree(int N){
		testBST(N, 2);
	}
	
	void testRBTree(int N){
		testBST(N, 3);
	}
	
	
	void testBST(int N, int option) {
		System.out.print(N + ";");
		int[] values = new int[N];
		
		//НЕПОВТОРЯЮЩИЕСЯ ЭЛЕМЕНТЫ В СЛУЧАЙНОМ ПОРЯДКЕ
		for(int i = 0; i < N ; i++) 
			values[i] = 2*i + 1;//заполняем нечетными числами
		shuffleArray(values);
		
		
		int[] values2 = new int[N];		
		System.arraycopy(values, 0, values2, 0, N);;
		shuffleArray(values2);
		
		int[] searchValues1 = new int[N/10];
		for(int i = 0; i < searchValues1.length ; i++) {//в массиве searchValues будут неповторяющиеся элементы, которые точно есть в дереве
			searchValues1[i] = values2[i];
		}
		
		int[] values3 = new int[N];
		for(int i = 0; i < N ; i++) 
			values3[i] = 2*i;//четные числа
		shuffleArray(values3);
		
		
		int[] searchValues2 = new int[N/10];
		for(int i = 0; i < searchValues2.length ; i++) {//в массиве searchValues2 будут неповторяющиеся элементы, которых точно нет в дереве
			searchValues2[i] = values3[i];
		}
		
		testBSTMethods(values, searchValues1, searchValues2, option);
		
		//ЭЛЕМЕНТЫ ПО ВОЗРАСТАНИЮ
		if(N > 100_000 && option == 1)//для значений N > 100_000 тестирование заполнения дерева по возрастанию не проводим, т.к. долго выполняется
			System.out.print(";;;;;;");
		else {
			//заполняем	values по возрастанию, не перемешивая		
			for(int i = 0; i < N ; i++) 
				values[i] = 2*i + 1;//заполняем нечетными числами
			
			testBSTMethods(values, searchValues1, searchValues2, option);
		}
		
		System.out.println("");
	}
	
	void testBSTMethods(int[] values, int[] searchValues1, int[] searchValues2, int option) {
		int N = values.length;
		BSTree tree ;
		switch(option) {
			case 1:
				tree = new BSTree();
				break;
			case 2:
				tree = new AVLTree();
				break;
			case 3:
				tree = new RBTree();
				break;
			default:
				tree = null;
				break;
		}
		
		//System.out.println("Adding "+ N +"(N) random values to the tree");
		Stopwatch sw = new Stopwatch();
		sw.start();
		for(int i = 0; i < N; i++) {
			//int value = (int)(Math.random()*N);
			int value = values[i];
			tree.insert(value);
		}
		sw.stop();
		//System.out.println("Added in "+ sw.getDuration() +" ms");
		System.out.print(sw.getDuration() + ";");
		//System.out.print("isBST2:" + (isBST2(tree)?"yes;":"no;") );
		//System.out.print("isBalanced:" + (isBalanced(tree, false)?"yes;":"no;") );
		if(option == 2)
			System.out.print( (isBalanced(tree, false)?"да;":"нет;") );
		
		if(option == 3)
			System.out.print( (isRBTree(tree)?"да;":"нет;") );
		
		/*if(values.length <= 20) {
			System.out.println("");
			print(tree);
		}*/
		
		//System.out.println("Searching "+ searchValues1.length +" random values in the tree");
		sw.start();
		int found = 0;
		for(int i = 0; i < searchValues1.length; i++) {
			int value = searchValues1[i];
			if(tree.search(value))
				found++;
		}
		sw.stop();
		//System.out.println("Searched in "+ sw.getDuration() +" ms. Found " + found);
		System.out.print(sw.getDuration() + ";");
		//System.out.print("found " + found + ";");
		
		//System.out.println("Searching "+ searchValues2.length +" random values in the tree");
		sw.start();
		found = 0;
		for(int i = 0; i < searchValues2.length; i++) {
			int value = searchValues2[i];
			if(tree.search(value))
				found++;
		}
		sw.stop();
		//System.out.println("Searched in "+ sw.getDuration() +" ms. Found " + found);
		System.out.print(sw.getDuration() + ";");
		//System.out.print("found " + found + ";");
		
		//System.out.println("Removing "+ searchValues2.length +" random values from the tree");
		sw.start();
		for(int i = 0; i < searchValues1.length; i++) {
			int value = searchValues1[i];
			tree.remove(value);
				
		}
		sw.stop();
		//System.out.println("Removed in "+ sw.getDuration() +" ms");
		System.out.print(sw.getDuration() + ";");
		
		//System.out.print("isBST2:" + (isBST2(tree)?"yes;":"no;") );
		//System.out.print("isBalanced:" + (isBalanced(tree, false)?"yes;":"no;") );
		if(option == 2)
			System.out.print( (isBalanced(tree, false)?"да;":"нет;") );
		
		if(option == 3)
			System.out.print( (isRBTree(tree)?"да;":"нет;") );
		
	}
	
	//Вывод дерева в консоль:
	
	
	void print(BSTree tree) {
		print(tree, false);
	}
	
	void print(BSTree tree, boolean showRBColor) {
		int width = showRBColor? 7 : 4;
		int totalLevels = getHeight(tree);
		
		System.out.println("-----");
		System.out.println("totalLevels: " + totalLevels);
		int elmsMaxCntInLastLevel = (int)Math.pow(2, totalLevels -1);
		int maxLettersCntInRow = elmsMaxCntInLastLevel*width;
		
		BTNode[] levelNodes = new BTNode[1];
		levelNodes[0] = tree.top;
		
		boolean existsNode = true;
		for(int curLevel = 0;curLevel < totalLevels && existsNode; curLevel++) {
			int elmCntInLevel = (int)Math.pow(2, curLevel);
			String s = "";
			s += getNSpb( (maxLettersCntInRow - elmCntInLevel*width) / (2*elmCntInLevel) );
			
			BTNode[] newLevelNodes = new BTNode[elmCntInLevel*2];
			existsNode = false;
			for(int i = 0; i < elmCntInLevel; i++) {
				
				if(levelNodes[i] != null) { 
					s += getNodeStr(levelNodes[i], width, showRBColor);

					newLevelNodes[i*2] = levelNodes[i].left;
					newLevelNodes[i*2 + 1] = levelNodes[i].right;
					
					if(levelNodes[i].left != null || levelNodes[i].right != null)
						existsNode = true;
				}
				else
					s += getNSpb(width);
					
				if(i < elmCntInLevel - 1)
					s += getNSpb( (maxLettersCntInRow - elmCntInLevel*width) / elmCntInLevel );
				
				
			}
			
			s += getNSpb( (maxLettersCntInRow - elmCntInLevel*width) / (2*elmCntInLevel) );
			System.out.println(s);
			System.out.println(getNSpb(maxLettersCntInRow));
			levelNodes = newLevelNodes;
		}
	}
	
	private int getHeight(BSTree tree) {
		
		if(tree.top == null)
			return 0;
		
		BTNode[] levelNodes = new BTNode[1];
		levelNodes[0] = tree.top;
		int res = 1;
		boolean existsNode = true;
		while(existsNode) {
			BTNode[] newLevelNodes = new BTNode[levelNodes.length*2];
			existsNode = false;
			for(int i = 0; i < levelNodes.length; i++) {
				if(levelNodes[i] != null) {
					newLevelNodes[i*2] = levelNodes[i].left;
					newLevelNodes[i*2 + 1] = levelNodes[i].right;
					
					if(levelNodes[i].left != null || levelNodes[i].right != null) {
						existsNode = true;
					}
				}
			}
			levelNodes = newLevelNodes;
			if(existsNode)res++;
		}
		return res;
	}
	
	private String getNSpb(int cnt) {
		String s = "";
		for(int i = 0; i < cnt; i++)
			s += " ";
		return s;
	}
	
	private String getNumStr(int n, int w) {
		String s = Integer.toString(n);
		int sLength = s.length();
		if(sLength < w) {
			int diff = w - sLength;
			if(diff%2 != 0) {
				s = s + " ";
				diff--;
			}
			for(int i=0;i < diff;i+=2) {
				s = " "+ s + " ";
			}
		}
		return s;
	}
	
	private String getNodeStr(BTNode node, int w, boolean showColor) {
		String s = Integer.toString(node.value) + (showColor?(BTNode.isRed(node)?"(R)":"(B)") :"" );
		int sLength = s.length();
		if(sLength < w) {
			int diff = w - sLength;
			if(diff%2 != 0) {
				s = s + " ";
				diff--;
			}
			for(int i=0;i < diff;i+=2) {
				s = " "+ s + " ";
			}
		}
		return s;
	}
	
	//проверка, является ли двоичное дерево деревом поиска:
	boolean isBST(BSTree tree) {//рекурсивная
		if(tree.top == null)return false;
		
		if(tree.top.left != null) {
			BSTree leftSubTree = new BSTree(tree.top.left);
			
			if(!isBST(leftSubTree))
				return false;
			
			if(getMax(leftSubTree) > tree.top.value)
				return false;
			
		}
		
		if(tree.top.right != null) {
			BSTree rightSubTree = new BSTree(tree.top.right);
			
			if(!isBST(rightSubTree))
				return false;
			
			if(getMin(rightSubTree) <= tree.top.value)
				return false;
		}
		
		return true;
	}
	
	private int getMax(BSTree tree) {
		BTNode current = tree.top;
		while(current.right != null)
			current = current.right;
		
		return current.value;
	}
	
	private int getMin(BSTree tree) {
		BTNode current = tree.top;
		while(current.left != null)
			current = current.left;
		
		return current.value;
	}
	
	
    void visitIterative(BTNode node){
        Stack<BTNode> stack = new Stack<BTNode>(); 
        BTNode cur = node;
        
        while (cur != null || !stack.isEmpty()){
            if (!stack.isEmpty()){
            	cur = stack.pop();
            	System.out.println(cur.value);
            	
                if ( cur.right != null) cur = cur.right;
                else cur=null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
    }
    
    boolean isBST2(BSTree tree){//итеративная
        Stack<BTNode> stack = new Stack<BTNode>(); 
        BTNode cur = tree.top;
        boolean firstMove = true;
        int lastValue = 0;
        
        //boolean setBug = false;
        while (cur != null || !stack.isEmpty()){
            if (!stack.isEmpty()){
            	cur = stack.pop();
            	
            	/*if(!setBug) {
	            	if(cur.left == null && cur.right == null) {
	            		cur.height = cur.height + 1;
	            		setBug = true;
	            		System.out.println("Info: setting bug height at node "+ cur.value);
	            	}
            	}*/
            	
            	if(firstMove) {
            		firstMove = false;
            	}
            	else {
            		if(lastValue > cur.value)
            			return false;
            		
            		if(lastValue == cur.value) {
            			if(cur.parent != null && cur.parent.right == cur && cur.value == cur.parent.value)
            				return false;
            		}
            	}
            	lastValue = cur.value;
            	
                if ( cur.right != null) cur = cur.right;
                else cur=null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
        
        return true;
    }
    
    void checkAndFixHeights(BSTree tree, boolean viewErrors) {
    	Stack<BTNode> stack = new Stack<BTNode>(); 
        BTNode cur = tree.top;
        
        while (cur != null || !stack.isEmpty()){
            if (!stack.isEmpty()){
            	cur = stack.pop();
            	
            	//System.out.println(cur.value);
            	if(cur.left == null && cur.right == null) {//лист
            		//идем вверх, проверяя/исправляя на ходу высоту у встречающихся узлов 
            		
            		int actualHeight = 0;//актуальное значение высоты, для листа это 0
            		
            		if (cur.height != actualHeight) {
            			if(viewErrors)
            				System.out.println("Warning: node " + cur.value + ": realH is " + actualHeight + ", but stored " + cur.height + ". Fixing.. ");
            			cur.height = actualHeight;
            		}
            		
            		BTNode cur2 = cur.parent;
            		while(cur2 != null) {
            			actualHeight = cur2.calcHeight();
            			if (cur2.height != actualHeight) {
            				if(viewErrors)
            					System.out.println("Warning: node " + cur2.value + ": realH is " + actualHeight + ", but stored " + cur2.height + ". Fixing.. ");
                			cur2.height = actualHeight;
                		}
            			cur2 = cur2.parent;
            		}
            		
            	}
            	
                if ( cur.right != null) cur = cur.right;
                else cur=null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
    }
    
    boolean isBalanced(BSTree tree, boolean viewErrors){
    	checkAndFixHeights(tree, viewErrors);
    	
    	Stack<BTNode> stack = new Stack<BTNode>(); 
        BTNode cur = tree.top;
        
        boolean res = true;
        while (cur != null || !stack.isEmpty()){//обходим все вершины дерева и проверяем балансы
            if (!stack.isEmpty()){
            	cur = stack.pop();
            	
            	//System.out.println(cur.value);
            	if( Math.abs(cur.balance()) > 1 ) {
            		if(viewErrors)
            			System.out.println("Error: node " + cur.value + " has balance = " + cur.balance());
            		res = false;
            		if(!viewErrors)
            			return false;//сразу выходим
            	}
            	
                if ( cur.right != null) cur = cur.right;
                else cur=null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }
    
    void shuffleArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			int j = (int)(Math.random()*array.length);
			int buf = array[i];
			array[i] = array[j];
			array[j] = buf;
		}
	}
    
    boolean isRBTree(BSTree tree) {
    	
    	//сначала проверяем "черные" высоты
    	if(!checkBlackHeights(tree))
    		return false;
    	
    	Stack<BTNode> stack = new Stack<BTNode>(); 
        BTNode cur = tree.top;
        
        while (cur != null || !stack.isEmpty()){
            if (!stack.isEmpty()){
            	cur = stack.pop();
            	
            	//ОБРАБОТКА УЗЛА
            	//System.out.println(cur.value);
            	if(cur == tree.top && BTNode.isRed(cur)) 
            		return false;
            	
            	if( BTNode.isRed(cur) && (BTNode.isRed(cur.left) || BTNode.isRed(cur.right)) )
            		return false;
            	
            	//КОНЕЦ ОБРАБОТКИ УЗЛА
            	
                if ( cur.right != null) cur = cur.right;
                else cur = null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
    	return true;
    }
    
    //
    boolean checkBlackHeights(BSTree tree) {
    	Stack<BTNode> stack = new Stack<BTNode>(); 
        BTNode cur = tree.top;
        
        boolean wasHeight = false;
        int firstHeight = 0;
        
        while (cur != null || !stack.isEmpty()){
            if (!stack.isEmpty()){
            	cur = stack.pop();
            	
            	//
            	//System.out.println(cur.value);
            	if(cur.left == null || cur.right == null) {//узел с 2 nil-листами или 1 nil-листом
            		int blackHeight = 0;
            		//идем вверх, считая черную высоту(nil-листы не считаем)
            		BTNode cur2 = cur;
            		while(cur2 != null) {
            			if(BTNode.isBlack(cur2))
            				blackHeight++;
            			cur2 = cur2.parent;
            		}
            		if(!wasHeight) {
            			firstHeight = blackHeight;
            			wasHeight = true;
            		}
            		else {
            			if(blackHeight != firstHeight)
            				return false;
            		}
            	}
            	
            	//
            	
                if ( cur.right != null) cur = cur.right;
                else cur = null;
            }
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
        }
        return true;
    }
    
    
    
}
