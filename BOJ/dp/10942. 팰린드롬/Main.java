import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	
	public static void main(String[] args) throws IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine()); // 수열의 크기
		int[] arr=new int[N+1]; // 수열을 담을 배열
		
		// 수열 초기화
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		for(int i=1;i<=N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		// memoization 처리
		int[][] dp=new int[N+1][N+1]; // 행 인덱스는 시작점의 번호, 열 인덱스는 시작점과 해당 열의 점과 팰린드롬 여부
		// step1: 행과 열 인덱스가 같은 경우 초기화
		for(int i=1;i<=N;i++) {
			dp[i][i]=1;
		}
		// step2 
        for(int i = 1; i <= N - 1; i++) {
        	if(arr[i]==arr[i+1]) {
        		dp[i][i+1]=1;        	
        	}
        }
        // step3
        for(int i=1; i<=N-1; i++){
            for(int j=1; j<=N-i; j++){
            	// 맨 앞과 맨뒤가 같은 경우 + 그 사잇값들이 팰린드롬인 경우
                if(arr[j]==arr[j+i]&&dp[j+1][j+i-1]==1) {
                	dp[j][j+i]=1;
                }
            }
        }
		
		int M=Integer.parseInt(in.readLine()); // 질문의 개수
		int a,b;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(in.readLine()," ");
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
            if(dp[a][b]==1) {
            	sb.append("1\n");
            }
            else {
            	sb.append("0\n");
            }
		}
		
		System.out.println(sb);
	}
}
