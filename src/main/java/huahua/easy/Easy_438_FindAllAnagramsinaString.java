package huahua.easy;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Easy_438_FindAllAnagramsinaString {
	public int[] findAnagram(String s, String p) {
		int n = s.length();
		int l = p.length();
		int[] arrS = new int[26];
		int[] arrP = new int[26];
		List<Integer> listResult = new ArrayList<Integer>();
		
		for(int i = 0; i < l; i++) {
			arrP[p.charAt(i) - 'a'] ++; 
		}
		
		for(int i = 0; i < n; i++) {
			arrS[s.charAt(i) - 'a'] ++;

			if (i >= l) 
				arrS[s.charAt(i - l) - 'a']--;
			
			
			if (Arrays.equals(arrP, arrS)) {
				listResult.add(i + 1 - l);
			}			
		}
		
		int[] arrResult = new int[listResult.size()];
		for(int i = 0; i < listResult.size(); i++) 
			arrResult[i] = listResult.get(i);
		
		return arrResult;
	}
	
	@Test
	public void test1() {
		int[] a = findAnagram("cbaebabacd", "abc");
		int[] e = {0, 6};
		assertTrue(Arrays.equals(a, e));
	}
	
	@Test
	public void test2() {
		int[] a = findAnagram("a", "a");
		int[] e = {0};
		assertTrue(Arrays.equals(a, e));
	}
	
	@Test
	public void test3() {
		int[] a = findAnagram("abc", "d");
		int[] e = {};
		assertTrue(Arrays.equals(a, e));
	}
	
	@Test
	public void test4() {
		int[] a = findAnagram("fooabcbacasdffoobadasdoof", "foo");
		int[] e = {0, 13, 22};
		assertTrue(Arrays.equals(a, e));
	}
}
