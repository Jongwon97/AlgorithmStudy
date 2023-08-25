import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
비어있는곳: .
돌: X
고슴도치의 위치: S
비버 소굴: D
물이 차 있는 지역: *
*
*/

// 고슴도치는 물로 이동 x, 물은 비버 소굴로 이동 x
//  매 분마다 고슴도치는 인접한 네 칸 중 하나로 이동 가능.
//  물은 매 분마다 인접한 비어있는 칸으로 확장

//  고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간 출력
//  이동 불가인 경우 "KAKTUS" 출력

public class Main {

	static int R,C;
	static char[][] map;
	static int[] S_index; // 고슴도치의 위치
	static boolean[][] visited;
	static boolean checked;
	static int answer;
	
	static int[] dr= {0,-1,0,1};
	static int[] dc= {-1,0,1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// 맵 초기화
		map=new char[R][C];
		visited=new boolean[R][C];
		S_index=new int[3];
		
		for(int i=0;i<R;i++) {
			String str=in.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=str.charAt(j);
				if(map[i][j]=='S') {
					S_index[0]=i;
					S_index[1]=j;
				}
			}
		}
		start();
		if(checked) {
			System.out.println(answer);
		}
		else {
			System.out.println("KAKTUS");
		}
	}
	
	private static void start() {

		// 고슴도치가 더이상 이동할 수 없는 경우 이동 불가
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(S_index); // 0은 R, 1은 C, 2는 level
		
		int[] temp;
		int pre=-1;
		// 반복횟수 = 걸린 시간
		while(!queue.isEmpty()) {			
			temp=queue.poll();
			// 이전 이동 레벨과 달라질 경우만 물 확장
			if(pre!=temp[2]) {
				expanded();
			}

			// 고슴도치 이동
			for(int i=0;i<4;i++) {
				int tr=temp[0]+dr[i];
				int tc=temp[1]+dc[i];
				if(tr<0||tr>=R||tc<0||tc>=C) continue;
				if(map[tr][tc]=='D') {
					answer=temp[2]+1;
					checked=true;
					return;
				}
				if(map[tr][tc]=='.'&& !visited[tr][tc]) {
					queue.offer(new int[] {tr,tc,temp[2]+1});
					visited[tr][tc]=true;
				}
			}
			pre=temp[2];
		}
		
	}
	
	private static void expanded() {
		// 물의 위치를 모두 큐에 담는다.
		Queue<int[]> queue=new ArrayDeque<>();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]=='*') queue.offer(new int[] {i,j});
			}
		}
		
		int[] temp;
		while(!queue.isEmpty()) {
			temp=queue.poll();
			for(int j=0;j<4;j++) {
				int tr=temp[0]+dr[j];
				int tc=temp[1]+dc[j];
				if(tr<0||tr>=R||tc<0||tc>=C) continue;
				if(map[tr][tc]=='.') {
					map[tr][tc]='*';
				}
			}
		}
	}
}