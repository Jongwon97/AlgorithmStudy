import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
 
	static int N,M;
	static Node[] list;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws IOException {
 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		N=Integer.parseInt(st.nextToken()); // 정점의 개수
		M=Integer.parseInt(st.nextToken()); // 간선의 개수

		
		list=new Node[N+1]; //head를 모두 null로 초기화
		int from,to;
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(in.readLine()," ");
			from=Integer.parseInt(st.nextToken());
			to=Integer.parseInt(st.nextToken());
			list[from]=new Node(to,list[from]);
			list[to]=new Node(from,list[to]);
		}
		
		int answer=0;
		visited=new boolean[N+1];
		for(int i=1;i<=N;i++) {
			if(!visited[i]) {
				dfs(i);
				answer++;
			}	
		}
		System.out.println(answer);
		
	}
 
	private static void dfs(int n) {
		visited[n]=true;

		for(Node temp=list[n];temp!=null;temp=temp.link) {
			if(!visited[temp.vertex]) {
				dfs(temp.vertex);
			}
		}
		
	}
}

class Node{
	int vertex;
	Node link;
	public Node(int vertex, Node link) {
		super();
		this.vertex = vertex;
		this.link = link;
	}
}

