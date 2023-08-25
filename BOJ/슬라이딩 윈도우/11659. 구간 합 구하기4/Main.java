import java.util.Scanner;


public class Main {

	public static void main(String args[]) {

		Scanner sc=new Scanner(System.in);
		
		int N=sc.nextInt(); // 수의 개수
		int M=sc.nextInt(); // 합을 구해야 하는 횟수
		
		int[] arr=new int[N+1]; // 누적 합 입력
		
		// 숫자 입력과 동시에 누적합 입력
		for(int i=1;i<=N;i++) {
			arr[i]=sc.nextInt()+arr[i-1];
		}
		
		
		for(int n=0;n<M;++n) {
			int i=sc.nextInt();
			int j=sc.nextInt();
			System.out.println(arr[j]-arr[i-1]);
		}

	}
}