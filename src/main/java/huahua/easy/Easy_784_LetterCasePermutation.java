package huahua.easy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Easy_784_LetterCasePermutation {
	public List<String> letterCasePermutation(String s) {
		List<String> list = new ArrayList<String>();
		dfs(s.toCharArray(), 0, list);
		return list;
	}
	
	void dfs(char[] s, int i, List<String> list) {
		if (i == s.length) {
			list.add(new String(s));
			return;
		}
		
		dfs(s, i + 1, list);
		if (!Character.isLetter(s[i]))
			return;
		
		s[i] ^= (1 << 5);
		dfs(s, i + 1, list);
		s[i] ^= (1 << 5);
		
	}
	
	@Test
	public void test_1() {
		List<String> list = letterCasePermutation("ab");
		System.out.println(list.toString());
	}
}
