import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int[][] graph;
	private static boolean[] checked_dfs;
	private static boolean[] checked_bfs;
	
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt(); 	// 정점의 개수
		int M=sc.nextInt();  	// 간선의 개수
		int V=sc.nextInt(); 	// 탐색을 시작할 정점의 번호
	
		// 노드가 1부터 시작하므로 0인덱스는 사용x
		graph=new int[N+1][N+1];
		checked_dfs=new boolean[N+1];
		checked_bfs=new boolean[N+1];
		
		// 간선 열결
		for(int i=0;i<M;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			graph[x][y]=1;
			graph[y][x]=1;
		}
		
		dfs(V,N);
		System.out.println();
		bfs(V,N);
	}
	
	// dfs
	private static void dfs(int V,int N) {
		checked_dfs[V]=true;
		System.out.print(V+" ");

		for(int i=1;i<=N;i++) {
			if(checked_dfs[i]==false && graph[V][i]==1){
				dfs(i,N);
			}
		}
	}
	// bfs
	private static void bfs(int V,int N) {
		Queue<Integer> queue=new LinkedList<>();
		queue.add(V);
		checked_bfs[V]=true;
		while(true) {
			int temp=queue.poll(); 			// 큐 front값 반환 후 삭제
			System.out.print(temp+" ");		// 반환값 출력
			for(int i=1;i<=N;i++) {				
				// 방문하지 않았고 간선이 연결되어 있으면, 큐에 추가
				if(checked_bfs[i]==false&&graph[temp][i]==1) {
					queue.add(i);		  				
					checked_bfs[i]=true;
				}
			}
			// 큐가 비어있게 되면 종료
			if(queue.isEmpty()) {
				break;
			}
			
		}		
	}
}
