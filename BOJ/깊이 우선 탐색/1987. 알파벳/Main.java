import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	private static int R,C;
	private static char[][] arr; // 알파벳을 저장할 문자배열
	
	private static int[] dr= {0,1,0,-1};
	private static int[] dc= {1,0,-1,0};
	
	private static boolean[][] visited;
	private static Set<Character> set;
	
	private static int answer;
	public static void main(String args[]) throws IOException {
		
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		arr=new char[R][C];		
		for(int i=0;i<R;i++) {			
			arr[i]=in.readLine().toCharArray();
		}
		visited=new boolean[R][C];
		set=new HashSet<>();
		dfs(0,0,1); // 시작 칸도 포함하므로 cnt -> 1
		
		System.out.println(answer);
	}
	
	private static void dfs(int row, int col,int cnt) {
		visited[row][col]=true;	
		if(!set.add(arr[row][col])) { // 중복되는 값이 들어올 경우 false 반환
			visited[row][col]=false;
			return;
		}
		answer=Math.max(answer, cnt);
		for(int i=0;i<4;i++) {
			int tr=row+dr[i];
			int tc=col+dc[i];
			if(tr<0||tr>=R||tc<0||tc>=C) continue;
			if(!visited[tr][tc]) {
				dfs(tr,tc,cnt+1);
			}
		}
		visited[row][col]=false;
		set.remove(arr[row][col]);
	}
}