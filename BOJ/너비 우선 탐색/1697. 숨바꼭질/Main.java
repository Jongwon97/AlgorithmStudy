import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N,K;
	
	private static int[] visited; 	// 해당 위치까지 걸리는 시간을 기록할 배열
	
	public static void main(String args[]) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 수빈이 위치
		K=Integer.parseInt(st.nextToken()); // 동생 위치
		visited=new int[100000+1]; // 맵
		
		bfs();
		System.out.println(visited[K]);
	}
	
	private static void bfs() {
		// 입력받은 위치가 서로 같을 경우
		if(N==K) {
			return;
		}
		
		Queue<Integer> queue=new ArrayDeque<>();
		queue.offer(N);
		
		int cur=0;
		while(!queue.isEmpty()) {
			cur=queue.poll();
			
			int next=0;
			for(int i=0;i<3;i++) { // 3가지 이동할 수 있는 경우
				if(i==0) {
					next=cur-1;
				}
				if(i==1) {
					next=cur+1;
				}
				if(i==2) {
					next=cur*2;
				}
				// 현재 위치가 맵의 범위를 넘어가거나, 방문했던 위치일 경우 continue
				if(next<0||next>100000||visited[next]!=0) continue;
				
				queue.offer(next);
				visited[next]=visited[cur]+1; // 이전 위치까지 걸린 시간에 1초 추가
				if(next==K) {
					return;
				}
			}
		}
	}
}