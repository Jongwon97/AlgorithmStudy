import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
  
/* 
 4x4 크기의 격자판, 0~9사이의 숫자가 적혀있음
 동서남북 격자로 총 6번 이동, 숫자를 이어 붙이면 7자리의 숫자가 완성됨
 이동은 갔던 위치 다시 가도됨, 격자판을 벗어나는것은 안됨
 서로 다른 7자리 수들의 개수를 구하는 프로그램을 작성
 */
 
public class Solution {
     
    private static int N=4; // NxN 격자판 크기
    private static int M=6;
    private static int[][] puzzle;
    private static Set<String> st;
    private static StringBuilder sb;
     
    // 동서남북 이동
    private static int[] dx= {0,1,0,-1};
    private static int[] dy= {1,0,-1,0};
 
    public static void main(String args[]) throws Exception{
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(); // 테스트 케이스 수
         
        for(int test_case = 1; test_case <= T; test_case++) {
            // 격자판 초기화
            puzzle=new int[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    puzzle[i][j]=sc.nextInt();
                }
            }
             
            st=new HashSet<>();
            sb=new StringBuilder(); // 문자열 덧셈 연산은 오버헤드가 발생하므로 StringBuilder 사용!
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    permutation(i, j, 0);
                }
            }
            // 출력
            System.out.println("#"+test_case+" "+st.size());
        }       
    }   
     
    // 중복 순열
    private static void permutation(int index_x,int index_y, int cnt) { // cnt: 현재 이동한 횟수
        // 6번 이동해야함. -> 7번째 방문때 숫자가 완성되어 있음
        if(cnt==7) {
            st.add(sb.toString());      // set이기 때문에 중복된 값일 경우 자동으로 입력 x
            return;
        }
        sb.append(Integer.toString(puzzle[index_x][index_y]));      // 문자열에 추가
        for(int i=0;i<4;i++) { 
            // 벽을 넘어가는 경우 continue
            if(index_x+dx[i]<0||index_x+dx[i]>=N||index_y+dy[i]<0||index_y+dy[i]>=N) continue;
            permutation(index_x+dx[i],index_y+dy[i], cnt+1);            // 순열 탐색
        }
        sb.setLength(sb.length()-1);                                // 추가했던 문자열 삭제
    }
}