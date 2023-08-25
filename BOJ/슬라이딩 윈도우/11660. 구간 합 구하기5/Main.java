import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		// Scanner로 입력받으면 시간초과뜸
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		// 출력 이걸로 안하면 시간초과뜸
		StringBuilder sb=new StringBuilder();
	
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
	    int N=Integer.parseInt(st.nextToken()); // 표의 크기
	    int M=Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

		int[][] puzzle=new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=1;j<=N;j++) {
				// 행별로 입력과 동시에 값을 누적
				puzzle[i][j]=Integer.parseInt(st.nextToken())+puzzle[i][j-1];
			}
		}
		
		for(int test_case=0;test_case<M;test_case++) {
			st=new StringTokenizer(in.readLine()," ");
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			int sum=0;
			for(int n=x1;n<=x2;n++) {
				sum+=puzzle[n][y2]-puzzle[n][y1-1];
			}
			sb.append(sum+"\n"); // 여기서 Syso으로 바로 출력하면 시간초과뜸!
		}
		System.out.println(sb);
	}
}