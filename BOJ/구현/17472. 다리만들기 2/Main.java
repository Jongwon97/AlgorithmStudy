import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// step 1,2 사용 변수
	static int N,M;
	static int[][] map; // 0: 바다, 1: 땅
	static int nums; // 단지수
	static boolean[][] visited;
	
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	static int[][] graph;
	
	// step3 사용 변수
    static Edge[] edgeList;
    static int[] parents;
    static int V,E;
	
	public static void main(String args[]) throws IOException{
		BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 맵 초기화
		map=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// step1: 각 섬에 번호 붙이기
		visited=new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j]) continue;
				if(map[i][j]!=0) {
					nums++;
					bfs(i,j);
				}
			}
		}
		
		// step2: 서로 연결된 섬 번호와 거리 구하기
		final int INF=Integer.MAX_VALUE;
		graph=new int[nums+1][nums+1];
		for(int i=1;i<=nums;i++) {
			for(int j=1;j<=nums;j++) {
				graph[i][j]=INF;
			}
		}
		findEdge();
		
		// step3: 크루스칼을 사용하여 MST 생성
		V=nums;
		E=nums*nums;
		
		edgeList=new Edge[E-nums];
		int cnt=0;
		for(int i=1;i<=V;i++) {
			for(int j=1;j<=V;j++) {
				if(i!=j) {
					edgeList[cnt]=new Edge(i,j,graph[i][j]);
					cnt++;
				}
			}
		}
		Arrays.sort(edgeList);
		
		makeSet();
		int result=0, count=0;
		
		for(Edge edge: edgeList) {
			if(union(edge.from, edge.to)) {
				if(edge.weight!=INF) {
					result+=edge.weight;
					if(++count==V-1) break;
				}
			}
		}
		if(count<V-1) {
			System.out.println(-1);
		}
		else {
			System.out.println(result);
		}
//		for(int i=1;i<=nums;i++) {
//			for(int j=1;j<=nums;j++) {
//				if(graph[i][j]!=INF) {
//					System.out.print(graph[i][j]);
//				}
//				else {
//					System.out.print(0);
//				}
//			}
//			System.out.println();
//		}
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
	}

	// step1: 섬에 번호 붙이는 메서드
	private static void bfs(int row, int col) {
		visited[row][col]=true;
		map[row][col]=nums;
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {row,col});
		
		int[] temp;
		while(!queue.isEmpty()) {
			temp=queue.poll();
			for(int i=0;i<4;i++) {
				int tr=temp[0]+dr[i];
				int tc=temp[1]+dc[i];
				if(tr<0 || tc<0 || tr>=N || tc>=M || visited[tr][tc]) continue;
				if(map[tr][tc]!=0) {
					visited[tr][tc]=true;
					map[tr][tc]=nums;
					queue.offer(new int[] {tr,tc});
				}
			}
		}
	}
	// step2: 연결된 섬 번호와 거리 구하는 메서드
	private static void findEdge() {	
		boolean flag=false;
		int col=0;
		int island_num=0;
		
		// 가로로 검사
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(flag && map[i][j]==0) {
					if(j+1>=M) continue;
					if(map[i][j+1]==island_num) col=j;
				}
				if(flag && map[i][j]==island_num) {
					col++;
				}
				// 한 행에서 섬을 처음만나고 값이 0이 아닐 경우
				if(!flag && map[i][j]!=0) {
					flag=true;
					col=j;
					island_num=map[i][j];
				}
				if(flag && map[i][j]!=0 && map[i][j] != island_num) {
					int d=j-col-1; // 섬 사이의 거리
					if(d<2) {
						col=j;
						island_num=map[i][j];
						continue;
					}
					// 이전에 기록한 거리보다 작을 경우 다리 교체
					if(graph[island_num][map[i][j]]>d) {
						graph[island_num][map[i][j]]=d;
						graph[map[i][j]][island_num]=d;
					}
					col=j;
					island_num=map[i][j];
				}
			}
			island_num=0;
			flag=false;
		}
		
		// 세로로 검사
		int row=0;
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(flag && map[j][i]==0) {
					if(j+1>=N) continue;
					if(map[j+1][i]==island_num) row=j;
				}
				if(flag && map[j][i]==island_num) {
					row++;
				}
				if(!flag && map[j][i]!=0) {
					flag=true;
					row=j;
					island_num=map[j][i];
				}
				if(flag && map[j][i]!=0 && map[j][i] != island_num) {
					int d=j-row-1; // 섬 사이의 거리
					if(d<2) {
						row=j;
						island_num=map[j][i];
						continue;
					}
					// 이전에 기록한 거리보다 작을 경우 다리 교체
					if(graph[island_num][map[j][i]]>d) {
						graph[island_num][map[j][i]]=d;
						graph[map[j][i]][island_num]=d;
					}
					row=j;
					island_num=map[j][i];
				}
			}
			island_num=0;
			flag=false;
		}
	}
	
	// step3
	static void makeSet() {
		parents=new int[V+1];
		for(int i=1;i<=V;i++) {
			parents[i]=i;
		}
	}
	static int findSet(int a) {
		if(a==parents[a]) return a;
		return parents[a]=findSet(parents[a]);
	}
	static boolean union(int a,int b) {
		int aRoot=findSet(a);
		int bRoot=findSet(b);
		if(aRoot==bRoot) return false;
		parents[bRoot]=aRoot;
		return true;
	}
}

class Edge implements Comparable<Edge>{
	int from, to, weight;	
	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}