import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 0은 빈칸, 1은 벽, 2는 바이러스가 있는 곳
 * 벽의 개수는 3개
 * 안전 영역의 최대 크기를 출력
 * 
 *  1. 벽 3개 배치하기 -> 조합 사용(빈칸 0일 경우만 선택)
 *	2. 벽 3개가 배치된 상태에서 바이러스 퍼뜨기리 -> bfs
 *	3. 빈칸 개수 체크 -> 안전영역이 최대일 경우 교체
 */
public class Main {

	static int N,M;
	static int[] map_1;          	// 맵(연구소) 1차원으로 저장
	static int[][] map_2;           // 맵(연구소) 2차원으로 저장
	static int[][] infected_map;    // 바이러스에 감염된 맵
	
	static int[][] bar_index; 	// 기둥 3개를 설치할 위치
	static int[] nums;			// 기둥을 설치할 위치를 1차원으로 입력받을 배열
	
	// bfs 변수
	static int[] dr= {0,-1,0,1};
	static int[] dc= {-1,0,1,0};
	static boolean[][] visited;
	
	static int max=0;
	
	public static void main(String[] args) throws IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		N=Integer.parseInt(st.nextToken()); // 		지도의 세로 크기
		M=Integer.parseInt(st.nextToken()); //      가로 크기
		map_1=new int[N*M];
		map_2=new int[N][M];
		
		// 맵 초기화 -> 1차원,2차원으로 저장
		int cnt=0;
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<M;j++) {
				int temp=Integer.parseInt(st.nextToken());
				map_1[cnt]=temp;
				map_2[i][j]=temp;
				cnt++;
			}
		}
		
		nums=new int[3];
		bar_index=new int[3][2]; // 0열은 row, 1열은 col 저장
		combination(0,0);

		System.out.println(max);
	}
	
	// 벽 3개 배치하기 -> 조합 사용(빈칸 0일 경우만 선택)
	private static void combination(int cnt,int start) {
		if(cnt==3) {
			for(int i=0;i<3;i++) {
				bar_index[i][0]=nums[i]/M; // 벽을 설치할 행
				bar_index[i][1]=nums[i]%M; // 벽을 설치할 열
			}
			install_bar();
			return;
		}
		for(int i=start;i<N*M;i++) {
			if(map_1[i]==0) {
				nums[cnt]=i;
				combination(cnt+1,i+1);
			}
		}
	}
	
	// 기둥 설치
	private static void install_bar() {
		// 맵 복사
		infected_map=new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				infected_map[i][j]=map_2[i][j];
			}
		}
		
		// 기둥 설치
		infected_map[bar_index[0][0]][bar_index[0][1]]=1;
		infected_map[bar_index[1][0]][bar_index[1][1]]=1;
		infected_map[bar_index[2][0]][bar_index[2][1]]=1;
		
		// bfs 호출 -> 바이러스 퍼뜨리기
		visited=new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j]) continue;
				if(infected_map[i][j]==2) {
					virus_bfs(i,j);
				}
			}
		}
		// 바이러스 개수 세기
		count_virus();
	}

	// 바이러스 퍼뜨기리
	private static void virus_bfs(int row, int col) {
		visited[row][col]=true;
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {row,col});
		
		int[] temp;
		while(!queue.isEmpty()) {
			temp=queue.poll();
			for(int i=0;i<4;i++) {
				int tr=temp[0]+dr[i];
				int tc=temp[1]+dc[i];
				if(tr<0||tc<0||tr>=N||tc>=M) continue; // 범위를 넘어갈 경우 패스
				if(!visited[tr][tc] && (infected_map[tr][tc]==0||infected_map[tr][tc]==2)) {
					queue.offer(new int[] {tr,tc});
					visited[tr][tc]=true;
					infected_map[tr][tc]=2;
				}
			}
		}
	}
	
	// 바이러스 개수 세기
	private static void count_virus() {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(infected_map[i][j]==0) {
					cnt++;
				}
			}
		}
		if(max<cnt) {
			max=cnt;
		}
	}
}
