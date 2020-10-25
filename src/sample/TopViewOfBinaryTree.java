package sample;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBinaryTree {

	private NodeForTopView rootNode = null;
	private Map<Integer, NodeForTopView> map = new TreeMap<Integer, NodeForTopView>();

	public static void main(String[] args) {
		new TopViewOfBinaryTree();
	}

	public TopViewOfBinaryTree() {
		addNode(rootNode, 10);
		addNode(rootNode, 5);
		addNode(rootNode, 20);
		addNode(rootNode, 6);
		addNode(rootNode, 7);
		addNode(rootNode, 8);

		printTopView(rootNode);

		for (Map.Entry<Integer, NodeForTopView> element : map.entrySet()) {
			System.out.print(((NodeForTopView) element.getValue()).getData() + " ");
		}
	}

	private void printTopView(NodeForTopView rootNode) {

		if (rootNode == null)
			return;

		Queue<NodeForTopView> queue = new LinkedList<NodeForTopView>();
		queue.add(rootNode);

		while (!queue.isEmpty()) {
			NodeForTopView nodeForTopView = (NodeForTopView) queue.poll();

			int currentLevel = nodeForTopView.getLevel();
			if (map.get(nodeForTopView.getLevel()) == null) {
				map.put(nodeForTopView.getLevel(), nodeForTopView);
			}

			if (nodeForTopView.getLeftNode() != null) {
				nodeForTopView.getLeftNode().setLevel(currentLevel - 1);
				queue.add(nodeForTopView.getLeftNode());
			}

			if (nodeForTopView.getRightNode() != null) {
				nodeForTopView.getRightNode().setLevel(currentLevel + 1);
				queue.add(nodeForTopView.getRightNode());
			}
		}

	}

	private void addNode(NodeForTopView rootNode, int data) {
		if (rootNode == null) {
			NodeForTopView temp1 = new NodeForTopView(data);
			this.rootNode = temp1;
		} else {
			addNodeInProperPlace(rootNode, data);
		}
	}

	private void addNodeInProperPlace(NodeForTopView rootNode, int data) {
		if (data > rootNode.getData()) {
			if (rootNode.getRightNode() != null) {
				addNode(rootNode.getRightNode(), data);
			} else {
				NodeForTopView temp1 = new NodeForTopView(data);
				rootNode.setRightNode(temp1);
			}
		} else if (data < rootNode.getData()) {
			if (rootNode.getLeftNode() != null) {
				addNode(rootNode.getLeftNode(), data);
			} else {
				NodeForTopView temp1 = new NodeForTopView(data);
				rootNode.setLeftNode(temp1);
			}
		}
	}

}

class NodeForTopView {

	private int data;
	private NodeForTopView leftNode;
	private NodeForTopView rightNode;
	private int level;

	public NodeForTopView(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public NodeForTopView getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(NodeForTopView leftNode) {
		this.leftNode = leftNode;
	}

	public NodeForTopView getRightNode() {
		return rightNode;
	}

	public void setRightNode(NodeForTopView rightNode) {
		this.rightNode = rightNode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
