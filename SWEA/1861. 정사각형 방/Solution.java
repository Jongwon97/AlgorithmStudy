import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {

	// 동서남북 이동
	private static int[] dx= {0,1,0,-1};
	private static int[] dy= {1,0,-1,0};

	private static int N;
	private static int[][] rooms;
	private static int max;
	private static Position start;
	
    public static void main(String args[]) throws Exception{
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(); // 테스트 케이스 수
        
        for(int test_case = 1; test_case <= T; test_case++) {
        	N=sc.nextInt();  // NxN 배열 크기
        	// 격자판 초기화
        	rooms=new int[N][N];
        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			rooms[i][j]=sc.nextInt();
        		}
        	}
 	
        	start=new Position(0,0);
        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			bfs(i,j);
        		}
        	}
        	// 출력
        	System.out.println("#"+test_case+" "+rooms[start.x_position][start.y_position]+" "+max);
        }       
    }
    
    public static void bfs(int i,int j) {
    	Queue<Position> queue=new ArrayDeque<>();
    	
    	queue.offer(new Position(i,j));  // 시작 위치 큐에 추가

    	int count=1;
    	while(!queue.isEmpty()) {
    		Position po=queue.poll();
    		// 4방향을 탐색화여 자식노드 입력
    		for(int d=0;d<4;d++) {
    			// 범위를 넘어가는 경우 continue
				if(po.x_position+dx[d]<0||po.y_position+dy[d]<0||po.x_position+dx[d]>=N||po.y_position+dy[d]>=N) {
					continue;
				}
    			// 4개의 방향중 +1이 되는 노드가 있을 경우 큐에 추가
    			if((1+rooms[po.x_position][po.y_position])==rooms[po.x_position+dx[d]][po.y_position+dy[d]]) {
    				queue.offer(new Position(po.x_position+dx[d],po.y_position+dy[d]));
    				count++;
    				break;
    			}
    		}
    	}
    	if(i==0&&j==0) {
    		max=count;
    		return;
    	}
    	// 이동이 최대일 경우
    	if(count>max) {
    		max=count;
    		start.x_position=i;
    		start.y_position=j;
    	}
    	// 최대 이동이 같은 경우 방의 수 비교
    	if(count==max) {
    		if(rooms[start.x_position][start.y_position]>rooms[i][j]) {
    			start.x_position=i;
    			start.y_position=j;
    		}
    	}
    }
}

class Position{
	int x_position;
	int y_position;
	public Position(int x_position, int y_position) {
		super();
		this.x_position = x_position;
		this.y_position = y_position;
	}
}