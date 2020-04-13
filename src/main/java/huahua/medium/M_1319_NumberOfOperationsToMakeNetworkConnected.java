package huahua.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M_1319_NumberOfOperationsToMakeNetworkConnected {
	static int[] p;
	public static int makeConnected(int n, List<int[]> connections) {
		if (connections.size() < n - 1) return -1;
		p = new int[n];
		for(int i = 0; i < n; i++)
			p[i] = i;
		printP();
		for(int[] c : connections) {
			p[find(c[0])] = find(c[1]);
			printP();
		}
		
		Set<Integer> s = new HashSet<Integer>();
		
		for(int i = 0; i < n; i++)
			s.add(find(i));
		
		return s.size() - 1;
	}
	
	private static int find(int x) {
		if (p[x] == x)
			return x;
		else  {
			p[x] = find(p[x]);
			return p[x];
		}
//		return p[x] == x ? x : p[x] = find(p[x]);
	}
	
	public static void main(String[] args) {
		List<int[]> connections = new ArrayList<int[]>();
		connections.add(new int[] {0, 1});
		connections.add(new int[] {1, 2});
		connections.add(new int[] {0, 2});
		int n = 4;
		System.out.println(makeConnected(n, connections));
	}
	
	private static void printP() {
		StringBuilder sb = new StringBuilder();
		for(int i : p) {
			sb.append(i + " ");
		}
		
		System.out.println(sb.toString());
	}
}
