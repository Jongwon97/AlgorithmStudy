import java.util.Scanner;

public class Main {

	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt(); 	// DNA 문자열의 길이 [S]
		int P=sc.nextInt();		// 비밀번호로 사용할 부분 문자열의 길이
		
		String DNA_str=sc.next();
		char[] ch=DNA_str.toCharArray();
		int[] min_code=new int[4]; // 부분문자열에 포함되어야 할 {A,C,G,T}의 최소 개수
		
		for(int i=0;i<4;i++) {
			min_code[i]=sc.nextInt();
		}

		int total_count=0;
		
		// 한번 먼저 검사
		int index_start=0;
		int index_end=P;
		int[] code_Count=new int[4];
		for(int j=index_start;j<index_end;j++) {
			if(ch[j]=='A') {
				code_Count[0]++;
			}
			else if(ch[j]=='C') {
				code_Count[1]++;
			}
			else if(ch[j]=='G') {
				code_Count[2]++;
			}
			else if(ch[j]=='T') {
				code_Count[3]++;
			}
		}
		// 문자 포함 개수 판별
		boolean check=true;
		for(int j=0;j<4;j++) {
			// 한개라도 최소한의 코드수를 못 맞추면
			if(code_Count[j]<min_code[j]) {
				check=false;
			}
		}
		if(check) {
			total_count++;
		}

		// 맨앞 인덱스를 빼고 맨뒤 인덱스를 더하는 방식으로 반복
		for(int i=1;i<=N-P;i++) {
			// 맨앞 문자 개수 -1
			if(ch[index_start]=='A') {
				code_Count[0]--;
			}
			else if(ch[index_start]=='C') {
				code_Count[1]--;
			}
			else if(ch[index_start]=='G') {
				code_Count[2]--;
			}
			else if(ch[index_start]=='T') {
				code_Count[3]--;
			}
			index_start++;
			// 맨뒤 문자 개수 +1
			if(ch[index_end]=='A') {
				code_Count[0]++;
			}
			else if(ch[index_end]=='C') {
				code_Count[1]++;
			}
			else if(ch[index_end]=='G') {
				code_Count[2]++;
			}
			else if(ch[index_end]=='T') {
				code_Count[3]++;
			}
			index_end++;
			// 문자 포함 개수 판별
			check=true;
			for(int j=0;j<4;j++) {
				// 한개라도 최소한의 코드수를 못맞추면
				if(code_Count[j]<min_code[j]) {
					check=false;
				}
			}
			if(check) {
				total_count++;
			}
		}
		System.out.println(total_count);
	}
}