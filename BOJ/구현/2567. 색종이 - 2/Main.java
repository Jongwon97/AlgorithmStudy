import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	
	static int[][] map;
	public static void main(String args[]) throws IOException {		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());  // 색종이의 수
		map=new int[101][101];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			int col=Integer.parseInt(st.nextToken());
			int row=Integer.parseInt(st.nextToken());
			for(int j=row;j<row+10;j++) {
				for(int k=col;k<col+10;k++) {
					map[j][k]=1;
				}
			}
		}
		
		int answer=0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]!=map[i][j+1]) answer++;
				if(map[j][i]!=map[j+1][i]) answer++;
			}
		}
		System.out.println(answer);
		
//		for(int i=0;i<100;i++) {
//			for(int j=0;j<100;j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
	}
}