import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int answer;
	private static boolean[] visited;
	private static int[][] computer;
	
	private static int N;
	
	public static void main(String args[]) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine()); 		// 컴퓨터의 수
		int M=Integer.parseInt(in.readLine()); 	// 서로 연결된 컴퓨터의 번호 쌍
		computer=new int[N+1][N+1]; 			// 문제에서 번호는 1부터 시작하므로, 0행과 0열은 사용 x
		
		for(int i=0;i<M;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			computer[from][to]=1;
			computer[to][from]=1;
		}
		
		visited=new boolean[N+1];
		dfs(1);
		System.out.println(answer);
	}
	
	private static void dfs(int cur) {
		visited[cur]=true; // 현재 노드 방문 처리
		for(int i=1;i<=N;i++) {
			// 방문 안한 노드 이고, 서로 연결되었을 경우
			if(!visited[i]&&computer[cur][i]==1) {
				answer++;
				dfs(i);
			}
		}
	}
}