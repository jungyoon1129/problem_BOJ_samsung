import java.util.*;
import java.io.*;

/*
 * ȥ�� Ǯ��� ������ �������
 * ������ ���� �Ẹ��
 * ��Ģ ã��.
 * ���� ���� �����!
 * stack�� �Ű� ���� �ʾƵ� �ڿ������� ������ ��
 * �� ȿ������ ���
 * https://jow1025.tistory.com/129
 */
public class dragon_15685 {
	static int generation;
	static int locRow[] = {0,-1,0,1};
	static int locCol[] = {1,0,-1,0};
	static boolean arr[][] = new boolean[101][101];
	static Stack<Integer> stack;
	static int cnt = 0;
	static int stage = 0;
	static LinkedList<Integer> temp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		int n = Integer.parseInt(br.readLine());
		
		
		for(int i = 0 ; i < n; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			generation = Integer.parseInt(st.nextToken());
			stage = 0;
			stack = new Stack<>();
			stack.add((dir - 1+4)%4);
			temp = new LinkedList<>();
			arr[y][x] = true;	//���� �߱� �����ϴ� ���� üũ�ؾ��Ѵ�.!!!
			draw(x, y, 0);	//���� �׸��� �� 0 ����
		}
		
		//ĭ �� ����
		countSquare();
		System.out.println(cnt);
	}
	
	public static void countSquare() {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = 0 ; j <arr[0].length-1; j++) {
				if(arr[i][j] == true && arr[i][j+1] == true && arr[i+1][j] == true && arr[i+1][j+1] == true) {
					
							cnt++;
					
				}
			}
		}
	}
	
	
	public static void draw(int col, int row, int stage) {
		//arr[y][x] = true;
		int dir=-1;
		
		if(stage-1== generation)
			return;
		while(!stack.isEmpty()) {
			dir = (stack.pop()+1)%4; 
			temp.add(dir);
			//dir = (dir+1)%4; 
			if(row+locRow[dir] >= 0 && row+locRow[dir] < arr.length &&
					col+locCol[dir] >= 0 && col+locCol[dir] < arr[0].length) {
				row = row+locRow[dir];
				col = col+locCol[dir];
				
				arr[row][col] =true;						
			}			
		}

		for(int i = 0; i <temp.size(); i++)
			stack.add(temp.get(i));
		draw(col, row, stage+1);
		
	}

}
