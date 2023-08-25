import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(in.readLine());
		
		StringTokenizer st=new StringTokenizer(in.readLine()," ");

		Stack<Tower> stk=new Stack<>();
		
		// 입력한 타워 개수만큼 반복
		for(int i=1;i<=N;i++) {
			int top=Integer.parseInt(st.nextToken()); // 새로 입력한 타워 높이
			// 스택이 비어있는 경우
			if(stk.isEmpty()) {
				stk.push(new Tower(i,top)); // 스택에 추가
				System.out.print("0 ");
			}
			else { // 스택이 비어있지 않았을 경우
				while(!stk.isEmpty()) {
					Tower tower=stk.peek();
					if(tower.height>=top) {
						System.out.print(tower.num+" ");
						stk.push(new Tower(i,top));
						break;
					}
					else {
						stk.pop();
						if(stk.isEmpty()) {
							System.out.print("0 ");
							stk.push(new Tower(i,top));
							break;
						}
					}

				}
			}
		}
	}
}

class Tower{
	int num;    // 탑의 번호
	int height; // 탑의 높이
	public Tower(int num, int height) {
		super();
		this.num = num;
		this.height = height;
	}
}