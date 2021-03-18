package marbleEscape;
import java.util.*;

/*
 * 백준 13460번 구슬 탈출 2 
 * Location으로 빨간 구슬, 파란 구슬 위치, 이동 횟수를 저장하고
 * visited는 4차원배열로 빨간구슬과 파란 구슬의 위치로 기록해야한다.(red는 방문했지만 blue는 처음이라 goal에 도달할 수도 있음)
 * 한번 방향설정시 중간에 못바꾸니까 빨간 파란 구슬 다 goal에 들어간다면 빨간게 먼저라도 안됨
 * BFS로 최적 경로 탐색
 */
class Location{
	int row1;
	int column1;
	int row2;
	int column2;
	int count;
	
	public Location(int row1, int column1, int row2, int column2, int count) {
		this.row1 = row1;
		this.column1 = column1;
		this.row2 = row2;
		this.column2 = column2;
		this.count = count;
	}
}

public class MarbleEscape {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int arr[][] = new int[n][m];
		sc.nextLine();
		int goalrow=0, goalcolumn =0;
		int redrow =0, redcolumn=0;
		int bluerow =0, bluecolumn=0;
		
		boolean visited[][][][] = new boolean[n][m][n][m];
		for(int i = 0; i < n; i++) {
			String temp = sc.nextLine();
					
			for(int j = 0; j < temp.length(); j++) {
				char ch = temp.charAt(j);
				if(ch == '.')	continue;
				else if(ch == '#')
					arr[i][j] = -1;
				else if(ch == 'O') 
					arr[i][j] = 3;
					
				else if(ch == 'R') {
					arr[i][j] = 1;
					redrow = i;
					redcolumn = j;
				}
				else if(ch == 'B') {
					arr[i][j] = 2;
					bluerow = i;
					bluecolumn = j;
					
				}
				
			}		
		}
		
		LinkedList<Location> queue = new LinkedList<>();
		queue.add(new Location(redrow, redcolumn, bluerow, bluecolumn, 0));
		visited[redrow][redcolumn][bluerow][bluecolumn] = true;
		
		Location temp;
		int locRow[] = {0,0,1,-1};
		int locColumn[] = {1,-1,0,0};
		int nextRedRow, nextRedColumn;
		int nextBlueRow, nextBlueColumn;
		int ans = -1;
		int count=0;
		boolean flag = false;
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			
			nextRedRow = temp.row1;
			nextRedColumn = temp.column1;
			nextBlueRow = temp.row2;
			nextBlueColumn = temp.column2;
			count = temp.count;
			
			if(count > 10)	break;
			if(arr[nextRedRow][nextRedColumn] == 3) {
				ans = count;
				break;
			}
			if(arr[nextBlueRow][nextBlueColumn] == 3)	continue;
			
			for(int i =0; i < locRow.length; i++) {
				nextRedRow = temp.row1;
				nextRedColumn = temp.column1;
				nextBlueRow = temp.row2;
				nextBlueColumn = temp.column2;
				count = temp.count;
				while(true) {
					nextRedRow += locRow[i];
					nextRedColumn += locColumn[i];
					if(arr[nextRedRow][nextRedColumn]==-1 || arr[nextRedRow][nextRedColumn]==3) {
						//벽이나 goal
						if(arr[nextRedRow][nextRedColumn]==-1) {
							nextRedRow -= locRow[i];
							nextRedColumn -= locColumn[i];
						}
						break;
					}				
				}
			
				while(true) {
					nextBlueRow += locRow[i];
					nextBlueColumn += locColumn[i];
					if(arr[nextBlueRow][nextBlueColumn]==-1 || arr[nextBlueRow][nextBlueColumn]==3) {
						//벽
						if(arr[nextBlueRow][nextBlueColumn]==-1) {
							nextBlueRow -= locRow[i];
							nextBlueColumn -= locColumn[i];
						}
						break;
					}				
				}
			
			
			//이동한 구슬들의 위치가 같다면 별로 안이동한게 그 자리 차지한다.
			//그리고 visited안했어야지 queue에 넣음
			if(nextBlueRow == nextRedRow && nextRedColumn == nextBlueColumn) {
				int distred = Math.abs(nextRedRow-temp.row1) + Math.abs(nextRedColumn - temp.column1);
				int distblue = Math.abs(nextBlueRow-temp.row2) + Math.abs(nextBlueColumn - temp.column2);

				if(arr[nextBlueRow][nextBlueColumn]==3)	flag = true;
				
					if(distred > distblue) {
						nextRedRow -= locRow[i];
						nextRedColumn -= locColumn[i];
					}
					else {
						nextBlueRow -= locRow[i];
						nextBlueColumn -= locColumn[i];
					}
				//if(arr[nextBlueRow][nextBlueColumn]==3)
				
			}
			
			if(!flag && !visited[nextRedRow][nextRedColumn][nextBlueRow][nextBlueColumn]) {
				queue.add(new Location(nextRedRow, nextRedColumn, nextBlueRow, nextBlueColumn, count+1));
				visited[nextRedRow][nextRedColumn][nextBlueRow][nextBlueColumn] = true;
			}
			flag = false;
			}
			
		}
		System.out.println(ans);
		
		
		

	}

}
