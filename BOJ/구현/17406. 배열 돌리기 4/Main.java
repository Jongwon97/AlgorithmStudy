import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N,M,K;
	
	private static int[][] original;
	private static int[][] copy;
	
	// 회전 방향(오른쪽, 아래, 왼쪽, 위)
	static int[] dx= {0,1,0,-1};
	static int[] dy= {1,0,-1,0};
	
	private static int[] numbers;
	private static boolean[] isSelected;
	
	private static int[] r;
	private static int[] c;
	private static int[] s;
	
	private static int min=Integer.MAX_VALUE;
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());  // 배열의 행
		M=Integer.parseInt(st.nextToken());	 // 배열의 열
		K=Integer.parseInt(st.nextToken());  // 회전 연산의 개수
		
		original=new int[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=1;j<=M;j++) {
				original[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		// r,c,s 입력및 초기화
		r=new int[K];
		c=new int[K];
		s=new int[K];
		for(int i=0;i<K;i++) {
			st=new StringTokenizer(in.readLine()," ");
			r[i]=Integer.parseInt(st.nextToken());
			c[i]=Integer.parseInt(st.nextToken());
			s[i]=Integer.parseInt(st.nextToken());
		}
		
		numbers=new int[K];
		isSelected=new boolean[K];
		permutation(0);
		
		System.out.println(min);
	}

	// 순열 구하는 메서드
    private static void permutation(int cnt) {  // cnt는 순열의 현재 위치(배열의 인덱스)
    	if(cnt==K) {
    		function();
    		return;
    	}
    	for(int i=0;i<K;i++) {
    		if(isSelected[i]) continue;
    		numbers[cnt]=i;
    		isSelected[i]=true;
    		permutation(cnt+1);
    		isSelected[i]=false;
    	}
    }
    
    // 알고리즘
    private static void function() {
    	copy=new int[N+1][M+1];
    	for(int i=0;i<=N;i++) {
    		for(int j=0;j<=M;j++) {
    			copy[i][j]=original[i][j];
    		}
    	}
    	// 회전 연산 수 만큼 반복
		for(int i=0;i<K;i++) {	
			boolean[][] isVisited=new boolean[N+1][M+1];
			int d_index=0;
			int x=r[numbers[i]]-s[numbers[i]]; // 시작 좌표 x
			int y=c[numbers[i]]-s[numbers[i]]; // 시작 좌표 y
			int x_po=x;
			int y_po=y;
			int x_end=x+2*s[numbers[i]];
			int y_end=y+2*s[numbers[i]];
			
			int min=Math.min(x_end-x+1, y_end-y+1);  // 루프 수=(작은 수/2)
			// 루프수만큼 반복
			for(int j=0;j<min/2;j++) {
				int temp=copy[x][y];
				while(true) {
					// 방향 전환
					if(x+dx[d_index]<x_po||x+dx[d_index]>x_end||y+dy[d_index]<y_po||y+dy[d_index]>y_end||isVisited[x+dx[d_index]][y+dy[d_index]]) {
						d_index++;
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
					int temp2=copy[x][y];
					copy[x][y]=temp;
					temp=temp2;
					// 방문 처리
					isVisited[x][y]=true;
				}
			}	

		}
		// 최소값 계산
		for(int i=1;i<=N;i++) {
			int temp=0;
			for(int j=1;j<=M;j++) {
				temp+=copy[i][j];
			}
			if(min>temp) {
				min=temp;
			}
		}
    }
}
