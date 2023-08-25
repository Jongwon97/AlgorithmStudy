import java.util.Scanner;

public class Main {
	private static int N;	 // 재료의 개수
	private static int[] S;	 // 신맛
	private static int[] B;	 // 쓴맛
	static boolean[] isSelected; // 각 원소가 부분집합의 구성에 포함되었는지 여부
	
	private static int min;	// 신맛과 쓴맛이 가장 적은 값
	private static int total_Count=0;
	
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();		// 재료의 개수
		S=new int[N];
		B=new int[N];
		isSelected=new boolean[N];
		
		for(int i=0;i<N;i++) {
			S[i]=sc.nextInt();
			B[i]=sc.nextInt();
		}
		min=Math.abs(S[0]-B[0]);  // 처음 값 초기화
		generateSubSet(0);
		System.out.println(min);
	}
	// 신맛은 사용한 재료의 신맛의 곱, 쓴맛은 합
	private static void generateSubSet(int cnt) { // cnt: 직전까지 고려된 원소 수
		if(cnt==N) {
			int mul_S=1;
			int sum_B=0;
			boolean false_check=false;
			for(int i=0;i<N;i++) {
				if(isSelected[i]) {
					mul_S*=S[i];
					sum_B+=B[i];
					false_check=true;
				}
			}
			// 모두 false인 경우
			if(false_check==false) {
				return;
			}
			// 현재 신맛의 곱과 쓴맛의 합 차이가 min보다 작을 경우
			if(min>Math.abs(mul_S-sum_B)) {
				min=Math.abs(mul_S-sum_B);
			}
			return;
		}		
		isSelected[cnt]=true;
		generateSubSet(cnt+1);
		isSelected[cnt]=false;
		generateSubSet(cnt+1);	
	}
}