import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {

			int N=sc.nextInt();		// 손님 수
			int M=sc.nextInt(); int K=sc.nextInt(); // M초의 시간을 들여 K개의 붕어빵을 만듬

			int[] time=new int[N];  // 사람이 도착하는 시간(초단위)
			for(int i=0;i<N;i++) {
				time[i]=sc.nextInt();
			}
			Arrays.sort(time);     // 오름차순 정렬
			
			int arrived_num=0;     // 도착한 손님 수, time 인덱스로 사용
			int cur_num=0;     	   // 현재 갖고 있는 붕어빵 수
			boolean bo=true;
			
			int temp=M;
			// 반복문 변수 t는 시간, 가장 늦게오는 손님 시간까지 반복
			for(int t=0;t<=time[N-1];t++) {
				// 붕어빵을 만들 시간이되면 붕어빵 개수 추가, 시간 추가(다음에 만들어지는 시간)
				if(t==M) {
					cur_num+=K;
					M+=temp;
				}
				// 손님 도착 시간이 됬을때
				if(t==time[arrived_num]) {
					// 붕어빵이 1개이상 있으면
					if(cur_num>0) {
						cur_num--;
						arrived_num++;
						// 아직 손님이 다 안왔고, 다음 손님의 도착시간이 이전손님과 같은 경우
						if(arrived_num!=N) {
							if(time[arrived_num]==time[arrived_num-1]) {
								t--;
							}
						}
					}
					// 붕어빵이 한개도 없는 경우
					else {
						bo=false;
						break;
					}
				}
			}
			if(bo) {
				System.out.println("#"+test_case+" Possible");
			}
			else {
				System.out.println("#"+test_case+" Impossible");
			}
		}
	}
}