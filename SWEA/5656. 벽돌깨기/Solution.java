import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution{
	
	static int N,W,H;
	static int[][] original;
	static int[][] copy;
	static int[] numbers; // 구슬을 발사할 위치를 저장할 배열(열 저장)
	static int answer;
	
	public static void main(String args[]) throws Exception{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++){
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			N=Integer.parseInt(st.nextToken()); // 구슬 수
			W=Integer.parseInt(st.nextToken()); // 너비
			H=Integer.parseInt(st.nextToken()); // 높이
			
			original=new int[H][W];
			for(int i=0;i<H;i++) {
				st=new StringTokenizer(in.readLine()," ");
				for(int j=0;j<W;j++) {
					original[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			answer=Integer.MAX_VALUE;
			numbers=new int[N];
			permutation(0);			
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	// 중복 순열 구현: 구슬을 쏠 수있는 위치들의 집합
	private static void permutation(int cnt) {
		if(cnt==N) {
			// 원본 배열 복사
			copy=new int[H][W];
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					copy[i][j]=original[i][j];
				}
			}
			start();
			return;
		}
		for(int i=0;i<W;i++) {
			numbers[cnt]=i;
			permutation(cnt+1);
		}
	}
		
	private static void start() {
		// 구슬을 발사가능한 횟수만큼 반복
		for(int cnt=0;cnt<N;cnt++) {
			// 행만큼 반복
			for(int i=0;i<H;i++) {
				if(copy[i][numbers[cnt]]!=0) { // 해당 열의 맨위에 벽돌이 구슬에 부딪힘
					remove(i,numbers[cnt]);
					break;
				}
			}
		}
		// 구슬을 모두 쏜 후, 남은 벽돌 세기
		int count=0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
			//	System.out.print(copy[i][j]);
				if(copy[i][j]!=0) count++;
			}
		//	System.out.println();
		}
	//	System.out.println();
		if(answer>count) {
			answer=count;
		}
	}
	
	// 벽돌을 제거하는 작업을 하는 메서드
	private static void remove(int row, int col) {
		if(copy[row][col]==1) {
			copy[row][col]=0;
			return;
		}
		
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		boolean[][] visited=new boolean[H][W];
		visited[row][col]=true;
		
		while(!queue.isEmpty()) {
			int[] temp=queue.poll();
			// 오른쪽 인덱스 큐에 넣기
			for(int i=1;i<=copy[temp[0]][temp[1]]-1;i++) {
				if(temp[1]+i>=W) break;
				if(visited[temp[0]][temp[1]+i]) continue;
				visited[temp[0]][temp[1]+i]=true;
				queue.offer(new int[] {temp[0],temp[1]+i});
			}
			// 왼쪽 인덱스 큐에 넣기
			for(int i=1;i<=copy[temp[0]][temp[1]]-1;i++) {
				if(temp[1]-i<0) break;
				if(visited[temp[0]][temp[1]-i]) continue;
				visited[temp[0]][temp[1]-i]=true;
				queue.offer(new int[] {temp[0],temp[1]-i});
			}
			// 위쪽 인덱스 큐에 넣기
			for(int i=1;i<=copy[temp[0]][temp[1]]-1;i++) {
				if(temp[0]-i<0) break;
				if(visited[temp[0]-i][temp[1]]) continue;
				visited[temp[0]-i][temp[1]]=true;
				queue.offer(new int[] {temp[0]-i,temp[1]});
			}
			// 아래쪽 인덱스 큐에 넣기
			for(int i=1;i<=copy[temp[0]][temp[1]]-1;i++) {
				if(temp[0]+i>=H) break;
				if(visited[temp[0]+i][temp[1]]) continue;
				visited[temp[0]+i][temp[1]]=true;
				queue.offer(new int[] {temp[0]+i,temp[1]});
			}
			copy[temp[0]][temp[1]]=0;
		}
		relocated();
	}
	
	// 벽돌을 재배치 하는 작업을 하는 메서드
	private static void relocated() {
		for(int j=0;j<W;j++) {
			Stack<Integer> stk=new Stack<>();
			for(int i=0;i<H;i++) {
				if(copy[i][j]!=0) {
					stk.push(copy[i][j]);
					copy[i][j]=0;
				}
			}
			int temp=H-1;
			while(!stk.isEmpty()) {
				copy[temp][j]=stk.pop();
				temp--;
			}
		}
	}
}