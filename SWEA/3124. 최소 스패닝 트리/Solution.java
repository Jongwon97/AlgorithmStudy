import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static class Edge implements Comparable<Edge>{
		int from,to,weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
	}
	static Edge[] edgeList;
	static int[] parents;
	static int V,E;
	
	static void makeSet() {
		parents=new int[V+1];
		for(int i=1;i<=V;i++) {
			parents[i]=i;
		}
	}
	static int findSet(int a) {
		if(a==parents[a]) return a;
		return parents[a]=findSet(parents[a]);
	}
	static boolean union(int a,int b) {
		int aRoot=findSet(a);
		int bRoot=findSet(b);
		if(aRoot==bRoot) return false;
		parents[bRoot]=aRoot;
		return true;
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++){
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			edgeList=new Edge[E];
			
			for(int i=0;i<E;i++) {
				st=new StringTokenizer(in.readLine()," ");
				int A=Integer.parseInt(st.nextToken()); // 정점 A
				int B=Integer.parseInt(st.nextToken()); // 정점 B
				int C=Integer.parseInt(st.nextToken()); // 가중치 C
				edgeList[i]=new Edge(A,B,C);
			}
			Arrays.sort(edgeList); // 가중치 기준으로 오름차순 정렬

			makeSet();
			long result=0, count=0;
			
			for(Edge edge: edgeList) {
				if(union(edge.from, edge.to)) {
					result+=edge.weight;
					if(++count==V-1) break;
				}
			}
			System.out.println("#"+test_case+" "+result);
			}
		}
}