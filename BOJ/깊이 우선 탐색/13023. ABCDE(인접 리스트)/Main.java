import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N,M;
	private static int answer=0;

	private static Node[] adjList;
	public static void main(String args[]) throws IOException{

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");

		N=Integer.parseInt(st.nextToken());  // 사람의 수
		M=Integer.parseInt(st.nextToken());  // 친구 관계의 수

		adjList=new Node[N];
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(in.readLine()," ");
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());	
			adjList[from]=new Node(to,adjList[from]);
			adjList[to]=new Node(from,adjList[to]);
		}
		
		for(int i=0;i<N;i++) {
			if(answer==1) {
				break;
			}
			if(adjList[i]!=null) {
				dfs(0,i,new boolean[N]);
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int cnt, int cur,boolean[] visited) {
		if(answer==1) {
			return;
		}
		if(cnt==4) {
			answer=1;
			return;
		}
		visited[cur]=true;
		for(Node temp=adjList[cur];temp!=null;temp=temp.link) {
			if(!visited[temp.vertex]) {
				dfs(cnt+1,temp.vertex,visited);
			}
		}
		visited[cur]=false;
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