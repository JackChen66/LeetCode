package application.answer.running;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
public class solution {
	public int[] answer(int[] sums, int target) {
		return null;
	}
 
	@Test
	public void test1() {
		int[] sums = new int[] {2, 7, 10, 22};
		int target = 9; 
		int[] result = answer(sums, target);
		assertEquals(0, result[0]);
		assertEquals(1, result[1]);
	}
	
	
	@Test
	public void test2() {
		int[] sums = new int[] {2, 10, 22, 9, 12, 7};
		int target = 9; 
		int[] result = answer(sums, target);
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
		}
		int target = 9; 
		
		int[] result = answer(sums, target);
		assertEquals(0, result[0]);
		assertEquals(sums.length - 1, result[1]);
	}
	
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		junit.run(solution.class);
	}
}