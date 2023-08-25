import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int C,P;
	static int[] map;
	static int answer;
	
	public static void main(String args[]) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		C=Integer.parseInt(st.nextToken()); // 필드의 열의 수
		P=Integer.parseInt(st.nextToken()); // 블록의 번호
		
		map=new int[C];
		st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<C;i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		start();
		System.out.println(answer);
	}
	
	private static void start() {
		switch(P) {
			case 1:
				answer=C;
				play(new int[] {0,0,0,0});
				break;
			case 2:
				play(new int[] {0,0});
				break;
			case 3:
				play(new int[] {0,0,1});
				play(new int[] {1,0});
				break;
			case 4:
				play(new int[] {1,0,0});
				play(new int[] {0,1});
				break;
			case 5:
				play(new int[] {0,0,0});
				play(new int[] {0,1});
				play(new int[] {1,0,1});
				play(new int[] {1,0});
				break;
			case 6:
				play(new int[] {0,0,0});
				play(new int[] {0,0});
				play(new int[] {0,1,1});
				play(new int[] {2,0});
				break;
			case 7:
				play(new int[] {0,0,0});
				play(new int[] {0,2});
				play(new int[] {1,1,0});
				play(new int[] {0,0});
				break;
		}
	}
	private static void play(int[] block) {
		int size=block.length;
		for(int i=0;i<=C-size;i++) {
			int temp=map[i]-block[0];
			boolean flag=true;
			for(int j=0;j<size;j++) {
				if(temp!=map[i+j]-block[j]) {
					flag=false;
					break;
				}
			}
			if(flag) answer++;
		}
	}
}