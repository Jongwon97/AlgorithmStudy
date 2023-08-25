import java.util.Scanner;

public class Main {

	
	public static void main(String args[]) {
		
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();   // 스위치 개수
		int[] sw=new int[N+1];  // 스위치, 번호 1부터
		
		// 스위치 초기화, 켜진 스위치 1, 꺼진 스위치 0
		for(int i=1;i<=N;i++) {
			sw[i]=sc.nextInt();
		}
		int student_num=sc.nextInt(); //학생 수
		int[][] students=new int[student_num][2];
		
		for(int i=0;i<student_num;i++) {
			students[i][0]=sc.nextInt();	// 성별: 남학생1, 여학생 2
			students[i][1]=sc.nextInt();	// 학생이 받은 수
		}
		
		// 알고리즘
		for(int i=0;i<student_num;i++) { // 학생 수 만큼 반복
			// 남학생일 때
			if(students[i][0]==1) {
				int temp=1;
				for(int j=1;j<=N;j++) {
					if(students[i][1]*temp==j) {
						if(sw[j]==1) {
							sw[j]=0;
						}
						else {
							sw[j]=1;
						}
						temp++;
					}
				}
			}
			// 여학생일 때
			else {
				int end_index=students[i][1];
				int start_index=students[i][1];
				while(true) {
					end_index++;
					start_index--;
					if(start_index==0||end_index==N+1||sw[start_index]!=sw[end_index]) {
						end_index--;
						start_index++;
						break;
					}
				}
				for(int j=start_index;j<=end_index;j++) {
						if(sw[j]==1) {
							sw[j]=0;
						}
						else {
							sw[j]=1;
						}
				}
				
			}
		}
		
		for(int i=1;i<=N;i++) {
			System.out.print(sw[i]+" ");
		}
		
	}


}
