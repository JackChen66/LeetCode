package huahua.easy;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
/**
 * Leetcode 1 Two Sum
 * Given an array of integers, return indices of the two number such that they 
 * add up to a specific target. 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * @author Jack
 *
 */
public class Easy_01_TwoSum {
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
	
	@Test
	public void test1() {
		int[] sums = new int[] {2, 7, 10, 22};
		int target = 9; 
		int[] result = twoSum_s2(sums, target);
		assertEquals(0, result[0]);
		assertEquals(1, result[1]);
	}
	
	
	@Test
	public void test2() {
		int[] sums = new int[] {2, 10, 22, 9, 12, 7};
		int target = 9; 
		int[] result = twoSum_s2(sums, target);
		assertEquals(0, result[0]);
		assertEquals(5, result[1]);
	}
	
	
	@Test
	public void test3() {
		int[] sums = new int[10000002];
		sums[0] = 2;
		sums[sums.length - 1] = 7;
		Random r = new Random();
		for(int i = 1; i < sums.length - 2; i++)  {
			int random = Math.abs(r.nextInt() + 10);
			if (!Arrays.asList(sums).contains(random)) {
				sums[i] = random;
			}
			System.out.println(random);
		}
		int target = 9; 
		long startTime = System.nanoTime();
		int[] result = twoSum_s1(sums, target);
		long endTime = System.nanoTime();
		
		long totalTime = endTime - startTime;
		double seconds = (double)totalTime / 1000000000.00;
		System.out.println("total time: " + seconds);
		assertEquals(0, result[0]);
		assertEquals(sums.length - 1, result[1]);
	}
}
