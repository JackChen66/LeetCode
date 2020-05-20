package huahua.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_1202_SmallestStringWithSwaps {
	int[] p;
	public String smallestStringWithSwaps(String s, int[][] paris) {
		int n = s.length();
		p = new int[n];
		for(int i = 0; i < p.length; i++)
			p[i] = i;
		
		for(int[] e : paris) {
			p[find(e[0])] = find(e[1]);
		}
		
		
		List<ArrayList<Integer>> idx = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < n; i++) {
			idx.add(new ArrayList<Integer>());
		}
		StringBuilder[] ss = new StringBuilder[n];
		
		for(int i = 0; i < s.length(); i++) {
			int id = find(i);
			idx.get(id).add(i);
			if (ss[id] == null)
				ss[id] = new StringBuilder();
			ss[id].append(s.charAt(i));
		}
		
		char[] sc = s.toCharArray();
		for(int i = 0; i < n; i++) {
			if (ss[i] != null && ss.length > 0) {
				String str = ss[i].toString();
				char[] carr = str.toCharArray();
				Arrays.sort(carr);
				StringBuilder sbTemp = new StringBuilder(new String(carr));
				ss[i] = sbTemp;
			}
			for(int k = 0; k < idx.get(i).size(); k++) 
				sc[idx.get(i).get(k)] = ss[i].toString().charAt(k);
		}
		
		return new String(sc);
	}
	
	private int find(int x) {
		if (p[x] == x)
			return x; 
		else {
			p[x] = find(p[x]);
			return p[x];
		}
	}
	
	
	public static void main(String[] args) {
		M_1202_SmallestStringWithSwaps m = new M_1202_SmallestStringWithSwaps();
		
		String s = "dcab";
		int[][] pairs = {{0,3}, {1, 2}};
		
		System.out.println(m.smallestStringWithSwaps(s, pairs));
	}
	
	
}
