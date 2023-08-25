import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());

		int[] T=new int[N+16]; // 걸리는 기간
		int[] P=new int[N+16]; // 받을 수 있는 금액
		int[] dp=new int[N+16];
		
		StringTokenizer st;
		// 인덱스(i)는 일
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(in.readLine(), " ");
			T[i]=Integer.parseInt(st.nextToken());
			P[i]=Integer.parseInt(st.nextToken());
		}
		
		int max=0;
		for(int i=1;i<=N+1;i++) {
			dp[i] = Math.max(dp[i],max); 
			dp[i+T[i]] = Math.max(dp[i+T[i]],dp[i]+P[i]);
			max=Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
	
}
