package huahua.easy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Leetcode 190 Revers Bits
 * Reverse bits of a given 32 bits unsigned integer
 * @author minghao
 *
 */
public class Easy_190_ReverseBits {
	public int reverseBits(int n) {
		int ans = 0; 
		for(int i = 0; i < 32; i++) {
			ans = (ans << 1) | (n & 1);
			n >>= 1;
		}
		return ans;
	}
	
	@Test
	public void test1() {
		assertEquals(964176192, reverseBits(43261596));
	}
	
	@Test
	public void test2() {
		assertEquals(0, reverseBits(0));
	}
	
//	@Test
//	public void test3() {
//		assertEquals(1, reverseBits(1));
//	}
//	
//	@Test
//	public void test4() {
//		assertEquals(1, reverseBits(2));
//	}
	
//	@Test
//	public void test2() {
//		assertEquals(3221225471, reverseBits(4294967293));
//	}
}
