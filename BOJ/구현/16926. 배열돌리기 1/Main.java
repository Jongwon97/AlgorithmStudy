import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException{
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken());  	// 배열의 크기 N
		int M=Integer.parseInt(st.nextToken()); 	// 배열의 크기 M
		int R=Integer.parseInt(st.nextToken()); 	// 회전의 수
		
		// 배열 입력
		int[][] arr=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<M;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		// 루프 수를 계산하기위해
		int min=Math.min(N,M);
		
		// 회전 방향
		int[] dx= {1,0,-1,0};
		int[] dy= {0,1,0,-1};

		// 회전 수 만큼 반복
		for(int i=0;i<R;i++) {
			boolean[][] isVisited=new boolean[N][M];
			int x=0; // 시작 좌표 x
			int y=0; // 시작 좌표 y
			int d_index=0;
			
			
			// 루프수만큼 반복
			for(int j=0;j<min/2;j++) {
				int temp=arr[x][y];
				while(true) {
					// 방향 전환
					if(x+dx[d_index]<0||x+dx[d_index]>=N||y+dy[d_index]<0||y+dy[d_index]>=M||isVisited[x+dx[d_index]][y+dy[d_index]]==true) {
						d_index=d_index+1;
						if(d_index==4) { // 한 바퀴 돌았으면 다음 루프로 넘어감
							x++;
							y++;
							d_index=0;
							break;
						}
					}
					// 좌표 이동
					x+=dx[d_index];
					y+=dy[d_index];
					// 값 변경
					int temp2=arr[x][y];
					arr[x][y]=temp;
					temp=temp2;
					// 방문 처리
					isVisited[x][y]=true;
				}
			}
		}
		
		// 출력
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}