import java.util.Scanner;
 
public class Solution {
    public static void main(String args[]) throws Exception{
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(); // 테스트 케이스 수
        
        for(int test_case = 1; test_case <= T; test_case++) {
        	int N=sc.nextInt();  	//NXN 배열 크기
        	System.out.println("#"+test_case);
        	int[][] snail=new int[N][N];

        	// 우 -> 하 -> 좌 -> 상
        	// [0,1], [1,0], [0,-1], [-1,0] 방향 순으로 이동
        	int[] dx= {0,1,0,-1};
        	int[] dy= {1,0,-1,0};
        	
        	int x_pos=0;   // 행 좌표
        	int y_pos=0;   // 열 좌표
        	int index=0;
        	for(int i=1;i<=N*N;++i) {
        		if(i==1) { 
        			snail[0][0]=1; 
        			continue;
        		}
        		// 방향 전환
        		// 퍼즐 밖으로 나가거나(인덱스가 N이상 0이하), 다음 값이 0이 아니면
        		if(x_pos+dx[index]<0||y_pos+dy[index]<0||x_pos+dx[index]>N-1||y_pos+dy[index]>N-1||snail[x_pos+dx[index]][y_pos+dy[index]]!=0) {
        			index=(index+1)%4;  // 0,1,2,3 반복
        		}	
        		x_pos+=dx[index];
        		y_pos+=dy[index];
        		snail[x_pos][y_pos]=i;
        	}

        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			System.out.print(snail[i][j]+" ");
        		}
        		System.out.println();
        	}
        	
        }
    }
 }