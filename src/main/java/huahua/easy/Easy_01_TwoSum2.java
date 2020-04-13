package huahua.easy;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import application.exception.TestFailException;
/**
 * Leetcode 1 Two Sum
 * Given an array of integers, return indices of the two number such that they 
 * add up to a specific target. 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * @author Jack
 *
 */
public class Easy_01_TwoSum2 {
	private int[] twoSum_s1(int[] sums, int target) {
		if (sums == null || sums.length < 2)
			return new int[2];
		
		for(int i = 0; i < sums.length; i++) {
			int new_target = target - sums[i];
			for(int j = i + 1; j < sums.length; j++) {
				if (new_target == sums[j]) {
					return new int[] {i, j};
				}
			}
		}
		return new int[2];
	}
	
	private int[] twoSum_s2(int[] sums, int target) {
		if (sums == null || sums.length < 2)
			return new int[2];
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < sums.length; i++) {
			map.put(sums[i], i);
		}
		
		for(int i = 0; i < sums.length; i++) {
			int new_target = target - sums[i];
			if (map.containsKey(new_target) && i != map.get(new_target))
				return new int[] {i, map.get(new_target)};
		}	
		return new int[2];
 	}
	
	
	public void test1() throws TestFailException {
		int[] sums = new int[] {2, 7, 10, 22};
		int target = 9; 
		int[] result = twoSum_s2(sums, target);
		if (0 != result[0] || 1 != result[1]) {
			fail++;
			throw new TestFailException("[0] expecte is 0 but result[0] is " + result[0] 
					+ ", [1] expect is 1 but result[1] is " + result[1]);
		} else {
			pass++;
		}
	}
	
	
	
	public void test2() throws TestFailException {
		int[] sums = new int[] {2, 10, 22, 9, 12, 7};
		int target = 9; 
		int[] result = twoSum_s2(sums, target);
		if (0 != result[0] || 5 != result[1]) {
			fail++;
			throw new TestFailException("[0] expecte is 0 but result[0] is " + result[0] 
					+ ", [1] expect is 5 but result[1] is " + result[1]);
		} else {
			pass++;
		}
	}
	
	
	
	public void test3() throws TestFailException {
		int[] sums = new int[10000002];
		sums[0] = 2;
		sums[sums.length - 1] = 7;
		Random r = new Random();
		for(int i = 1; i < sums.length - 2; i++)  {
			int random = Math.abs(r.nextInt() + 10);
			if (!Arrays.asList(sums).contains(random)) {
				sums[i] = random;
			}
			
		}
		int target = 9; 
		int[] result = twoSum_s1(sums, target);
		
		if (0 != result[0] || sums.length - 1 != result[1]) {
			fail++;
			throw new TestFailException("[0] expecte is 0 but result[0] is " + result[0] 
					+ ", [1] expect is " + (sums.length - 1) 
					+ " but result[1] is " + result[1]);
		} else {
			pass++;
		}
	}
	
	static int pass;
	static int fail;
	static int total;
	public static String errorMessage;
	public static void main(String[] args) {
		Easy_01_TwoSum2 e = new Easy_01_TwoSum2();
		try {
			e.test1();
		} catch (TestFailException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			e.test2();
		} catch (TestFailException e1) {
			System.out.println(e1.getMessage());
		}
		try {
			e.test3();
		} catch (TestFailException e1) {
			System.out.println(e1.getMessage());
		}
		total = pass + fail;
		System.out.println("total: " + total + ", pass: " + pass + ", fail" + fail);
		
	}
}
