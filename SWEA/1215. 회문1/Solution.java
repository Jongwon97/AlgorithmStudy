import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 총 10개의 텡스트 케이스
		// 8x8 크기 글자판
		for(int test_case = 1; test_case <= 10; test_case++) {
			int N=sc.nextInt();  // 회문의 길이
			char[][] puzzle=new char[8][8]; // 글자판
			int answer=0;
			
			// 글자판 초기화
			for(int i=0;i<puzzle.length;i++) {
				String str=sc.next();
				for(int j=0;j<puzzle.length;j++) {
					puzzle[i][j]=str.charAt(j);
				}
			}
			
			// 가로 회문 탐색
			for(int i=0;i<puzzle.length;i++) {
				int start_index=0;     // 비교할 때 시작 인덱스
				int end_index=N-1;     // 비교할 때 마지막 인덱스
				boolean bo=true;
				
				// 홀수일 경우 가운데는 셀필요 없음
				for(int k=0;k<puzzle.length-N+1;k++) {    // 행에서 반복할 수 -> ex) 8x8에서 N=4 이므로 한 행당 5번 탐색
					for(int j=0;j<N/2;j++) {   // 회문 탐색
						if(puzzle[i][start_index+j]!=puzzle[i][end_index-j]) {
							bo=false;
							break;
						}
					}
					if(bo) {
						answer++;
					}
					else {
						bo=true;
					}
					start_index++;
					end_index++;
				}
			}
			// 세로 회문 탐색
			for(int i=0;i<8;i++) {
				int start_index=0;
				int end_index=N-1;
				boolean bo=true;
				
				// 홀수일 경우 가운데는 셀필요 없음
				for(int k=0;k<puzzle.length-N+1;k++) {  // 열에서 반복할 수 -> ex) 8x8에서 N=4 이므로 한 열당 5번 탐색
					for(int j=0;j<N/2;j++) {
						if(puzzle[start_index+j][i]!=puzzle[end_index-j][i]) {
							bo=false;
							break;
						}
					}
					if(bo) {
						answer++;
					}
					else {
						bo=true;
					}
					start_index++;
					end_index++;
				}
			}
			// 출력
			System.out.println("#"+test_case+" "+answer);
			}			
		}
	}
