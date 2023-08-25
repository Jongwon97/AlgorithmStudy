import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 행(6개) = 나라 수, 열(3개) = 승/무/패
	private static int[][] group;
	
	// 가능한 경기 팀 조합(총 15경기)
	private static int[] team1= {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	private static int[] team2= {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	
	private static int answer;
	public static void main(String args[]) throws IOException{

		
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=0;test_case<4;test_case++) {
			// 그룹 초기화 및 입력
			group=new int[6][3];
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			boolean checked=true;
			for(int i=0;i<6;i++) {
				int temp=0;
				for(int j=0;j<3;j++) {
					group[i][j]=Integer.parseInt(st.nextToken());
					temp+=group[i][j];
				}
				// 한팀당 경기수가 5보다 많으면 오류
				if(temp>5) {
					checked=false;
					break;
				}
			}
			if(checked) {
				dfs(0);
			}
			System.out.println(answer);
			answer=0;
		}
		
	}
	private static void dfs(int match_count) {
		if(answer==1) {
			return;
		}
		if(match_count==15) {
			answer=1;
			return;
		}
		// 팀1: win, 팀2: lose 일때
		if(group[team1[match_count]][0]>0 && group[team2[match_count]][2]>0) {
			group[team1[match_count]][0]--;
			group[team2[match_count]][2]--;
			dfs(match_count+1);
			group[team1[match_count]][0]++;
			group[team2[match_count]][2]++;
		}
		// 팀2: win, 팀1: lose 일때
		if(group[team1[match_count]][2]>0 && group[team2[match_count]][0]>0) {
			group[team1[match_count]][2]--;
			group[team2[match_count]][0]--;
			dfs(match_count+1);
			group[team1[match_count]][2]++;
			group[team2[match_count]][0]++;
		}
		// draw일 경우
		if(group[team1[match_count]][1]>0 && group[team2[match_count]][1]>0) {
			group[team1[match_count]][1]--;
			group[team2[match_count]][1]--;
			dfs(match_count+1);
			group[team1[match_count]][1]++;
			group[team2[match_count]][1]++;
		}
	}
}