package application.answer.running;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
public class solution {
	public int[] answer(int[] sums, int target) {
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
 
public void test1() {
		int[] sums = new int[] {2, 7, 10, 22};
		int target = 9; 
		int[] result = answer(sums, target);
		if (result == null){
			fail++;
			System.out.println("Output Result is null");
			return;
		}
		if (0 != result[0] || 1 != result[1]) {
			fail++;
			System.out.println("[0] expecte is 0 but result[0] is " + result[0] 
					+ ", [1] expect is 1 but result[1] is " + result[1]);
		} else {
			pass++;
		}
	}
	
	
	
	public void test2() {
		int[] sums = new int[] {2, 10, 22, 9, 12, 7};
		int target = 9; 
		int[] result = answer(sums, target);
		if (result == null){
			fail++;
			System.out.println("Output Result is null");
			return;
		}
		if (0 != result[0] || 5 != result[1]) {
			fail++;
			System.out.println("[0] expecte is 0 but result[0] is " + result[0] 
					+ ", [1] expect is 5 but result[1] is " + result[1]);
		} else {
			pass++;
		}
	}
	
	
	
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
		if (result == null){
			fail++;
			System.out.println("Output Result is null");
			return;
		}
		if (0 != result[0] || sums.length - 1 != result[1]) {
			fail++;
			System.out.println("[0] expecte is 0 but result[0] is " + result[0] 
					+ ", [1] expect is " + (sums.length - 1) 
					+ " but result[1] is " + result[1]);
		} else {
			pass++;
		}
	}
	
	static int pass = 0;
	static int fail = 0;
	static int total = 0;
	public static String errorMessage;
	public static void main(String[] args) {
		solution e = new solution();
		e.test1();
		e.test2();
		e.test3();
		total = pass + fail;
		System.out.println("total: " + total + ", pass: " + pass + ", fail: " + fail);
		
	}}