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
/*
 * 모든 도형을 직접 입력해서 완전탐색해도 되지만
 * 
 *  한 노드에서 4번만큼 4방향으로 노드를 확장시킨다면?
	'ㅗ' 도형을 제외한 모든 테트로미노의 경우가 나오게된다
	dfs 로 나머지 도형을 탐색하고 'ㅗ' 도형만 예외로 처리를 해주면 된다
	bfs는 한 지점으로부터 상하좌우로 이동하기 때문에, 만들어진 도형이 반드시 인접할 거란 보장이 없다.
	그에 반해 dfs는 한붓그리기와 같은 형태라서 안심하고 사용할 수 있다.
 */

public class Tetromino_14500 {
	static int max = 0;
	static int arr[][];
	static boolean visited[][];
	static int locrow[] = {-1, 0, 1,0};
	static int loccolumn[] = {0, 1, 0, -1};
	//static int sum=0;
	
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[arr.length][arr[0].length];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		
		for(int i =0; i < arr.length; i++) {
			for(int j =0; j < arr[i].length; j++) {				
				visited[i][j] = true;
				dfs(i, j, 1, arr[i][j]);
				visited[i][j] = false;
				uo(i, j, 1, arr[i][j]);
			}
		}
		System.out.println(max);
	}
	
	public static void dfs(int row, int column, int count, int sum) {
		//visited[row][column] = true;
		
		if(count == 4) {
			max = Math.max(max, sum);
			return;
		}
		for(int i = 0; i < locrow.length; i++) {
			if(row+locrow[i] >=0 && row+locrow[i] < arr.length && 
					column + loccolumn[i] >= 0 && column + loccolumn[i] < arr[0].length && !visited[row+locrow[i]][column + loccolumn[i]]) {
				//방문하지 않았고 범위안에 존재한다면
				visited[row+locrow[i]][column + loccolumn[i]] = true;
				dfs(row+locrow[i], column + loccolumn[i], count+1, sum + arr[row+locrow[i]][column + loccolumn[i]]);
				visited[row+locrow[i]][column + loccolumn[i]] = false;
			}
		}
	}
	
	public static void uo(int row, int column, int count, int sum) {
		if(column + 1 < arr[0].length && row + 2 < arr.length)
			max = Math.max(max, arr[row][column+1]+arr[row+1][column]+arr[row+1][column+1]+arr[row+2][column+1]);
		if(row+1 < arr.length && column +2 < arr[0].length)
			max = Math.max(max, arr[row][column]+arr[row][column+1]+arr[row][column+2]+arr[row+1][column+1]);
		if(row+2 < arr.length && column + 1 < arr[0].length)
			max = Math.max(max, arr[row][column]+arr[row+1][column]+arr[row+1][column+1]+arr[row+2][column]);
		if(row + 1 < arr.length && column + 2 < arr[0].length)
			max = Math.max(max, arr[row][column+1]+arr[row+1][column]+arr[row+1][column+1]+arr[row+1][column+2]);
	}

}
