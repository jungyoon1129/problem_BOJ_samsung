import java.util.*;
import java.io.*;
class info implements Comparable<info>{
	int num,val;
	public info(int val,int num) {
		this.val = val;
		this.num = num;
	}
	@Override
	public int compareTo(info o) {
		// TODO Auto-generated method stub
		if(this.num == o.num) {
			return this.val - o.val;
		}
		else
			return this.num - o.num;
	}
}
public class D2_ArraySorting_17140 {
	static int arr[][] = new int[100][100];
	static int row = 3;
	static int col = 3;
	static int calc[] = new int[101];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int rowSize = 3;
		int colSize = 3;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < rowSize; i++) {
			//list[i] = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < colSize; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int time = 0;
		boolean find = false;
		while(time != 101) {
			if(arr[r-1][c-1] == k) {
				find =true;
				break;
			}
			if(time == 100) {
				//100�� �Ŀ��� �տ��� �´��� Ȯ���ϰ� �ƴϸ� break
				break;
			}
				
			
			//arr�� �࿬��or������ ����
			LinkedList<info> list = new LinkedList<>();
			if(rowSize >= colSize) {
				//R���� ����, �࿡ ���� ����
				for(int i = 0; i < rowSize; i++) {
					//i��° �࿡ ���ؼ� ����
					for(int j = 0; j < colSize; j++) {
						int temp = arr[i][j];
						if(temp == 0)
							continue;
						arr[i][j] = 0;
						calc[temp]++;
					}
					for(int j = 1; j < calc.length;j++) {
						if(calc[j] != 0) {
							list.add(new info(j, calc[j]));
							calc[j] = 0;
						}
					}
					Collections.sort(list);
					
					int size = (list.size() > 50)?50:list.size();
					colSize = Math.max(colSize, size * 2);
					for(int j = 0; j < size; j++) {
						info temp = list.get(j);
						arr[i][j*2] = temp.val;
						arr[i][j*2+1] = temp.num;
					}
					list.clear();
				}
							
			}
			else {
				//C���� ����
				for(int i = 0; i < colSize; i++) {
					//i��° ���� ���ؼ� ����
					for(int j = 0; j < rowSize; j++) {
						int temp = arr[j][i];					
						if(temp == 0)	continue;
						arr[j][i] = 0;
						calc[temp]++;
					}
					for(int j = 1; j < calc.length; j++) {
						if(calc[j] != 0) {
							list.add(new info(j, calc[j]));
							calc[j] = 0;
						}
					}
					Collections.sort(list);
					
					int size = (list.size() > 50)?50:list.size();
					rowSize = Math.max(rowSize, size* 2);
					for(int j = 0; j < size; j++) {
						info temp = list.get(j);
						arr[j*2][i] = temp.val;
						arr[j*2+1][i] = temp.num;
					}
					list.clear();
				}
			}
			
			time++;
		}
		if(!find)
			time=-1;
		System.out.println(time);
	}

}
