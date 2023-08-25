import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String text=in.readLine();
		String bomb=in.readLine();

		int text_size=text.length();
		int bomb_size=bomb.length();
		StringBuilder sb=new StringBuilder();
		
		// 문자열 전체 길이만큼 반복
		for(int i=0;i<text_size;i++) {
			sb.append(text.charAt(i));
			// 폭발 문자열 길이만큼 반복
			if(sb.length()>=bomb_size) {
				boolean flag=true;
				for(int j=0;j<bomb_size;j++) {
					// StringBuilder의 charAt() 메서드 -> 해당 인덱스 위치의 문자 반환
					char ch=sb.charAt(sb.length()-bomb_size+j);
					if(ch!=bomb.charAt(j)) {
						flag=false;
						break;
					}
				}
				if(flag) {
					// StringBuilder의 delete() 메서드 -> 해당 인덱스 사이의 문자열 제거
					sb.delete(sb.length()-bomb_size,sb.length()); //
				}
			}
		}
		System.out.println(sb.length()!=0?sb:"FRULA");
	}
}