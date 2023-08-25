import java.util.Arrays;
import java.util.Scanner;
  
public class Solution {
 
    public static void main(String args[]) throws Exception{
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt(); // 테스트 케이스 수
         
        boolean[] answer=new boolean[T+1];
         
        for(int test_case = 1; test_case <= T; test_case++) {
            int N=sc.nextInt();    // 정수의 개수
             
            int[] arr=new int[N];
            for(int i=0;i<N;i++) {
                arr[i]=sc.nextInt();
            }
            Arrays.sort(arr);   // 오름차순 정렬
             
            boolean checked=true;
            int temp=arr[0];
            // 수열의 앞과 뒤의 수를 비교함, 뒤에수가 앞의수+1이 아니면 순열이 아님
            for(int i=1;i<N;i++) {
                if((temp+1)==arr[i]) {
                    temp=arr[i];
                }
                else {
                    checked=false;
                    break;
                }
            }
            // 순열 o
            if(checked) {
                answer[test_case]=true;
            }
            // 순열 x
            else {
                answer[test_case]=false;
            }
        }
         
        // 출력
        for(int i=1;i<=T;i++) {
            if(answer[i]) {
                System.out.println("#"+i+" Yes");
            }
            else {
                System.out.println("#"+i+" No");
            }
        }
    }
}