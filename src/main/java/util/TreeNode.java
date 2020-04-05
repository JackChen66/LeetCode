package util;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		super();
		this.value = value;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
