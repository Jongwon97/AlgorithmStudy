import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static int N,M;
	static int[] parents;
	
	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			N=Integer.parseInt(st.nextToken()); // 사람의 수
			M=Integer.parseInt(st.nextToken()); //  사람의 관계 수
			makeSet();
			
			for(int i=0;i<M;i++) {
				st=new StringTokenizer(in.readLine()," ");
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				union(a,b);
			}
			for(int i=1;i<=N;i++) {
				findSet(i);
			}
			Set<Integer> set=new HashSet<>();
			for(int i=1;i<=N;i++) {
				set.add(parents[i]);
			}
			System.out.println("#"+test_case+" "+set.size());
		}
	}
	// 모든 원소를 각각 단위 집합으로 만듬
	static void makeSet() {
		parents=new int[N+1];
		for(int i=1;i<=N;i++) {
			parents[i]=i; // 자기자신을 자신의 집합의 대표자로
		}
	}
	
	static int findSet(int a) {
		if(a==parents[a]) return a; // 부모가 자기 자신일 경우
		return parents[a]=findSet(parents[a]); // path compression
	}
	static boolean union(int a, int b) {
		int aRoot=findSet(a);
		int bRoot=findSet(b);
		if(aRoot==bRoot) return false;
		
		parents[bRoot]=aRoot;
		return true;
	}
}