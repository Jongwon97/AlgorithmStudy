import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int r;
	private static int c;

	private static int count=0;
	
	public static void main(String args[]) throws IOException{
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); 	// 2^N x 2^N 배열의 크기
		r=Integer.parseInt(st.nextToken()); 	// 행 위치
		c=Integer.parseInt(st.nextToken()); 	// 열 위치

		divide((int)Math.pow(2, N),r,c);
		System.out.println(count);
	}
	
	private static void divide(int size,int r, int c) {
		if(size==1) {
			return;
		}
		
		int half=size/2;
		// 1사분면일 경우
		if(r<half&&c<half) {
			divide(half,r,c);
		}
		// 2사분면일 경우
		if(r<half&&c>=half) {
			count+=half*half;
			divide(half,r,c-half);
		}
		// 3사분면일 경우
		if(r>=half&&c<half) {
			count+=half*half*2;
			divide(half,r-half,c);
		}
		// 4사분면일 경우
		if(r>=half&&c>=half) {
			count+=half*half*3;
			divide(half,r-half,c-half);
		}
	}
}