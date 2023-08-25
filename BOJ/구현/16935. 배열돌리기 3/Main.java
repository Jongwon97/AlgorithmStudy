import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// N,M은 짝수
	private static int N,M,R;
	private static int[][] arr;
	
	public static void main(String args[]) throws IOException{
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());  	// 배열의 크기 N
		M=Integer.parseInt(st.nextToken()); 	// 배열의 크기 M
		R=Integer.parseInt(st.nextToken()); 	// 수행해야 하는 연산의 수
		
		// 배열 입력
		arr=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(in.readLine()," ");
			for(int j=0;j<M;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		// R번 연산 수행
		st=new StringTokenizer(in.readLine()," ");
		for(int i=0;i<R;i++) {
			switch(Integer.parseInt(st.nextToken())) {
			case 1:
				count1();
				break;
			case 2:
				count2();
				break;
			case 3:
				count3();
				break;
			case 4:
				count4();
				break;
			case 5:
				count5();
				break;
			case 6:
				count6();
				break;
			}
		}

		// 출력
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	// 상하 반전시키는 연산
	private static void count1() {
		int temp=0;
		// i는 행 인덱스
		for(int i=0;i<N/2;i++) {
			// j는 열 인덱스
			for(int j=0;j<M;j++) {
				temp=arr[i][j];
				arr[i][j]=arr[N-1-i][j];
				arr[N-1-i][j]=temp;
			}
		}
	}
	// 좌우 반전시키는 연산
	private static void count2() {
		int temp=0;
		// i는 열 인덱스
		for(int i=0;i<M/2;i++) {
			// j는 행 인덱스
			for(int j=0;j<N;j++) {
				temp=arr[j][i];
				arr[j][i]=arr[j][M-1-i];
				arr[j][M-1-i]=temp;
			}
		}
	}
	// 오른쪽으로 90도 회전시키는 연산
	private static void count3() {
		int[][] arr2=new int[M][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr2[j][N-1-i]=arr[i][j];
			}
		}
		arr=arr2;
		int temp=N;
		N=M;
		M=temp;
	}
	// 왼쪽으로 90도 회전시키는 연산
	private static void count4() {
		int[][] arr2=new int[M][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				arr2[M-1-j][i]=arr[i][j];
			}
		}
		arr=arr2;
		int temp=N;
		N=M;
		M=temp;
	}
	
	/*
	 * 1 2
	 * 4 3
	 */
	private static void count5() {
		int[][] arr2=new int[N/2][M/2]; // 임시로 저장할 부분 배열
		int[][] arr3=new int[N][M];     // 새로 저장할 배열
		// 1번 ->2번 옮기기
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<M/2;j++) {
				arr2[i][j]=arr[i][j];
			}
		}
		for(int i=0;i<N/2;i++) {
			for(int j=M/2;j<M;j++) {
				arr3[i][j]=arr2[i][j-M/2];
			}
		}
		// 2번 ->3번 옮기기
		for(int i=0;i<N/2;i++) {
			for(int j=M/2;j<M;j++) {
				arr2[i][j-M/2]=arr[i][j];
			}
		}
		for(int i=N/2;i<N;i++) {
			for(int j=M/2;j<M;j++) {
				arr3[i][j]=arr2[i-N/2][j-M/2];
			}
		}
		// 3번 ->4번 옮기기
		for(int i=N/2;i<N;i++) {
			for(int j=M/2;j<M;j++) {
				arr2[i-N/2][j-M/2]=arr[i][j];
			}
		}
		for(int i=N/2;i<N;i++) {
			for(int j=0;j<M/2;j++) {
				arr3[i][j]=arr2[i-N/2][j];
			}
		}
		// 4번 ->1번 옮기기
		for(int i=N/2;i<N;i++) {
			for(int j=0;j<M/2;j++) {
				arr2[i-N/2][j]=arr[i][j];
			}
		}
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<M/2;j++) {
				arr3[i][j]=arr2[i][j];
			}
		}
		arr=arr3;
	}
	
	private static void count6() {
		
		int[][] arr2=new int[N/2][M/2]; // 임시로 저장할 부분 배열
		int[][] arr3=new int[N][M];     // 새로 저장할 배열
		// 1번 -> 4번 옮기기
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<M/2;j++) {
				arr2[i][j]=arr[i][j];
			}
		}
		for(int i=N/2;i<N;i++) {
			for(int j=0;j<M/2;j++) {
				arr3[i][j]=arr2[i-N/2][j];
			}
		}
		// 2번 -> 1번 옮기기
		for(int i=0;i<N/2;i++) {
			for(int j=M/2;j<M;j++) {
				arr2[i][j-M/2]=arr[i][j];
			}
		}
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<M/2;j++) {
				arr3[i][j]=arr2[i][j];
			}
		}
		// 3번 -> 2번 옮기기
		for(int i=N/2;i<N;i++) {
			for(int j=M/2;j<M;j++) {
				arr2[i-N/2][j-M/2]=arr[i][j];
			}
		}
		for(int i=0;i<N/2;i++) {
			for(int j=M/2;j<M;j++) {
				arr3[i][j]=arr2[i][j-M/2];
			}
		}
		// 4번 -> 3번 옮기기
		for(int i=N/2;i<N;i++) {
			for(int j=0;j<M/2;j++) {
				arr2[i-N/2][j]=arr[i][j];
			}
		}
		for(int i=N/2;i<N;i++) {
			for(int j=M/2;j<M;j++) {
				arr3[i][j]=arr2[i-N/2][j-M/2];
			}
		}
		arr=arr3;
	}
}