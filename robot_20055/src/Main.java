import java.util.*;
import java.io.*;

public class Main {
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[2*n]; 
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int robotinlist[] = new int[2*n];
		int start = 0;
		int end = start + n-1;

		ArrayList<Integer> robot = new ArrayList<>();

		
		int time =0;
		while(true) {
			//ȸ��
			//end�� �ִ��� ����������			
			int t,index;
			
			start = (start-1+2*n)%(2*n);
			end = (start + n-1)%(2*n);
			
			while(robotinlist[end]!=0) {
				robotinlist[end]--;
				robot.remove(0);
				//ù��°�� ���°� end�� �����״ϱ� ����
			}
			
			int nextindex;
			//ó�� ���� �κ� ������ �κ��� ��ĭ �̵���ų �� �ִٸ� �̵���Ŵ
			for(int i =0; i < robot.size(); i++) {
				t = robot.get(i); //t�� �κ��� ��ġ �ε���
				
				nextindex = (t + 1) % arr.length;
				
				if(nextindex == (end+1)%arr.length || robotinlist[nextindex]!=0|| arr[nextindex] < 1) {
					//�̵� �Ұ���
					continue;
				}
				else {
					if(nextindex ==end) {
						robot.remove(i);
						i = i-1;
						
					}
					else {
						robot.set(i, nextindex);
						robotinlist[nextindex]++;
					}

					arr[nextindex]--;
					robotinlist[t]--;
					
					
				}
			}
			//�κ� �ϳ��� �ø�
			//�������� 0�� �ƴ϶��
			if(arr[start]>=1) {
				arr[start]--;
				robot.add(start);
				robotinlist[start]++;
			}
			time++;
			//�������� 0�� ����
			int num = search();
			if(num >= k)
				break;
			
			
		}
		System.out.println(time);
	}
	
	public static int search() {
		int count =0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]==0)
				count++;
		}
		return count;
	}
}
