import java.util.Scanner;

public class Solution {

	static int[] queen;
	static int answer,N;
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
			N=sc.nextInt();		
			queen=new int[N];
			answer=0;
			dfs(0);
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	private static void dfs(int col) {
		if ( N==col ) {
			answer++;
			return ;
		}
		for(int i=0; i<N; i++) {
			// 해당 열에 i행에 값이 들어갈 수 있는지 순서대로 탐색
			queen[col] = i;
			if( isPossible(col) ) {   // 해당 행에 퀸을 놓을 수 있는 경우
				dfs(col+1);   // 다음 열 탐색
			}
		}
		
	}
	
	private static boolean isPossible(int col) {
		for(int i=0; i<col; i++) {
			// 다른 퀸들과 같은 행에 있거나, 같은 대각선 안에 위치할 경우 false 반환 
			if (queen[col] == queen[i] || Math.abs(col-i) == Math.abs(queen[col]-queen[i])) { // 행-행 과 열-열의 절대값이 같을 경우 같은 대각선에 위치
				return false;
			}
		}
		return true;
	}

}

 /*
 * 5x5 체스판이 있다고 가정, 1은 가능한 퀸의 위치
 * 1 0 0 0 0
 * 0 0 1 0 0
 * 0 0 0 0 1      -> 같은 열과 행에는 퀸이 들어갈 수 없으므로, 이것을 1차원 배열로 표현할 수 있다. 
 * 0 1 0 0 0 		 [0,3,1,4,2] -> 0열에는 0, 1열에는 3, 2열에는 1 ... 4열에는 2행에 퀸이 위치함을 의미.
 * 0 0 0 1 0
 */
