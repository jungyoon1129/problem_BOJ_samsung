import java.util.*;
import java.io.*;

class graph{
	int row;
	int col;
	
	public graph(int r, int c) {
		row = r;
		col = c;
	}
}

public class Watch_15683 {
	static Stack<graph> stack;
	static LinkedList<graph> wall;
	static int arr[][];
	static int min =Integer.MAX_VALUE;
	static int locRow[] = {-1, 0, 1, 0};
	static int locCol[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		stack = new Stack<>();
		wall = new LinkedList<>();
		
		for(int i =0; i < arr.length; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st1.nextToken());
				if(arr[i][j] >= 1 && arr[i][j] <= 5)
					stack.add(new graph(i, j));
				else if(arr[i][j] == 6)
					wall.add(new graph(i, j));
			}
		}
		
		dfs(arr);
		System.out.println(min);
	}
	
	public static int[][] deepcopy(int arr[][]){
		int temp[][] = new int[arr.length][arr[0].length];
		for(int i = 0 ; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				temp[i][j] = arr[i][j];
			}
		}
		return temp;
	}
	
	public static void dfs(int temp3[][]) {
		
		//int temp3[][];
		
		if(!stack.isEmpty()) {
			graph temp = stack.pop();
			int row = temp.row;
			int col = temp.col;
			
			switch(arr[row][col]) {
			case 1:
				for(int i = 0; i < 4; i++) {
					int temp2[][] = deepcopy(temp3);
					temp2 =search(row, col, i, temp2);
					dfs(temp2);
				}
				break;
			case 2:
				for(int i = 0; i < 2; i++) {
					int temp2[][] = deepcopy(temp3);
					temp2 =search(row, col, i, temp2);
					temp2 =search(row, col, i+2, temp2);
					dfs(temp2);
				}
				break;
			case 3:
				for(int i = 0; i < 4; i++) {
					int temp2[][] = deepcopy(temp3);
					temp2 =search(row, col, i, temp2);
					temp2 =search(row, col, (i+1)%4, temp2);
					dfs(temp2);
				}
				break;
			case 4:
				for(int i = 0; i < 4; i++) {
					//int cnt =0;
					int temp2[][] = deepcopy(temp3);
					for(int j = 0; j < 4; j++) {
						if(j == i)	continue;
						
						temp2 =search(row, col, j, temp2);
						//cnt++;
					}
					dfs(temp2);
				}
				break;
			case 5:
				int temp2[][] = deepcopy(temp3);
				temp2 =search(row, col, 0, temp2);
				temp2 =search(row, col, 1, temp2);
				temp2 =search(row, col, 2, temp2);
				temp2 =search(row, col, 3, temp2);
				dfs(temp2);
			}
			stack.add(new graph(row, col));
		}
		else {
			min = Math.min(min, countBlank(temp3));
		}
	}
	
	public static int countBlank(int temp[][]) {
		int count = 0;
		for(int i =0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++) {
				if(temp[i][j] == 0)
					count++;
			}
		}
		return count;
	}
	
	public static int[][] search(int row, int col, int dir, int arr[][]) {
		//int temp[][] = new int[arr.length][arr[0].length];
		arr[row][col] = 7;
		
		while(true) {
			if(row + locRow[dir] >= 0 && row+locRow[dir] < arr.length 
					&& col+locCol[dir] >= 0 && col+locCol[dir]<arr[0].length && 
					arr[row+locRow[dir]][col+locCol[dir]] != 6 ) {
				row = row + locRow[dir];
				col = col+locCol[dir];
				arr[row][col] = 7;
			}
			else
				break;
		}
		
		return arr;
	}

}
