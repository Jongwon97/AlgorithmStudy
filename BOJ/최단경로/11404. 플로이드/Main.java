import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(in.readLine()); 		// 도시의 개수
		int m=Integer.parseInt(in.readLine());		// 버스의 개수

		final int INF=10000001;
		int[][] distance=new int[n+1][n+1];
		StringTokenizer st;
		int a,b,c;
		
		// 0인 곳들 INF로 초기화
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i!=j) distance[i][j]=INF;
			}
		}
		
		// 입력
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(in.readLine()," ");
			a=Integer.parseInt(st.nextToken()); // 시작 도시
			b=Integer.parseInt(st.nextToken()); // 도착 도시
			c=Integer.parseInt(st.nextToken()); // 한 번 타는데 필요한 비용
			// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있으므로
			distance[a][b]=Math.min(distance[a][b], c);
		}
		
		// 경유지 -> 출발지 -> 도착지
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					// 출발지->경유지, 경유지->도착지가 연결되어있고, 원래 걸리는 값보다 작은 경우
					if(distance[i][k]!=INF && distance[k][j]!=INF && distance[i][j] > distance[i][k]+distance[k][j]) {
						distance[i][j] = distance[i][k]+distance[k][j];
					}
				}
			}
		}
		
		// 출력
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(distance[i][j]>=INF) {
					sb.append("0 ");
				}
				else {
					sb.append(distance[i][j]+" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}