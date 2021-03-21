package laboratory_14502;
import java.util.*;
import java.io.*;

class graph{
	int row;
	int column;
	public graph(int row, int column) {
		this.row = row;
		this.column = column;
	}
}

public class Laboratory_14502 {
	static int arr[][];
	static boolean visited[][];
	static int max = -1;
	static LinkedList<graph> queue;
	static LinkedList<graph> blank;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		queue = new LinkedList<>();
		blank = new LinkedList<>();
				
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			for(int j =0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st1.nextToken());
				
			}
		}
		
		dfs(0,0,0);
		System.out.println(max);
	}
	
	public static void dfs(int row, int column, int count) {
		//if(max < sum) max = sum;
		
		if(count == 3) {
			//빈공간 count 함수
			max = Math.max(max, countBlank());
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {		
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == 0) {
					//벽이나 바이러스가 아니고 방문하지 않았다면
					//방문여부를 벽으로 표시하고 갔다오고 다시 통로로 만들어줌으로써 표시
					
					arr[i][j] = 1;
					if(i == 0 && j == arr[0].length -1  && arr[i][j] == 1 && count == 1)	
						System.out.println(count);
					dfs(i, j, count+1);
					arr[i][j] = 0;
					
									
				}
			}
		}
	}
	
	public static int countBlank() {
		int count = 0;		
		graph temp;
		int locrow[] = {-1, 0, 1, 0};
		int loccol[] = {0, 1, 0, -1};
		int currow, curcol;
		
		int arr2[][] = new int[arr.length][arr[0].length];
		
		//arr배열을 복사
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				arr2[i][j] = arr[i][j];
				if(arr2[i][j] == 2)	queue.add(new graph(i,j));
				//if(i == )
			}
		}
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			currow = temp.row;
			curcol = temp.column;
			
			for(int i = 0; i < locrow.length; i++) {
				if(currow + locrow[i] >=0 && currow + locrow[i] < arr.length &&
						curcol + loccol[i] >= 0 && curcol + loccol[i] < arr[0].length) {
					
					if(arr2[currow + locrow[i]][curcol + loccol[i]]==0) {				
						arr2[currow + locrow[i]][curcol + loccol[i]] = 2;
						queue.add(new graph(currow + locrow[i], curcol + loccol[i]));											
					}
				}			
			}
		}
		//빈공간 카운트
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				//arr2[i][j] = arr[i][j];
				if(arr2[i][j] == 0)	count++;
			}
		}
		
		return count;
	}
	

}
