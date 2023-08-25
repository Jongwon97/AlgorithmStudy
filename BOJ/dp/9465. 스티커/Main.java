import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(in.readLine());  // 테스트 케이스 개수 입력
		
		for(int test_case=0;test_case<T;test_case++) {
			int n=Integer.parseInt(in.readLine());    // 스티커 열의 개수 입력
			
			int[][] arr=new int[2][n+1];	// 스티커 점수 입력
			
			for(int i=0;i<2;i++) {
				StringTokenizer st=new StringTokenizer(in.readLine()," ");
				for(int j=1;j<=n;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp=new int[2][n+1]; // 해당 열까지의 최댓값을 저장할 배열, 인덱스 i-2 연산을 위해 1열부터 시작
			// 초기화, 첫 번째 열(시작 위치)
			dp[0][1]=arr[0][1];
			dp[1][1]=arr[1][1];
			
			// 두 번째 열부터 반복 시작, dp배열 0,1행 각각에 현재 위치까지의 최대값 기록
			for(int i=2;i<=n;i++) {
				dp[0][i]=Math.max(dp[1][i-1], dp[1][i-2])+arr[0][i];
				dp[1][i]=Math.max(dp[0][i-1], dp[0][i-2])+arr[1][i];
			}
			
			System.out.println(Math.max(dp[0][n],dp[1][n])); // 출력
		}
	}
}