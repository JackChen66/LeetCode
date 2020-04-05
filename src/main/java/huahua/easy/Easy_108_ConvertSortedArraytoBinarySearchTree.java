package huahua.easy;

import java.awt.FontFormatException;

import org.junit.Test;

import util.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, 
 * convert it to a height balanced BST. 
 * 
 * For this problem, a height-balanced binary tree is defined as a binary 
 * tree in which the depth of the two subtrees of every node never differ 
 * by more than 1
 * 
 * @author Jack
 *
 */
public class Easy_108_ConvertSortedArraytoBinarySearchTree {
	TreeNode sortedArrayToBST(int[] nums) {
		return build(nums, 0, nums.length - 1);
	}
	
	private TreeNode build(int[] nums, int l, int r) {
		if (l > r)
			return null;
		int m = l + (r - l) / 2;
		TreeNode root = new TreeNode(nums[m]);
		root.setLeft(build(nums, l, m - 1));
		root.setRight(build(nums, m + 1, r));
		return root;
	}
	
	@Test
	public void test1 () {
		int[] nums = new int[] {1, 2, 3, 4, 5};
		TreeNode root = sortedArrayToBST(nums);
		System.out.println();
	}
}
