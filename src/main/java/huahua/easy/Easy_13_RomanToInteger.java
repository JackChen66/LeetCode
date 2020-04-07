package huahua.easy;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Easy_13_RomanToInteger {
	Map<Character, Integer> map = 
			new HashMap<Character, Integer>(){{
				put('I', 1);
				put('V', 5);
				put('X', 10);
				put('L', 50);
				put('C', 100);
				put('D', 500);
				put('M', 1000);
			}};
			
	public int romanToInteger(String roman) {
		if (roman == null || roman.length() == 0)
			return 0;
		
		char prev = roman.charAt(0);
		int ans = map.get(prev);
		for(int i = 1; i < roman.length(); i++) {
			char c = roman.charAt(i);
			ans += map.get(c);
			if (map.get(prev) < map.get(c)) {
				ans = ans - 2 * map.get(prev);
			}
			prev = c;
		}
		return ans;
	}
	
	@Test
	public void Test1() {
		assertEquals(1, romanToInteger("I"));
	}
	
	@Test
	public void Test2() {
		assertEquals(2, romanToInteger("II"));
	}
	
	@Test
	public void Test3() {
		assertEquals(3, romanToInteger("III"));
	}
	
	@Test
	public void Test4() {
		assertEquals(4, romanToInteger("IV"));
	}
	
	@Test
	public void Test5() {
		assertEquals(5, romanToInteger("V"));
	}
	
	@Test
	public void Test6() {
		assertEquals(6, romanToInteger("VI"));
	}
	
	@Test
	public void Test7() {
		assertEquals(7, romanToInteger("VII"));
	}
	
	@Test
	public void Test8() {
		assertEquals(8, romanToInteger("VIII"));
	}
	
	@Test
	public void Test9() {
		assertEquals(9, romanToInteger("IX"));
	}
	
	@Test
	public void Test10() {
		assertEquals(10, romanToInteger("X"));
	}
	
	@Test
	public void Test11() {
		assertEquals(11, romanToInteger("XI"));
	}
	
	@Test
	public void Test12() {
		assertEquals(12, romanToInteger("XII"));
	}
	
	@Test
	public void Test13() {
		assertEquals(58, romanToInteger("LVIII"));
	}
	
	@Test
	public void Test14() {
		assertEquals(1994, romanToInteger("MCMXCIV"));
	}
	
}
