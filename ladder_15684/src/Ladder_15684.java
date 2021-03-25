import java.util.*;
import java.io.*;
/*
 * ¯ �����..
 * dfs
 * ���� Ž������ ������ ���� �� ���ƺ���.(dfsȰ���ؼ� cnt�� ��� ���� ������ ������ �ٴ�)
 * https://ukyonge.tistory.com/113
 */
public class Ladder_15684 {
	static LinkedList<Integer> list[];
	static boolean arr[][];
	static LinkedList<Integer> add[];
	static int line=0;
	static int ans = -1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		int n= sc.nextInt();
		int m = sc.nextInt();
		int h = sc.nextInt();
		arr = new boolean[h+1][n+1];
		
		
		for(int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
	
			arr[a][b] = true;
		}
		
		for (int i = 0; i < 4; i++) {
	     //���μ� i�� �߰�   
			line = i;
	        dfs(1,0);
	        if(ans!=-1)
	        	break;
	        
	    }
		System.out.println(ans);
	}
	
	public static boolean solve() {
		for(int i = 1; i < arr[0].length; i++) {
			int col = i;
			for(int j = 1; j < arr.length; j++) {
				//������ �̵�
				//������ �ִٸ� �̵�
				if(arr[j][col])
					col++;
				else if(col > 1 && arr[j][col-1])
					col--;
			}
			if(i!=col)
				return false;
		}
		return true;
	}
	
	public static void dfs(int y, int cnt) {
		
		if(line == cnt) {
        	if(solve())
        		ans = cnt;
        	return;
        }
        
		for(int i = y; i < arr.length; i++) {
			for(int j =1; j < arr[0].length-1; j++) {
				if(!arr[i][j] && !arr[i][j-1] && !arr[i][j+1]) {
					arr[i][j] = true;
					dfs(i, cnt+1);
					arr[i][j] = false; 
				}
			}
		}
	}

}
