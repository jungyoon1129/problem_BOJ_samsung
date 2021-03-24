package gear_14891;
import java.util.*;
import java.io.*;

/*
 * deque�� ��� Ȱ��
 * 
 * ����
 * https://mygumi.tistory.com/306
 */
public class Gear_14891 {
	static int point12=2;
	static int point21=6;
	static int point23=2;
	static int point32=6, point34=2, point43=6;
	static int arr1[], arr2[], arr3[], arr4[];
	static LinkedList<Integer> list[];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		list = new LinkedList[5];
		for(int i=1; i < list.length; i++) {
			list[i] = new LinkedList<>();
			String str = br.readLine();
			for(int j = 0; j < 8; j++)
				list[i].add(str.charAt(j)-'0');
		}
	
		
		int k = Integer.parseInt(br.readLine());
		int arr[][] = new int[k][2];
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		int start, dir;
		int temp;
		
		for(int i =0; i < arr.length; i++) {
			start = arr[i][0];
			dir = arr[i][1];
			left(start-1, -dir);
			right(start+1, -dir);
			rotate(start, dir);
			
		}
		int sum = 0;
		int point[] = {0,1,2,4,8};
		for(int i = 1; i <= 4; i++) {
			if(list[i].get(0)==1) {
				sum += point[i];
			}
		}
		System.out.println(sum);
	}
	public static void left(int start, int dir) {
		if(start>= 1 && start <= 4) {
			if(!check(start, start+1)) {
				//���� ��ģ �κ��� �����ʴٸ�
				left(start-1, -dir);
				rotate(start, dir);
			}
			else {
				return;
			}
		}
		else {
			return;
		}
	}
	public static void right(int start, int dir) {
		if(start>= 1 && start <= 4) {
			if(!check(start, start-1)) {
				//���� ��ģ �κ��� �����ʴٸ�
				right(start+1, -dir);
				rotate(start, dir);
			}
			else {
				//���� ������ ��ģ��� ȸ������ �ʴ´�.
				return;
			}
		}
		else {
			return;
		}
	}
	public static boolean check(int cur, int next) {
		if(cur < next) {
			//cur�� �����ʰ� next�� ������ ��� �κ� �˻�
			if(list[cur].get(2) == list[next].get(6)) {
				return true;
			}
			else
				return false;
		}
		else {
			if(list[cur].get(6) == list[next].get(2)) {
				return true;
			}
			else
				return false;
		}
	}
	
	public static void rotate(int start, int dir) {
		int temp;
		if(dir == 1) {
			//�ð����
			temp = list[start].removeLast();
			list[start].addFirst(temp);
		}
		else {
			//�ݽð����
			temp = list[start].removeFirst();
			list[start].addLast(temp);
		}
	}
	
}
