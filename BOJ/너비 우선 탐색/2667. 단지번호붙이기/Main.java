import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {

	static int N;
	static int[][] map;
	static int nums; // 단지수
	static List<Integer> answer; // 각 단지에 속한 집의 수를 저장할 리스트
	static boolean[][] visited;
	
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	
	public static void main(String args[]) throws IOException{
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			String str=in.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]=str.charAt(j)-'0'; // 문자 -> 정수형 변환
			}
		}
		answer=new ArrayList<>();
		visited=new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				if(map[i][j]!=0) {
					bfs(i,j);
					nums++;
				}
			}
		}
		
		// 출력
		System.out.println(nums);
		Collections.sort(answer); // 리스트 오름차순으로 정렬
		for(int a: answer) {
			System.out.println(a); // 단지에 속한 집의 수 출력
		}

	}
	private static void bfs(int row, int col) {
		visited[row][col]=true;
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {row,col});
		
		int[] temp;
		int num=0;
		while(!queue.isEmpty()) {
			temp=queue.poll();
			for(int i=0;i<4;i++) {
				int tr=temp[0]+dr[i];
				int tc=temp[1]+dc[i];
				if(tr<0 || tc<0 || tr>=N || tc>=N || visited[tr][tc]) continue;
				if(map[tr][tc]!=0) {
					visited[tr][tc]=true;
					queue.offer(new int[] {tr,tc});
				}
			}
			num++;
		}
		answer.add(num);
	}
}