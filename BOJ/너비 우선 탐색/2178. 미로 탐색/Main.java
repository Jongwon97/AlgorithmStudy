import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[][] maze;
	private static int N,M;
	
	private static int[] dr= {0,1,0,-1};
	private static int[] dc= {-1,0,1,0};
	

	public static void main(String args[]) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		// NxM 크기의 배열로 표현된 미로
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 미로 입력및 초기화
		maze=new int[N][M];
		for(int i=0;i<N;i++) {
			String str=in.readLine();
			for(int j=0;j<M;j++) {
				maze[i][j]=Integer.parseInt(str.charAt(j)+"");
			}
		}
		
		search_bfs();
		System.out.println(maze[N-1][M-1]);
	}
	
	private static void search_bfs() {
		Queue<int[]> queue=new ArrayDeque<>();
		boolean[][] visited=new boolean[N][M];
		visited[0][0]=true;	
		int[] position=new int[2]; // 0 인덱스: 행, 1 인덱스: 열
		queue.offer(position);
		
		int[] current=new int[2];
		while(!queue.isEmpty()) {
			current=queue.poll(); // 큐의 front에서 값을 꺼냄

			for(int i=0;i<4;i++) {
				int tr=current[0]+dr[i]; // 임시로 이동할 행 저장
				int tc=current[1]+dc[i]; // 임시로 이동할 열 저장
				if(tr<0||tr>=N||tc<0||tc>=M) continue;    // 미로의 범위를 넘어갈 경우 continue
				if(maze[tr][tc]!=0 && !visited[tr][tc]) { // 미로의 길이 있고, 방문 안했을 경우
					if(tr==N-1&&tc==M-1) { // 도착 위치에 도착했을 경우
						maze[tr][tc]=maze[current[0]][current[1]]+1;
						return;
					}
				    visited[tr][tc]=true; // 방문 처리
				    // 큐에 길이있는 위치 추가
					int[] temp={tr,tc};
					queue.offer(temp);
					// 지금까지 몇칸 왔는지 저장
					maze[tr][tc]=maze[current[0]][current[1]]+1;
				}		
			}
		}
	}
}