//Tran Anh Khoa Huynh
//Data Structure- CS3345.001
//Project 2
package Project2;

//BinarySearchTree class
//
//CONSTRUCTION: with no initializer
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//void remove( x )       --> Remove x
//boolean contains( x )  --> Return true if x is present
//Comparable findMin( )  --> Return smallest item
//Comparable findMax( )  --> Return largest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//void printTree( )      --> Print tree in sorted order
//******************ERRORS********************************
//Throws UnderflowException as appropriate

/**
* Implements an unbalanced binary search tree.
* Note that all "matching" is based on the compareTo method.
* @author Mark Allen Weiss
*/




public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
 /**
  * Construct the tree.
  */
 public BinarySearchTree( )
 {
     root = null;
 }

 /**
  * Insert into the tree; duplicates are ignored.
  * @param x the item to insert.
  */
 public void insert( AnyType x )
 {
     root = insert( x, root );
 }

 /**
  * Remove from the tree. Nothing is done if x is not found.
  * @param x the item to remove.
  */
 public void remove( AnyType x )
 {
     root = remove( x, root );
 }

 /**
  * Find the smallest item in the tree.
  * @return smallest item or null if empty.
  */
 public AnyType findMin( )
 {
     if( isEmpty( ) )
         throw new UnderflowException( );
     return findMin( root ).element;
 }

 /**
  * Find the largest item in the tree.
  * @return the largest item of null if empty.
  */
 public AnyType findMax( )
 {
     if( isEmpty( ) )
         throw new UnderflowException( );
     return findMax( root ).element;
 }

 /**
  * Find an item in the tree.
  * @param x the item to search for.
  * @return true if not found.
  */
 public boolean contains( AnyType x )
 {
     return contains( x, root );
 }

 /**
  * Make the tree logically empty.
  */
 public void makeEmpty( )
 {
     root = null;
 }

 /**
  * Test if the tree is logically empty.
  * @return true if empty, false otherwise.
  */
 public boolean isEmpty( )
 {
     return root == null;
 }

 /**
  * Print the tree contents in sorted order.
  */
 public void printTree( )
 {
     if( isEmpty( ) )
         System.out.println( "Empty tree" );
     else
         printTree( root );
 }

 /**
  * Internal method to insert into a subtree.
  * @param x the item to insert.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return new BinaryNode<>( x, null, null );
     
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = insert( x, t.left );
     else if( compareResult > 0 )
         t.right = insert( x, t.right );
     else
         ;  // Duplicate; do nothing
     return t;
 }

 /**
  * Internal method to remove from a subtree.
  * @param x the item to remove.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return t;   // Item not found; do nothing
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = remove( x, t.left );
     else if( compareResult > 0 )
         t.right = remove( x, t.right );
     else if( t.left != null && t.right != null ) // Two children
     {
         t.element = findMin( t.right ).element;
         t.right = remove( t.element, t.right );
     }
     else
         t = ( t.left != null ) ? t.left : t.right;
     return t;
 }

 /**
  * Internal method to find the smallest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the smallest item.
  */
 private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
 {
     if( t == null )
         return null;
     else if( t.left == null )
         return t;
     return findMin( t.left );
 }

 /**
  * Internal method to find the largest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the largest item.
  */
 private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
 {
     if( t != null )
         while( t.right != null )
             t = t.right;

     return t;
 }

 /**
  * Internal method to find an item in a subtree.
  * @param x is item to search for.
  * @param t the node that roots the subtree.
  * @return node containing the matched item.
  */
 private boolean contains( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return false;
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         return contains( x, t.left );
     else if( compareResult > 0 )
         return contains( x, t.right );
     else
         return true;    // Match
 }

 /**
  * Internal method to print a subtree in sorted order.
  * @param t the node that roots the subtree.
  */
 private void printTree( BinaryNode<AnyType> t )
 {
     if( t != null )
     {
         printTree( t.left );
         System.out.println( t.element );
         printTree( t.right);
     }
 }

 /**
  * Internal method to compute height of a subtree.
  * @param t the node that roots the subtree.
  */
 private int height( BinaryNode<AnyType> t )
 {
     if( t == null )
         return -1;
     else
         return 1 + Math.max( height( t.left ), height( t.right ) );    
 }
 
 /**
  * Count nodes method to count all node in binary tree
  * @param t the node that roots the subtree.
  */
 private int nodeCount(BinaryNode<AnyType> t)
 {
	 if(t == null)
		 return 0;
	 int l = nodeCount(t.left);
	 int r = nodeCount(t.right);
	 
	 return 1+l + r;
	 
 }
 
 /**
  * isFull method to check if it is Full Binary Tree
  * @param t the node that roots the subtree.
  */
 private boolean isFull(BinaryNode<AnyType> t)
 {
	 if(t == null)
		 return true;
	 if(t.left == null && t.right == null)
		 return true;
	 if(t.left!=null && t.right!=null)
		 return (isFull(t.left)&& isFull(t.right));
	 
	 return false;
 }
 
 /**
  * compareStructure method to check if two trees have same structure
  * @param t the node that roots the subtree.
  */
 private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
 {
	 if(t1==null && t2 ==null)
		 return true;
	 if(t1!=null && t2!= null)
	 {
		 return (compareStructure(t1.left,t2.left)&& compareStructure(t1.right,t2.right));
	 }
	 return false;
 }
 
 /**
  * equals method to check if the current tree equal to another tree and returns true
  * @param t the node that roots the subtree.
  */
 private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType>t2)
 {
	 if(t1==null && t2 == null) {
		 return true;
	 }
	 if(t1 != null && t2 != null) {
		 return (t1.element.equals(t2.element) && equals(t1.left,t2.left) && equals(t1.right, t2.right));
	 }
	 return false;
 }
 
 /**
  * copy method to create and returns a new tree that is a copy of the original tree.
  * @param t the node that roots the subtree.
  */
 private BinarySearchTree<AnyType> copy()
 {
	  BinarySearchTree<AnyType> copyTree = new BinarySearchTree<>();
	  copyTree.root = copyTree(root); 
	  return copyTree;
 }
 
 private BinaryNode<AnyType> copyTree(BinaryNode<AnyType> t) 
 {
	if(t==null)
	{
		return t;
	}
	BinaryNode<AnyType> newNode = new BinaryNode<> (t.element);
	newNode.left = copyTree(t.left);
	newNode.right= copyTree(t.right);
	
	return newNode;
 }
 
 /**
  * mirror method to creates and returns a new tree that is a mirror image of the original tree.
  * @param t the node that roots the subtree.
  */
 private BinarySearchTree<AnyType> mirror()
 {
	 BinarySearchTree<AnyType> mirrorTree = new BinarySearchTree<>();
	 mirrorTree.root = mirror(root);
	 return mirrorTree;
 }
 
 private BinaryNode<AnyType> mirror(BinaryNode<AnyType> originalTree)
 {
	 if(originalTree == null)
	 {
		 return null;
	 }
	 
	 BinaryNode<AnyType> newMirrorTree = new BinaryNode<>(originalTree.element);
	 newMirrorTree.left = mirror(originalTree.right); // Swap left and right subtrees
	 newMirrorTree.right = mirror(originalTree.left); // Swap left and right subtrees

	    return newMirrorTree;
 }
 
 /**
  * isMirror method to returns true if the tree is a mirror of the passed tree.
  * @param originalTree node for the original tree and the mirrorTree for the mirror tree.
  */
 private boolean isMirror(BinaryNode<AnyType> originalTree, BinaryNode<AnyType> mirrorTree) 
 {
	 if(originalTree==null && mirrorTree==null)
		 return true;
	
	 if(originalTree == null || mirrorTree == null)
		 return false;
				 
	 return originalTree.element == mirrorTree.element && isMirror(originalTree.left,mirrorTree.right)
			 && isMirror(originalTree.right,mirrorTree.left);
 }
 
 /**
  * rotateRight method to performs a single rotation on the node having the passed value.
  * @param t the node that roots the subtree.
  */
 public void rotateRight(AnyType valueToRotate) {
	    root = rotateRight(root, valueToRotate);
	}
 private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t, AnyType valueToRotate)
 {
	 if (t == null) {
		 throw new UnderflowException(); // Value not found, do nothing
	 }
	 int compareResult = valueToRotate.compareTo(t.element);
	 if (compareResult < 0)
	 {
		 // Value is in the left subtree
		 t.left = rotateRight(t.left, valueToRotate);
	 } 
	 else if (compareResult > 0)
	 {
		 // Value is in the right subtree
		 t.right = rotateRight(t.right, valueToRotate);
	 } 
	 else
	 {
		 // Found the node to rotate
		 if (t.left != null)
		 {
			 BinaryNode<AnyType> newRoot = t.left;
			 t.left = newRoot.right;
			 newRoot.right = t;
			 t = newRoot;
		 }
		 else
		 {
			 throw new UnderflowException();
	     }
	 }
	 return t;
 }

 /**
  * rotateLeft method to performs a single rotation on the node having the passed value.
  * @param t the node that roots the subtree.
  */
 public void rotateLeft(AnyType valueToRotate)
 {
	    root = rotateLeft(root, valueToRotate);
 }
 private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t, AnyType valueToRotate) 
 {
	    if (t == null) {
	        throw new UnderflowException(); // Value not found, do nothing
	    }

	    int compareResult = valueToRotate.compareTo(t.element);

	    if (compareResult < 0) {
	        // Value is in the left subtree
	        t.left = rotateLeft(t.left, valueToRotate);
	    } else if (compareResult > 0) {
	        // Value is in the right subtree
	        t.right = rotateLeft(t.right, valueToRotate);
	    } else {
	        // Found the node to rotate
	        if (t.right != null) {
	            BinaryNode<AnyType> newRoot = t.right;
	            t.right = newRoot.left;
	            newRoot.left = t;
	            t = newRoot;
	        } else {
	            throw new UnderflowException();
	        }
	    } 
	 return t;
 }
 
 /**
  * printLevelOrder method to print level order traversal of tree
  * @param t the node that roots the subtree.
  */
 private void printGivenLevel(BinaryNode<AnyType>t, int height)
 {	 
	 if(t==null)
	 {
		return; 
	 }
	 if(height==1)
	 {
		 System.out.print(t.element + " ");
	 }
	 else if(height>1)
	 {
		 printGivenLevel(t.left,height-1);
		 printGivenLevel(t.right,height-1);
	 }
 }
 
 private void printLevelOrder()
 {
	 int height = height(root);
	 for(int i =1; i <=height+1;i++)
	 {
		 printGivenLevel(root,i);
		 System.out.println();
	 }
 }
 
 
 // Basic node stored in unbalanced binary search trees
 private static class BinaryNode<AnyType>
 {
         // Constructors
     BinaryNode( AnyType theElement )
     {
         this( theElement, null, null );
     }

     BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
     {
         element  = theElement;
         left     = lt;
         right    = rt;
     }

     AnyType element;            // The data in the node
     BinaryNode<AnyType> left;   // Left child
     BinaryNode<AnyType> right;  // Right child
 }


   /** The tree root. */
 private BinaryNode<AnyType> root;


     // Test program
 public static void main( String [ ] args )
 {
	     BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
	     BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
	     BinarySearchTree<Integer> tree3 = new BinarySearchTree<>();
	     final int[] elements = {100, 50, 150, 40, 45};
	     final int[] elements2 = {5,3,8,1,4};
	     final int[] elements3 = {100, 50, 150, 40, 45};
	     // Insert elements into the binary search tree 1
	     for (int element : elements) {
	         tree1.insert(element);
	     }
	     
	     // Insert elements into the binary search tree2
	     for (int element1 : elements2) {
	         tree2.insert(element1);
	     }
	     
	     // Insert elements into the binary search tree2
	     for (int element2 : elements3) {
	         tree3.insert(element2);
	     }
	     
	     //Call print_Function
	     System.out.println("This is tree1, print by internal method:");
	     tree1.printTree();
	     System.out.println();
	     System.out.println("This is tree2, print by internal method:");
	     tree2.printTree();
	     
	     System.out.println("This is tree3, print by internal method:");
	     tree2.printTree();
	     
	     System.out.println("_______________________________________________");
	     System.out.println();
	     
	     // Call nodeCount and print the result
	     System.out.println("Call the nodeCount method:");
	     int totalNodes = tree1.nodeCount(tree1.root); // Call nodeCount on the tree instance
	     System.out.println("Total number of nodes in the tree1: " + totalNodes);
	     
	     System.out.println("_______________________________________________");
	     System.out.println();
	     	   
	     //Call isFull to check if the tree is full and print the result
	     System.out.print("Call the isFull method:");
	     System.out.println("Tree1 check:");
	     if (tree1.isFull(tree1.root))
	         System.out.println("The binary tree is full!");
	     else
	         System.out.println("The binary tree is not full!");
	     
	     System.out.println("Tree2 check:");
	     if (tree2.isFull(tree2.root))
	         System.out.println("The binary tree is full!");
	     else
	         System.out.println("The binary tree is not full!");
	     
	     System.out.println("_______________________________________________");
	     System.out.println();
	     
	     //Call compareStructure to check if two trees have same structure
	     System.out.println("Call the compareStructure method:");
	     System.out.println("Compare between tree1 and tree2:");
	     if(tree1.compareStructure(tree1.root,tree2.root))
	    	 System.out.println("Both trees have the same structure!");
	     else
	    	 System.out.println("Trees do not have the same structure!");   
	   
	     System.out.println();
	     
	     System.out.println("Compare between tree1 and tree3:");
	     if(tree1.compareStructure(tree1.root,tree3.root))
	    	 System.out.println("Both trees have the same structure!");
	     else
	    	 System.out.println("Trees do not have the same structure!");   
	     
	     System.out.println("_______________________________________________");
	     System.out.println();
	     
	     //Call equals to check if two tree are identical
	     System.out.println("Call the equals method:");
	     System.out.println("Compare to check identical between tree 1 and tree2:");
	     if(tree1.equals(tree1.root,tree2.root))
	    	 System.out.println("Both trees are identical!");
	     else
	    	 System.out.println("Trees do not identical!");  
	     
	     System.out.println();
	     
	     System.out.println("Compare to check identical between tree 1 and tree3:");
	     if(tree1.equals(tree1.root,tree3.root))
	    	 System.out.println("Both trees are identical!");
	     else
	    	 System.out.println("Trees do not identical!");  
	     
	     System.out.println("_______________________________________________");
	     System.out.println();
	     
	     //Call copy to create new tree and copy the original tree
	     System.out.println("Call the copy method:");
	     BinarySearchTree<Integer> copiedTree = tree1.copy();
	     //Print the original tree and its copy
	     System.out.println("Copied Tree from tree1:");
	     copiedTree.printTree();

	     System.out.println("_______________________________________________");
	     System.out.println();
	     
	     //Call mirror method
	     System.out.println("Call mirror method: ");
	     System.out.println("Original Tree: ");
	     tree1.printLevelOrder();
	     //Create a mirror image of the tree
	     BinarySearchTree<Integer> mirrorTree = tree1.mirror();
	     System.out.println();
	     //Call printLevelOrder to print the mirror tree
	     System.out.println("Mirror Tree:");
	     mirrorTree.printLevelOrder();
	     
	     System.out.println("_______________________________________________");
	     System.out.println();	     
	     
	     //Call isMirror to check if the tree is a mirror of the passed tree.
	     System.out.println("Call isMirror method:");
	     System.out.println("Check if Mirror Tree is the mirror of the tree 1:");
	     if(tree1.isMirror(tree1.root,mirrorTree.root))
	     {
	    	 System.out.println("Yes, this is mirror tree!");
	     }
	     else
	    	 System.out.println("No, this is not mirror tree!");
	     
	     System.out.println("_______________________________________________");
	     System.out.println();	 
	     
	     //Call printLevelOrder to print the original tree
	     System.out.println("Call rightRotate method:");
	     System.out.println("Tree1 before Right Rotation: ");
	     tree1.printLevelOrder();
	     
	     System.out.println();
	     //Call rotateRight to perform the right rotation
	     // Value to rotate
	     int valueToRotate = 100;
	     tree1.rotateRight(valueToRotate);
	     System.out.println("Tree after right rotation at " + valueToRotate +":");
	     tree1.printLevelOrder();
	     
	     System.out.println("_______________________________________________");
	     System.out.println();	 

	     //Call rotateLeft to perform the left rotation
	     System.out.println("Call the leftRotate method:");
	     System.out.println("Original Tree before Left Rotation: ");
	     tree1.printLevelOrder();
	     
	     System.out.println();
	     //Call rotationLeft to perform the right rotation
	     valueToRotate = 50;
	     tree1.rotateLeft(valueToRotate);
	     System.out.println("Tree after left rotation at " + valueToRotate +":");
	     tree1.printLevelOrder();
	 }
}	



