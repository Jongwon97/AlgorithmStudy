import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		int[][] arr=new int[N][2]; 	// 0열: A전깃줄 번호, 1열: B전깃줄 번호 -> 서로 연결 되어있음
		int[] dp=new int[N];		// 해당 위치까지 설치할 수 있는 전깃줄의 최댓값
		
		// 입력
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<2;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		// 0열(A 전봇대 번호)을 기준으로 오름차순 정렬
		Arrays.sort(arr,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		// i는 현재 전깃줄 위치(행), j는 i이전까지의 과거 전깃줄 위치(행)
		for(int i = 0; i < N; i++) {
			dp[i] = 1; // 전깃줄의 최소 연결 개수
			for(int j = 0; j < i; j++) {
				if(arr[i][1] > arr[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		Arrays.sort(dp);
		int max =dp[N-1]; 
		
		// 없애야하는 전깃줄의 최소 개수 = 전체 개수-전깃줄을 설치할 수 있는 최대 개수 
		System.out.println(N - max);

	}
}