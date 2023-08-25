import java.util.Scanner;

// 왼쪽부터 N자리 수가 모두 소수인 수 구하기
/*거리 이동          이동 횟수
 * 1  1               1
 * 2  1 1             2
 * 3  1 1 1           3
 * 4  1 2 1           3
 * 5  1 2 1 1         4
 * 6  1 2 2 1         4
 * 7  1 2 2 1 1       5
 * 8  1 2 2 2 1       5
 * 9  1 2 3 2 1       5
 * 10 1 2 3 2 1 1     6
 * -> 최대 이동거리는 거리를 루트하고 소수점을 제외한 나머지 값
 */

public class Main {

	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		
		int T= sc.nextInt(); 	// 테스크케이스 개수
		for(int i=0;i<T;i++) {
			int x=sc.nextInt();		// 현재 위치
			int y=sc.nextInt();		// 목표 위치
			
			int distance=y-x;  // 이동할 거리
			int max_move=(int)Math.sqrt(distance);  	// 한번 이동할 거리의 최대값
			
			// move_sum은 이동한 거리의 합 (임시) -> max_move가 부족할 수 도 있음
			int move_sum=(max_move*(max_move+1))/2; 	// 1부터 n까지의 합 -> n*(n+1)/2
			move_sum*=2;  		// ex) 123321 의 합
			move_sum-=max_move; // ex) 12321 의 합
			
			int check=x+move_sum;   // 시작위치 + 이동 거리
			
			// 시작위치+이동거리가 목표위치와 같을 때
			// ex) 1 2 3 2 1 이런 방식으로 이동
			if(check==y) {
				System.out.println(2*max_move-1);
			}
			// 같지 않은데 +max_move 한 값이 y보다 작을때 -> 이동거리 안에 max_move 크기보다 작은 이동이 한번 더 있음
			// ex) 1 2 2 3 2 1, 1 2 3 2 1 1
			else if(check+max_move<y) {
				System.out.println(2*max_move+1);
			}
			// 위에서 안 걸렸으면 1 2 3 3 2 1 방식의 이동
			else {
				System.out.println(2*max_move);
			}						
		}
    }
}