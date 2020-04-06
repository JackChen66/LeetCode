package huahua.lecture;

import java.util.LinkedList;
import java.util.Queue;

import util.TreeNode;

/**
 * Lecture about BinarySearchTree from Huahua
 * @author Jack
 *
 */
public class BinarySearchTree {
	Queue<Integer> q = new LinkedList<Integer>();
	public TreeNode createBST(int[] nums) {
		TreeNode root = null;
		for(int n : nums) {
			root = insert(root, n);
		}
		return root;
	}
	
	public TreeNode insert(TreeNode root, int value) {
		if (root == null)
			return new TreeNode(value);
		
		if (value <= root.value) {
			root.left = insert(root.left, value);
		} else {
			root.right = insert(root.right, value);
		}
		return root;
	}
	
	public void inorder(TreeNode root) {
		if (root == null)
			return;
		inorder(root.left);
		q.add(root.value);
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		TreeNode root = bst.createBST(new int[] {5,3,1,4,6,7});
		bst.inorder(root);
		while(!bst.q.isEmpty()) {
			System.out.print(bst.q.poll() + " ");
		}
	}
}
