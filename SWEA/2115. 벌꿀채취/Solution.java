import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N,M,C;
	static int[][] map; // 벌통(꿀의 양 저장)
	static int profit1,profit2;
	static int[] list;
	static boolean[] isSelected;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(in.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 벌통의 수
			C=Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 최대 양
			map=new int[N][N];
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(in.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			// 첫 번째 일꾼이 먼저 벌통 선택
			for(int i=0;i<N;i++) {
				for(int j=0;j<=N-M;j++) {
					getList(i,j);
					getsubSet(0,0);
					combination(i,j);
					profit1=0;
				}
			}
			System.out.println("#"+test_case+" "+max);
			max=0;
		}
	}
	
	// 2명의 일꾼이 벌통을 선택할 수 있는 조합 생성(겹치면 안됨)
	private static void combination(int row, int col) {
		for(int i=row;i<N;i++) {
			if(i==row) {
				for(int j=col+M;j<=N-M;j++) {
					getList(i,j);
					getsubSet(0,1);
					max=Math.max(max, profit1+profit2);
				}
			}
			else {
				for(int j=0;j<=N-M;j++) {
					getList(i,j);
					getsubSet(0,1);
					max=Math.max(max, profit1+profit2);
				}
			}
		}
		profit2=0;
	}
	
	// 채집한 벌통들을 담은 배열 생성
	private static void getList(int row, int col) {
		isSelected=new boolean[M];
		list=new int[M];
		for(int i=0;i<M;i++) {
			list[i]=map[row][col+i];
		}
	}
	
	// 채집가능한 벌통들의 부분집합 생성
	private static void getsubSet(int cnt, int flag) {
		if(cnt==M) {
			int temp=0;
			for(int i=0;i<M;i++) {
				if(isSelected[i]) {
					temp+=list[i];
				}
			}
			if(temp<=C) {
				int profit=0;
				for(int i=0;i<M;i++) {
					if(isSelected[i]) {
						profit+=list[i]*list[i];
					}
				}
				if(flag==0) {
					profit1=Math.max(profit, profit1);
				}
				else {
					profit2=Math.max(profit, profit2);
				}
			}
			return;
		}
		isSelected[cnt]=true;
		getsubSet(cnt+1,flag);
		isSelected[cnt]=false;
		getsubSet(cnt+1,flag);
	}
}