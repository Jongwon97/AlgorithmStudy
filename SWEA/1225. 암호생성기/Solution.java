import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Testcase 10개, 각각 8개의 데이터
		int[][] ans=new int[10][8];

		for(int test_case = 0; test_case < 10; test_case++)
		{
			LinkedList<Integer> list=new LinkedList<Integer>();
			int n=sc.nextInt();
			int num=0;
			for(int i=0;i<8;i++) {
				num=sc.nextInt();
				list.add(num);
			}
			int count=1;
			
			while(true) {
				if(list.get(0)-count<=0) {
					list.add(0);
					list.remove(0);
					break;
				}
				list.add(list.get(0)-count);
				list.remove(0);
				if(count==5) {
					count=0;
				}
				count++;
			}
			for(int i=0;i<8;i++) {
				ans[test_case][i]=list.get(i);
			}

		}
		for(int i=1;i<=10;i++) {
			System.out.print("#"+i);
			for(int j=0;j<8;j++) {
				System.out.print(" "+ans[i-1][j]);	
			}
			System.out.println();
			
		}
	}

}
