import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {


	private static int H;
	private static int W;
	private static char[][] map;
	private static int tank_row;
	private static int tank_col;
	
	public static void main(String args[]) throws IOException {
	
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(in.readLine()); // 총 테스트 케이스 수
		
		for(int test_case=1;test_case<=T;++test_case) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			H=Integer.parseInt(st.nextToken());  	// 맵의 높이
			W=Integer.parseInt(st.nextToken());		// 맵의 너비
			
			// 입력 및 맵 초기화
			map=new char[H][W];
			for(int i=0;i<H;i++) {
				String str=in.readLine();
				for(int j=0;j<W;j++) {
					map[i][j]=str.charAt(j);
					// 탱크 위치 기록
					if(map[i][j]=='^'||map[i][j]=='v'||map[i][j]=='<'||map[i][j]=='>') {
						tank_row=i;
						tank_col=j;
					}
				}
			}
			
			int N=Integer.parseInt(in.readLine()); // 사용자가 넣을 입력의 개수
			String command=in.readLine(); // 길이가 N인 문자열(명령어)
			
			// 명령 시작
			for(int i=0;i<N;i++) {
				play(command.charAt(i));
			}
			
			// 출력
			System.out.print("#"+test_case+" ");
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
		
	}
	
	private static void play(char c) {
		//	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
		if(c=='U') {
			map[tank_row][tank_col]='^';
			if(tank_row!=0) { // 탱크의 행 위치가 0이 아닐 경우
				if(map[tank_row-1][tank_col]=='.') { // 한칸 위가 평지일 경우
					tank_row-=1;
					map[tank_row][tank_col]=map[tank_row+1][tank_col];
					map[tank_row+1][tank_col]='.'; // 지나온 곳은 평지로				
				}
			}
		}
		// 	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
		if(c=='D') {
			map[tank_row][tank_col]='v';
			if(tank_row!=H-1) { //  탱크의 행 위치가 맵의 가장 아래가 아닐 경우
				if(map[tank_row+1][tank_col]=='.') { // 한칸 아래가 평지일 경우
					tank_row+=1;
					map[tank_row][tank_col]=map[tank_row-1][tank_col];
					map[tank_row-1][tank_col]='.'; // 지나온 곳은 평지로	
				}
			}
		}
		// Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
		if(c=='L') {
			map[tank_row][tank_col]='<';
			if(tank_col!=0) { //  탱크의 열 위치가 0이 아닐 경우
				if(map[tank_row][tank_col-1]=='.') { // 왼쪽이 평지일 경우
					tank_col-=1;
					map[tank_row][tank_col]=map[tank_row][tank_col+1];
					map[tank_row][tank_col+1]='.'; // 지나온 곳은 평지로	
				}
			}
		}
		// Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
		if(c=='R') {
			map[tank_row][tank_col]='>';
			if(tank_col!=W-1) { //  탱크의 열 위치가 오른쪽 맨 끝이 아닐 경우
				if(map[tank_row][tank_col+1]=='.') { // 오른쪽이 평지일 경우
					tank_col+=1;
					map[tank_row][tank_col]=map[tank_row][tank_col-1];
					map[tank_row][tank_col-1]='.'; // 지나온 곳은 평지로	
				}
			}
		}
		// 	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
		if(c=='S') {
			if(map[tank_row][tank_col]=='<') {
				if(tank_col!=0) {
					int temp=tank_col;
					while(temp>0) {
						temp--;
						if(map[tank_row][temp]=='*') { // 벽돌로 만들어진 벽일 경우
							map[tank_row][temp]='.';
							break;
						}
						if(map[tank_row][temp]=='#') { // 강철로 만들어진 벽일 경우
							break;
						}
					}
				}
			}
			if(map[tank_row][tank_col]=='>') {
				if(tank_col!=W-1) {
					int temp=tank_col;
					while(temp<W-1) {
						temp++;
						if(map[tank_row][temp]=='*') { // 벽돌로 만들어진 벽일 경우
							map[tank_row][temp]='.';
							break;
						}
						if(map[tank_row][temp]=='#') { // 강철로 만들어진 벽일 경우
							break;
						}
					}
				}
			}
			if(map[tank_row][tank_col]=='^') {
				if(tank_row!=0) {
					int temp=tank_row;
					while(temp>0) {
						temp--;
						if(map[temp][tank_col]=='*') { // 벽돌로 만들어진 벽일 경우
							map[temp][tank_col]='.';
							break;
						}
						if(map[temp][tank_col]=='#') { // 강철로 만들어진 벽일 경우
							break;
						}
					}
				}
			}
			if(map[tank_row][tank_col]=='v') {
				if(tank_row!=H-1) {
					int temp=tank_row;
					while(temp<H-1) {
						temp++;
						if(map[temp][tank_col]=='*') { // 벽돌로 만들어진 벽일 경우
							map[temp][tank_col]='.';
							break;
						}
						if(map[temp][tank_col]=='#') { // 강철로 만들어진 벽일 경우
							break;
						}
					}
				}
			}
		}
	}
}