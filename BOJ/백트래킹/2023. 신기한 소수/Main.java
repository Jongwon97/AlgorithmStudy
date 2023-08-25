import java.util.Scanner;

// 왼쪽부터 N자리 수가 모두 소수인 수 구하기
public class Main {

	private static int N;
	public static void main(String args[]){

		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		
		recurs(0,0);
	}
	
	// 재귀로 구현
	private static void recurs(int cnt, int prime) {
		// 소수 검사 -> 소수가 아닐 경우 리턴
		if(!checkValue(prime)) return;
		// N자리의 수일때
		if(cnt==N) {
			System.out.println(prime);
			return;
		}	
		for(int i=0;i<=9;i++) {
			// 0,1은 소수가 아니므로 -> 맨앞이 0이나 1 방지 코드
			if((i==0&&prime==0)||(cnt==0&&i==1)) continue;
			recurs(cnt+1,prime*10+i);
		}
	}
	// 소수 판별 메소드
	// 소수일 경우 true 소수가 아닐 경우 false 반환
    private static boolean checkValue(int temp){
        boolean bo=true;
        for(int i=2;i<temp;i++){
            if(temp%i==0){
                bo=false;
                break;
            }
        }
        return bo;
    }
}