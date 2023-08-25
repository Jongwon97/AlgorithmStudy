import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이전에 그린 선분을 다시 그을 수 없다.
 * C에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 출발점으로 되돌아올 수 있다.
 * -> 따라서, 이미 같은 집합에 속한 점들을 포함하는 선분이 추가될 경우 사이클이 생성된다.
 */

public class Main {

	static int N,M;
	static int[] parents;
	
	static void makeSet() { //모든 원소를 각각의 단위집합으로 만듬(크기가 1인 집합)
		parents=new int[N];
		for(int i=0;i<N;i++) {
			parents[i]=-1; // 자기자신을 자신의 집합의 대표자로
		}
	}
	
	static int findSet(int a) {
		if(parents[a]<0) return a;
		return parents[a]=findSet(parents[a]); // path compression
	}
	
	static boolean union(int a, int b) {
		int aRoot=findSet(a);
		int bRoot=findSet(b);
		if(aRoot==bRoot) return false;
		
		parents[aRoot]+=parents[bRoot];
		parents[bRoot]=aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 점의 개수
		M=Integer.parseInt(st.nextToken()); // 진행된 차례의 수
		
		makeSet();
		
		int a,b;
		int answer=0;
		// 인덱스가 순서 저장
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(in.readLine()," ");
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			if(!union(a,b)) {
				answer=i+1;
				break;
			}
		}
		
		System.out.println(answer);
	}
}
