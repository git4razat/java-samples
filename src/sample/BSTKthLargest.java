package sample;

class BNode {
	BNode left;
	BNode right;	
	int data;
}

class BST {
  
  int countElement = 0;
    
  public BNode getKthLargestElementBST(BNode node, int k) {
    if (node == null) {
      return null;
    }
    
    BNode right = getKthLargestElementBST(node.right, k);
    
    if(right != null) {
      return right;
    }
    
    countElement++;
    if(countElement == k) {
      return node;
    }
    
    return getKthLargestElementBST(node.left, k);
  }
    
  public BNode insert(BNode node, int val) {
    if(node == null) {
      return createNewNode(val);
    }
    
    if(val < node.data) {
      node.left = insert(node.left, val);
    } else if((val > node.data)) {
      node.right = insert(node.right, val);
    }
    
    return node;
  }
  
  public BNode createNewNode(int k) {
    BNode a = new BNode();
    a.data = k;
    a.left = null;
    a.right = null;
    return a;
  }
}

public class BSTKthLargest {

  public static void main(String[] args) {
    BST a = new BST();
    BNode root = null;
    
    root = a.insert(root, 8);
    root = a.insert(root, 15);
    root = a.insert(root, 6);
    root = a.insert(root, 2);
    root = a.insert(root, 7);
    root = a.insert(root, 20);
    
    BNode kthLargest = a.getKthLargestElementBST(root, 5);
    
    if(kthLargest != null) {
      System.out.println(kthLargest.data);  
    } else {
      System.out.println("There're more values than given value");
    }
    
  }

}
