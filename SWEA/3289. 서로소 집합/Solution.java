import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int n,m;
	static int[] parents;
	
	// 모든 원소를 각각 단위 집합으로 만듬
	static void makeSet() {
		parents=new int[n+1];
		for(int i=1;i<=n;i++) {
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
	
	public static void main(String args[]) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			n=Integer.parseInt(st.nextToken()); // 집합의 개수
			m=Integer.parseInt(st.nextToken()); // 연산의 개수
			makeSet();
			
			StringBuilder sb=new StringBuilder();
			
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(in.readLine()," ");
				int temp=Integer.parseInt(st.nextToken());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				// 합집합(a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합침)
				if(temp==0) {
					union(a,b);
				}
				// 같은 집합에 포함되어 있는지 확인하는 연산
				if(temp==1) {
					if(findSet(a)==findSet(b)) {
						sb.append("1");
					}
					else {
						sb.append("0");
					}
				}
			}
			
			System.out.println("#"+test_case+" "+sb);
			sb.setLength(0);
		}
	}
}