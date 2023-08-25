import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[] ans=new int[10];
		
		for(int test_case = 0; test_case < 10; test_case++)
		{
			/*
			 *  테이블 윗부분 N극, 아랫부분 S극
			 *  1은 N극 자성체, 2는 S극 자성체
			 *  자성체는 테이블 앞뒤 쪽에 있는 N극 S극에만 반응하며 자성체끼리는 반응 x			 *
			 */
			
			// 테이블 초기화 및 입력
			int size=sc.nextInt();
			int[][] table=new int[size][size];
			for(int i=0;i<size;i++) {
				for(int j=0;j<100;j++) {
					table[i][j]=sc.nextInt();
				}
			}
			
			// 한 열씩 탐색, j는 열 i는 행
			for(int j=0;j<size;j++) {
				// 1. 떨어지는 자성체 처리
				// 위에서부터 검사
				for(int i=0;i<size;i++) {
					if(table[i][j]==1) {
						break;
					}
					if(table[i][j]==2) {
						table[i][j]=0;
					}
				}
				// 아래서부터 검사
				for(int i=size-1;i>=0;i--) {
					if(table[i][j]==2) {
						break;
					}
					if(table[i][j]==1) {
						table[i][j]=0;
					}
				}
				// 2. 교착 상태 탐색
				// 위에서부터 내려오면서 N극(1)을 만날경우, S(2)를 만나면 +1
				boolean find=false;
				for(int i=0;i<size;i++) {
					if(table[i][j]==1) {
						find=true;
					}
					if(table[i][j]==2&&find==true) {
						ans[test_case]++;
						find=false;
					}
				}
			}		
		}
		
		// 출력
		for(int i=1;i<=10;i++) {
			System.out.println("#"+i+" "+ ans[i-1]);
		}
	}

}
