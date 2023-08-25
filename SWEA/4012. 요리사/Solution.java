import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	private static int[][] table;
	private static int N;
	
	private static boolean[] ingredients;
	private static int min;
	
	public static void main(String args[]) throws IOException {
	
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(in.readLine()); // 총 테스트 케이스 수
		
		for(int test_case=1;test_case<=T;++test_case) {
			N=Integer.parseInt(in.readLine());  // 식재료의 수
			table=new int[N][N];
			
			// 재료를 담을 배열
			ingredients=new boolean[N];
			min=Integer.MAX_VALUE;

			// 시너지 값 초기화
			for(int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(in.readLine()," ");
				for(int j=0;j<N;j++) {
					table[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			combination(0,0);
			System.out.println("#"+test_case+" "+min);
		
		}
		
	}
	private static void combination(int cnt, int start) {
		// N/2개의 재료를 선택했을 경우
		if(cnt==N/2) {
			int sum1=0;
			int sum2=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					// 같은 식재료 일 경우 pass
					if(i==j) continue;
					// true true 식재료 일 경우
					if(ingredients[i]&&ingredients[j]) {
						sum1+=table[i][j];
					}
					// false false 식재료 일 경우
					if(!ingredients[i]&&!ingredients[j]) {
						sum2+=table[i][j];
					}

				}
			}
			int temp=Math.abs(sum1-sum2); // 2개의 요리 시너지 차이
			if(min>temp) {
				min=temp;
			}
			return;
		}
		
		for(int i=start;i<N;i++) {
			ingredients[i]=true;
			combination(cnt+1,i+1);
			ingredients[i]=false;
		}
	}
}