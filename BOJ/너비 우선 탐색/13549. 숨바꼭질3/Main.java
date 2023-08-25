import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int[] map;
	private static int N;
	private static int K;
	private static boolean[] visited;
	
	// 그래프 -> 최단경로 가중치 x -> bfs 풀이
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt(); // 수빈이 위치
		K=sc.nextInt(); // 동생 위치
		map=new int[100001];
		visited=new boolean[100001];
		bfs();
		System.out.println(map[K]);
	}
	
	private static void bfs() {
		if(N==K) {
			return;
		}
		Queue<Integer> queue=new ArrayDeque<>();
		visited[N]=true;
		queue.offer(N);
		
		int tmp=0;
		while(!queue.isEmpty()) {
			tmp=queue.poll(); // 현재 위치
			if(tmp==K) {
				return;
			}
			// 이동할 수 있는 3가지 경우
			for(int i=0;i<3;i++) {
				// 0초후 2* 위치로 이동하는 경우
				if(i==0) {
					int tmp2=tmp;
					int tmp3=tmp;
					if(tmp==0) continue;
					while(tmp2<100000) {
						tmp2*=2;
						if(tmp2>100000) break;
						map[tmp2]=map[tmp3];
						visited[tmp2]=true;
						queue.offer(tmp2);
						tmp3*=2;
					}
				}
				// 1초후 +1 위치로 이동하는 경우
				else if(i==1) {
					int tmp2=tmp+1;
					if(tmp2>100000||tmp2<0||visited[tmp2]) continue;
					map[tmp2]=map[tmp]+1;
					visited[tmp2]=true;
					queue.offer(tmp2);
				}
				// 1초후 -1 위치로 이동하는 경우
				else if(i==2) {
					int tmp2=tmp-1;
					if(tmp2>100000||tmp2<0||visited[tmp2]) continue;
					map[tmp2]=map[tmp]+1;
					visited[tmp2]=true;
					queue.offer(tmp2);
				}
			}
		}	
	}
}
