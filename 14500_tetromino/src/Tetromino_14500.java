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
 * ��� ������ ���� �Է��ؼ� ����Ž���ص� ������
 * 
 *  �� ��忡�� 4����ŭ 4�������� ��带 Ȯ���Ų�ٸ�?
	'��' ������ ������ ��� ��Ʈ�ι̳��� ��찡 �����Եȴ�
	dfs �� ������ ������ Ž���ϰ� '��' ������ ���ܷ� ó���� ���ָ� �ȴ�
	bfs�� �� �������κ��� �����¿�� �̵��ϱ� ������, ������� ������ �ݵ�� ������ �Ŷ� ������ ����.
	�׿� ���� dfs�� �Ѻױ׸���� ���� ���¶� �Ƚ��ϰ� ����� �� �ִ�.
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
				//�湮���� �ʾҰ� �����ȿ� �����Ѵٸ�
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
