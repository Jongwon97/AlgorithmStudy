import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] sushi;
	static int[] sushi_cnt;
	static int max;  // 최대 가짓수
	static int current_Type; // 현재 가짓수
	
	public static void main(String[] args) throws IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		int N=Integer.parseInt(st.nextToken());		// 벨트에 놓인 접시의 수
		int d=Integer.parseInt(st.nextToken());		// 초밥의 가짓수		(2<=d<=3000)
		int k=Integer.parseInt(st.nextToken());		// 연속해서 먹는 접시의 수 (2<=k<=3000)
		int c=Integer.parseInt(st.nextToken()); 	// 쿠폰 번호

		sushi=new int[N+k-1];
		sushi_cnt=new int[d+1];
		// 벨트에 놓인 초밥 입력 (1~d)
		for(int i=0;i<N;i++) {
			sushi[i]=Integer.parseInt(in.readLine());
		}
		// 뒤에 K-1개만큼 앞부분 추가
		for(int i=0;i<k-1;i++) {
			sushi[N+i]=sushi[i];
		}

		// 먼저 K개의 접시를 고른다.
		for(int i=0;i<k;i++) {
			// 처음 들어가는 종류인 경우
			if(sushi_cnt[sushi[i]]==0) {
				current_Type++;
			}
			sushi_cnt[sushi[i]]++;
		}
		// 쿠폰 번호를 처음에 미리 처리
		if(sushi_cnt[c]==0) {
			current_Type++;
		}
		sushi_cnt[c]++;
		max=current_Type;
		
		// 맨앞은 빼고 맨 뒤는 한칸 이동하여 더함
		int end=k;
		for(int start=0;start<N-1;start++) {
			// 맨앞에 초밥 제거
			if(sushi_cnt[sushi[start]]==1) { // 해당 초밥 종류가 1인경우 -1의 경우 종류가 감소하므로
				current_Type--;
			}
			sushi_cnt[sushi[start]]--;
			// 맨 뒤에 초밥 추가
			if(sushi_cnt[sushi[end]]==0) { // 해당 초밥의 종류가 0인 경우 +1한 경우 종류가 증가하므로
				current_Type++;
			}
			sushi_cnt[sushi[end]]++;
			++end;
			// 최대 가짓수 갱신
			if(max<current_Type) {
				max=current_Type;
			}
		}
		System.out.println(max);
		
	}
}