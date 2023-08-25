import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{

		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());

		ArrayList<Integer> list=new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			list.add(i);
		}
		
		int index=K-1; // 인덱스는 0부터 시작

		int temp=N;
		ArrayList<Integer> list2=new ArrayList<>();
		
		for(int i=0;i<temp;i++) {
			list2.add(list.get(index));
			list.remove(index);
			N--;
			if(N==0) {
				break;
			}
			index+=K-1;
			if(index>N-1) {
				index%=N;
			}
		}
		
		System.out.print("<");
		for(int i=0;i<list2.size();i++) {
			if(i==list2.size()-1) {
				System.out.print(list2.get(i));
				continue;
			}
			System.out.print(list2.get(i)+", ");
		}
		System.out.print(">");
		
	}	
}