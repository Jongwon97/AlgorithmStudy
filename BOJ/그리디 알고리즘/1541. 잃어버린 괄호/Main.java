import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		
		// 연산자를 제외하고 숫자만 배열에 저장
		String[] split=str.split("\\+|-");
		
		// 입력된 문자열에서 +, - 개수를 센 후, 순서대로 문자 배열에 입력
		int count=0;    // count는 연산자 개수 저장
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='-'||str.charAt(i)=='+') {
				count++;
			}
		}
		
		char[] ch=new char[count];
		int num=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='-'||str.charAt(i)=='+') {
				ch[num]=str.charAt(i);
				num++;
			}
		}   // 입력받은 문자열에서 연산자가 순서대로 문자 배열에 입력된 상태
		
		
		// 알고리즘 구현
		// -를 만나기전까진 전부 +하고, -를 만나면 계속 - 연산
		int answer=Integer.parseInt(split[0]);  // 맨 처음 문자는 숫자이므로
		boolean bo=true;
		// 연산자 개수만큼 반복, 연산자 개수 = 숫자 개수 -1
		for(int i=0;i<ch.length;i++) {
			// -를 만났을 경우, 계속 - 연산
			if(ch[i]=='-') {
				bo=false;
			}
			if(bo) {
				answer+=Integer.parseInt(split[i+1]);
			}
			else {
				answer-=Integer.parseInt(split[i+1]);
			}
		}	
	
		System.out.println(answer);
		
	}
}
