import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	
	private static int N;
	private static int company_position_x;
	private static int company_position_y;
	private static int home_position_x;
	private static int home_position_y;
	private static int[] customer_position_x;
	private static int[] customer_position_y;
	
	private static int min_distance;
	private static boolean[] isVisited;
	private static int temp;
	
	private static int[] arr; // 지나간 위치를 기록
	
	public static void main(String args[]) throws Exception{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++){

			N=Integer.parseInt(in.readLine()); // 고객의 수
			
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			// 회사 좌표
			company_position_x=Integer.parseInt(st.nextToken());
			company_position_y=Integer.parseInt(st.nextToken());
			// 집 좌표
			home_position_x=Integer.parseInt(st.nextToken());
			home_position_y=Integer.parseInt(st.nextToken());
			
			customer_position_x=new int[N];
			customer_position_y=new int[N];
			// N명의 고객 좌표
			for(int i=0;i<N;i++) {
				customer_position_x[i]=Integer.parseInt(st.nextToken());
				customer_position_y[i]=Integer.parseInt(st.nextToken());
			}
			isVisited=new boolean[N];
			arr=new int[N];
			temp=0;
			min_distance=Integer.MAX_VALUE;
			
			dfs(0);
			System.out.println("#"+test_case+" "+min_distance);
		}
	}
	
	// 회사 -> 고객 집 순회 -> 집
	private static void dfs(int cnt) {
		if(cnt==N) {
			// 고객집 -> 집
			int distance=Math.abs(customer_position_x[arr[cnt-1]]-home_position_x)+Math.abs(customer_position_y[arr[cnt-1]]-home_position_y);
			temp+=distance;
			if(temp<min_distance) {
				min_distance=temp;
			}
			temp-=distance;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(isVisited[i]) continue;
			
			isVisited[i]=true;
			int distance=0;
			if(cnt==0) { // cnt가 0일 경우 회사 -> 고객 집
				distance+=Math.abs(company_position_x-customer_position_x[i])+Math.abs(company_position_y-customer_position_y[i]);
			}
			else { // 0이 아닐때 고객집 -> 고객집
				distance=Math.abs(customer_position_x[arr[cnt-1]]-customer_position_x[i])+Math.abs(customer_position_y[arr[cnt-1]]-customer_position_y[i]);
			}
			arr[cnt]=i;
			temp+=distance;
			dfs(cnt+1);
			temp-=distance;
			isVisited[i]=false;
		}
	}
}