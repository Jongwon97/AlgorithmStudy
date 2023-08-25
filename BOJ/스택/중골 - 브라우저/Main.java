import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException{

		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		
		Stack<String> forward=new Stack<>();
		Stack<String> backward=new Stack<>();
		
		String url="http://www.acm.org/"; // 현재 웹페이지
		while(true) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			String command=st.nextToken();
			if(command.equals("VISIT")) {
				backward.add(url);
				url=st.nextToken();
				forward.clear();
				System.out.println(url);
			}
			if(command.equals("FORWARD")) {
				if(forward.isEmpty()) {
					System.out.println("Ignored");
					continue;
				}
				backward.push(url);
				url=forward.pop();
				System.out.println(url);
			}
			if(command.equals("BACK")) {
				if(backward.isEmpty()) {
					System.out.println("Ignored");
					continue;
				}
				forward.add(url);
				url=backward.pop();
				System.out.println(url);
			}
			if(command.equals("QUIT")) {
				break;
			}
		}
    }
}