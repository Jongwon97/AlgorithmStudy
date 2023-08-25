import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine());
		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int[] dp=new int[n];
		dp[0]=Integer.parseInt(st.nextToken());
		for(int i=1;i<n;i++) {
			dp[i]=Integer.parseInt(st.nextToken());
			dp[i]=Math.max(dp[i],dp[i-1]+dp[i]);
		}
		
		// 우선순위 큐 -> 내림차순
		PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
		for(int i=0;i<n;i++) {
			pq.add(dp[i]);
		}
		// 출력
		System.out.println(pq.poll());
	}	
}