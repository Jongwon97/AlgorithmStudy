import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
	
		int answer=0;
		Scanner sc=new Scanner(System.in);

		int N = sc.nextInt();
		
		int[][] conf=new int[N][2];
		// 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				conf[i][j]=sc.nextInt();
			}
		}

		// 1열 기준 오름차순 정렬, 1열의 값이 같을 경우 0열의 값도 오름차순 정렬
		Arrays.sort(conf, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});

		// (0,1)은 첫 회의 시간
		int end_Time=conf[0][1];  // 첫 회의의 끝나는 시간
		answer++;
	
		for(int i=1;i<N;i++) {
			// 시작 시간이 이전 끝나는 시간보다 클경우 회의수 +1
			if(conf[i][0]>=end_Time) {
				answer++;
				end_Time=conf[i][1];  // 끝나는 시간 저장
			}
		}
		System.out.println(answer);
	}
}
