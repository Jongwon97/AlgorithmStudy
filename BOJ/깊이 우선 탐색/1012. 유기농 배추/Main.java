import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int[][] cabbage_field;
	private static boolean[][] visited;
	private static int[] dx= {-1,1,0,0};
	private static int[] dy= {0,0,-1,1};
	private static int M,N,K;
	
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);

		int T=sc.nextInt();	// 테스트 케이스수

		int[] answer=new int[T];
		
		for(int test_case=0;test_case<T;test_case++) {
			M=sc.nextInt();			// 배추밭의 가로길이
			N=sc.nextInt(); 		// 배추밭의 세로길이
			int K=sc.nextInt(); 	// 배추가 심어져 있는 위치의 개수
			
			cabbage_field=new int[M][N];
			visited=new boolean[M][N];
			
			// 배추 위치 입력
			for(int i=0;i<K;i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				cabbage_field[x][y]=1;
			}
			
			for(int i=0;i<M;i++) {
				for(int j=0;j<N;j++) {
					// 해당 위치에 배추가 심어져 있고, 방문하지 않은 경우
					if(!visited[i][j]&&cabbage_field[i][j]==1) {
						dfs(i,j);
						answer[test_case]++;
					}
				}
			}
		}

		// 출력
		for(int i=0;i<answer.length;i++) {
			System.out.println(answer[i]);
		}
		
	}
	
	// dfs
	private static void dfs(int i,int j) {
		visited[i][j]=true;

		for(int d=0;d<4;d++) {
			int nx=i+dx[d];
			int ny=j+dy[d];
			if(nx<0||nx>=M|| ny<0 ||ny>=N)	continue;  // 범위를 벗어날 경우 아래줄 생략
			if(cabbage_field[nx][ny]==1&&!visited[nx][ny]) { // 해당 좌표에서 십자가 모양으로 탐색
				dfs(nx,ny);
			}
		}
	}
}
