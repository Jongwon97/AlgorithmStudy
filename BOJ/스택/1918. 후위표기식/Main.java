import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	/*
	 *  '('가 나오면 스택에 넣음.
	 *  ')'가 나오면 '('를 만날때 까지 pop해서 출력함.
	 *  연산자가 나오면 넣으려는 연산자보다 우선순위가 높거나 같은 연산자를 모두 pop해서 출력함. 그리고 해당 연산자를 스택에 push함
	 *  모든 문자를 처리한 후, 스택에 남은 연산자 pop후 출력 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String str=in.readLine(); //입력
		
		Stack<Character> stk=new Stack<>();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<str.length();i++) {
			char temp=str.charAt(i);
			if(temp>='A' && temp<='Z') { // 알파벳들은 바로 출력함.
				sb.append(temp);
			}
			else if(temp=='(') {
				stk.push(temp);
			}
			else if(temp==')') {
				while(!stk.isEmpty()) {
					if(stk.peek()=='(') {
						stk.pop();
						break;
					}
					sb.append(stk.pop());
				}
			}
			// 연산일 경우 (+,-,*,/)
			else {
				// 현재의 연산 보다 우선순위가 높거나 같을경우 pop하고 출력
				while(!stk.isEmpty() && priority(stk.peek())>=priority(temp)) {
					sb.append(stk.pop());
				}
				stk.push(temp);
			}
		}
		// 스택에 남아있는 연산 출력
		while(!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		System.out.println(sb);
	}
	
    public static int priority(char ch) {
        if (ch=='*'||ch=='/') {
        	return 2;
        }
        else if(ch=='+'||ch=='-'){
        	return 1;
        }
        else { // '(' 는 꺼내지면 안되므로 우선순위가 가장 낮음.
        	return 0;
        }
    }

}
