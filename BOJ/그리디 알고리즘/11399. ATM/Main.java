import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){

		int answer=0;

		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();

		int[] P=new int[N];
		
		for(int i=0;i<N;i++) {
			P[i]=sc.nextInt();
		}
		
		Arrays.sort(P);    // 오름차순 정렬
		
		for(int i=0;i<N;i++) {
			answer+=P[i]*(N-i);   // 해당 원소가 나오는 수만큼 더하기
		}
		
		System.out.println(answer);
		
	}
}
