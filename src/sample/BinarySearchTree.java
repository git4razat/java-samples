package sample;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinarySearchTree {

	BSTNode root;

	public BinarySearchTree() {
		root = null;
	}

	public BSTNode insertRec(BSTNode root, int data) {
		BSTNode node = new BSTNode(data);
		if (root == null) {
			root = node;
			return root;
		}
		if (data <= root.data) {
			root.left = insertRec(root.left, data);
		} else {
			root.right = insertRec(root.right, data);
		}
		return root;
	}

	public void insert(int data) {
		root = insertRec(root, data);
	}

	public BSTNode search(BSTNode root, int key) {

		if (root == null) {
			return root;
		}

		if (root.data == key) {
			return root;
		} else if (root.data > key) {
			return search(root.left, key);
		} else if (root.data < key) {
			return search(root.right, key);
		} else {
			return null;
		}
	}
	
	public int heightOfTree(BSTNode root) {
		if (root == null) {
			return -1;
		}

		int lHeight = heightOfTree(root.left);
		int rHeight = heightOfTree(root.right);

		if (lHeight > rHeight) {
			return lHeight + 1;
		} else {
			return rHeight + 1;
		}
	}

	public void printInorderTrav(BSTNode root) {
		if (root == null) {
			return;
		}
		printInorderTrav(root.left);
		System.out.println(root.data);
		printInorderTrav(root.right);
	}

	// lowest common ancestor
	public BSTNode lca(BSTNode root, int a, int b) {
		if (root.data > a && root.data > b) {
			return lca(root.left, a, b);
		} else if (root.data < a && root.data < b) {
			return lca(root.right, a, b);
		} else {
			return root;
		}
	}

	boolean checkBST(BSTNode root) {
		return checkBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean checkBSTUtil(BSTNode root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.data < min || root.data > max) {
			return false;
		}

		if (checkBSTUtil(root.left, min, root.data - 1) && checkBSTUtil(root.right, root.data + 1, max)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Level Order Traversal or Breadth First Search - starting from root, put root in to queue and iterate over queue, and print each node and include
	// children of that node into queue. No recursion required...
	 

	public static void levelOrder(BSTNode root) {
		java.util.LinkedList<BSTNode> queue = new java.util.LinkedList<BSTNode>();
		if (root != null)
			queue.add(root);
		while (queue.peekFirst() != null) {
			BSTNode node = queue.poll();
			System.out.print(node.data + " ");
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}

	}

	// side view - https://www.techiedelight.com/print-left-view-of-binary-tree/
	// side view (left or right) takes approach of looping elements at each level
	// inside Level Order Traversal and
	// take first or last element ; based on left or right side view
	// where as top or bottom view focus on hd i.e. horizontal distance for each
	// element and process
	// first or last element at each hd (for top or bottom view respectively)
	public static void topView(BSTNode root) {
		// also check right/left side view and bottom view of a bst

		class QNode {
			BSTNode node;
			int hd;

			public QNode(BSTNode node, int hd) {
				this.node = node;
				this.hd = hd;
			}
		}

		java.util.LinkedList<QNode> queue = new java.util.LinkedList<QNode>();
		java.util.Map<Integer, Integer> map = new java.util.HashMap<Integer, Integer>();
		if (root == null) {
			return;
		}
		queue.add(new QNode(root, 0));
		while (queue.peekFirst() != null) {
			QNode qNode = queue.poll();

			if (!map.containsKey(qNode.hd)) { // for bottom view this if check will not be there, map will always be
												// updated
				map.put(qNode.hd, qNode.node.data);
			}

			if (qNode.node.left != null) {
				queue.add(new QNode(qNode.node.left, qNode.hd - 1));
			}
			if (qNode.node.right != null) {
				queue.add(new QNode(qNode.node.right, qNode.hd + 1));
			}
		}

		for (Integer key : map.keySet()) {
			System.out.print(map.get(key) + " ");
		}
	}

	// side view (left or right) takes approach of looping elements at each level
	// inside Level Order Traversal and
	// take first or last element ; based on left or right side view
	// where as top or bottom view focus on hd i.e. horizontal distance for each
	// element and process
	// first or last element at each hd (for top or bottom view respectively)
	public static void leftView(BSTNode root) {
		// return if tree is empty
		if (root == null) {
			return;
		}

		// create an empty queue and enqueue root node
		Queue<BSTNode> queue = new ArrayDeque<>();
		queue.add(root);

		// pointer to store current node
		BSTNode curr;

		// loop till queue is empty
		while (!queue.isEmpty()) {
			// calculate number of nodes in current level
			int size = queue.size();
			int i = 0;

			// process every node of current level and enqueue their
			// non-empty left and right child to queue
			while (i++ < size) {
				curr = queue.poll();

				// if this is first node of current level, print it
				if (i == 1) {
					System.out.print(curr.data + " ");
				}

				if (curr.left != null) {
					queue.add(curr.left);
				}

				if (curr.right != null) {
					queue.add(curr.right);
				}
			}
		}
	}

	// post order and then swap.
	// post order is left right root (processing)
	public BSTNode invert(BSTNode root) {
		if (root == null) {
			return root;
		}
		BSTNode leftNode = invert(root.left);
		BSTNode rightNode = invert(root.right);
		root.left = rightNode;
		root.right = leftNode;
		return root;
	}

	// any path in the tree from root to a leaf node has sum equals to input number
	// if yes, return true else false
	// post order processing
	public boolean hasPathSum(BSTNode root, int sum) {
		if (root == null) {
			return sum == 0;
		}
		return (hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data));
	}

	// https://www.youtube.com/watch?v=TO5zsKtc1Ic
	// post order processing
	public int maxPathSum(BSTNode root) {
		int res = Integer.MIN_VALUE;
		helper(root, res);
		return res;
	}

	private int helper(BSTNode root, int res) {
		if (root == null)
			return 0;
		int left = helper(root.left, res);
		int right = helper(root.right, res);
		int max_single = Math.max((Math.max(left, right) + root.data), root.data);
		int max_top = Math.max(max_single, left + right + root.data);
		res = Math.max(max_top, res);
		return max_single;
	}

	void deleteKey(int key) {
		root = deleteRec(root, key);
	}

	BSTNode deleteRec(BSTNode root, int key) {
		/* Base Case: If the tree is empty */
		if (root == null)
			return root;

		/* Otherwise, recur down the tree */
		if (key < root.data)
			root.left = deleteRec(root.left, key);
		else if (key > root.data)
			root.right = deleteRec(root.right, key);

		// if key is same as root's key, then This is the node
		// to be deleted
		else {
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// node with two children: Get the inorder successor (smallest
			// in the right subtree)
			root.data = minValue(root.right);

			// Delete the inorder successor
			root.right = deleteRec(root.right, root.data);
		}

		return root;
	}

	int minValue(BSTNode root) {
		BSTNode current = root;
		/* loop down to find the leftmost leaf */
		while (current.left != null) {
			current = current.left;
		}
		return (current.data);
	}
	
	int maxValue(BSTNode root) {
		BSTNode current = root;
		/* loop down to find the rightmost leaf */
		while (current.right != null) {
			current = current.right;
		}
		return (current.data);
	}

	/**
	 * Closest possible value in a BST - Recursive Time complexity - o(h) h - height
	 * of binary search tree
	 */
	int result;
	int min = Integer.MAX_VALUE;

	public int closestValue(BSTNode root, int target) {
		closestValueHelper(root, target);
		return result;
	}

	public void closestValueHelper(BSTNode root, int target) {
		if (root == null)
			return;

		if ((root.data - target) < min) {
			min = root.data - target;
			result = root.data;
		}

		if (target < root.data) {
			closestValueHelper(root.left, target);
		} else {
			closestValueHelper(root.right, target);
		}
	}
	
	
	/**
	 * Alternate approach
	 * @param root
	 * @param target
	 * @return
	 */
	public int closestValue(BSTNode root, double target) {
		if (root.data > target && root.left != null) {
			int l = closestValue(root.left, target);
			if (Math.abs(l - target) < Math.abs(root.data - target))
				return l;
			return root.data;
		}
		if (root.data < target && root.right != null) {
			int r = closestValue(root.right, target);
			if (Math.abs(r - target) < Math.abs(root.data - target))
				return r;
			return root.data;
		}
		return root.data;
	}

	/**
	 * Closest possible value in a BST - Iterative Time complexity - o(h) h - height
	 * of binary search tree
	 */

	public int closestValueIterative(BSTNode root, int target) {
		int min = Integer.MAX_VALUE;
		int result = root.data;

		while (root != null) {
			if (target > root.data) {
				int diff = root.data - target;
				if (diff < min) {
					min = Math.min(min, diff);
					result = root.data;
				}
				root = root.right;
			} else if (target < root.data) {

				int diff = root.data - target;
				if (diff < min) {
					min = Math.min(min, diff);
					result = root.data;
				}
				root = root.left;
			} else {
				return root.data;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(50);
		tree.insert(40);
		tree.insert(60);
		tree.insert(30);
		tree.insert(70);
		tree.insert(20);
		tree.insert(90);
		tree.printInorderTrav(tree.root);
		tree.levelOrder(tree.root);
		System.out.println("Root Node::" + tree.root.data);
		BSTNode node = tree.search(tree.root, 60);
		System.out.println("search output ::" + node);

		System.out.println(tree.heightOfTree(tree.root));

		System.out.println("LCA:: 60&90::" + tree.lca(tree.root, 90, 20).data);
	}

}

class BSTNode {
	int data;
	BSTNode left;
	BSTNode right;

	public BSTNode(int data) {
		this.data = data;
	}
}
