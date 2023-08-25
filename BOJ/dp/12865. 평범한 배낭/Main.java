import java.io.IOException;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt(); // 물건 개수
		int W=sc.nextInt(); // 가방 무게
		
		int[] weights=new int[N+1];
		int[] profits=new int[N+1];
		
		for(int i=1;i<=N;i++) {
			weights[i]=sc.nextInt();
			profits[i]=sc.nextInt();
		}
		
		int[][] D=new int[N+1][W+1];
		
		// 초기값 세팅: int[][] 배열의 자동초기화를 이용
		
		for(int i=1;i<=N;i++) { // i: 물건
			for(int w=1;w<=W;w++) { // w: 가방의 무게
				// 해당 물건의 무게가 w를 초과하는지
				if(weights[i]>w) {
					D[i][w]=D[i-1][w];
				}
				else {
					D[i][w]=Math.max(D[i-1][w],profits[i]+D[i-1][w-weights[i]]);
				}
			}
			
		}
		System.out.println(D[N][W]);

	}
}