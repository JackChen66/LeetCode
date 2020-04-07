package huahua.easy;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class Easy_973_K_ClosestPointsToOrigin {
	class Pair implements Comparable<Pair>{
		int key;
		int value;
		
		public Pair(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int compareTo(Pair o) {
			if (this.key < o.key)
				return -1;
			else if (this.key > o.key)
				return 1;
			else if (this.value < o.value)
				return -1;
			else if (this.value > o.value)
				return 1;
			else 
				return 0;
		}
	}
	
	public int[][] KCloset(int[][] points, int k) {
		List<Pair> toSort = 
				new ArrayList<Pair>();
		
		for(int i = 0; i < points.length; i++) {
			toSort.add(new Pair(points[i][0] * points[i][0] 
					+ points[i][1] * points[i][1], i));
		}
		
		Collections.sort(toSort);
		
		int[][] result = new int[k][2];
		for(int i = 0; i < k; i++) {
			result[i] = points[toSort.get(i).value];
		}
		return result;
	}
	
	@Test
	public void test1() {
		int[][] test = new int[][] {{1,3}, {-2, 2}};
		
		int[][] result = KCloset(test, 1);
		
		assertTrue(result.length == 1);
		assertTrue(result[0][0] == -2);
		assertTrue(result[0][1] == 2);
	}
	
	@Test
	public void test2() {
		int[][] test = new int[][] {{3,3}, {5, -1}, {-2, 4}};
		
		int[][] result = KCloset(test, 2);
		
		assertTrue(result.length == 2);
		assertTrue(result[0][0] == -2 || result[0][0] == 3);
		if (result[0][0] == -2)
			assertTrue(result[0][1] == 4);
		else if (result[0][0] == 3)
			assertTrue(result[0][1] == 3);
	}
}
