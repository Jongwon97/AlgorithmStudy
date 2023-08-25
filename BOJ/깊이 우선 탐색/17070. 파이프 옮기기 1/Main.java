import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	
	private static int[][] map;
	private static int answer=0;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine());
		
		// 맵 입력
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,1,0); // 시작위치 (0,1), 상태 0(파이프가 가로로 놓여있음)
		System.out.println(answer);
	}
	
	private static void dfs(int row, int col, int state) {
		if(row==N-1 && col==N-1) { // 파이프 목적지에 도착했을 때
			answer++;
			return;
		}
		// 파이프가 가로로 놓여있을 때
		if(state==0) {
			if(col+1<N && map[row][col+1]==0) {
				dfs(row, col+1, 0);  // 오른쪽으로 이동
			}
			if(col+1<N && row+1<N && map[row][col+1]==0 && map[row+1][col]==0 && map[row+1][col+1]==0) {
				dfs(row+1, col+1, 2); // 대각선으로 이동
			}
		}
		// 파이프가 세로로 놓여있있을 떄
		else if(state==1) {
			if(row+1<N && map[row+1][col]==0) {
				dfs(row+1, col, 1);  // 아래로 이동
			}
			if(col+1<N && row+1<N && map[row][col+1]==0 && map[row+1][col]==0 && map[row+1][col+1]==0) {
				dfs(row+1, col+1, 2); // 대각선으로 이동
			}
		}
		// 파이프가 대각선으로 놓여있을 때
		else if(state==2) {
			if(col+1<N && map[row][col+1]==0) {
				dfs(row, col+1, 0); // 오른쪽으로 이동
			}
			if(row+1<N && map[row+1][col]==0) {
				dfs(row+1, col, 1); // 아래로 이동
			}
			if(col+1<N && row+1<N && map[row][col+1]==0 && map[row+1][col]==0 && map[row+1][col+1]==0) {
				dfs(row+1, col+1, 2); // 대각선으로 이동
			}
		}
		
	}
}