import java.util.*;
/*
 * 방문 안했던 애들 싹 방문 하면서 
 * 근처 애들과 값 차이 계산하고 범위안에 드는 차이면 
 * listtemp라는 arraylist에 넣고 list라는 queue에도 넣는다.
 * list가 empty가 될때까지 범위에 드는 근처애들을 빼서 탐색
 * 
 * list empty시 listtemp에 넣어놓은 애들과 평균 구해서 
 * arr배열 업데이트
 * 
 * listtemp의 사이즈가 계속 1이면 변화가 없는 것 -> flag =false
 * 반복문 빠져나오게!
 */
class graph{
	int row;
	int col;
	public graph(int r, int c) {
		row =r;
		col =c;
	}
}

public class PopulationMove_16234 {
	static boolean visited[][];
	static LinkedList<graph> list;
	static int locRow[] = {-1,0,1,0};
	static int locCol[] = {0,1,0,-1};
	static int arr[][];
	static int l, r;
	static boolean flag = false;
	static int count =-1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();
		
		arr = new int[n][n];
		list = new LinkedList<>();
		visited = new boolean[n][n];
		
		for(int i = 0; i < arr.length; i++)
			for(int j =0; j < arr[0].length; j++)
				arr[i][j] = sc.nextInt();
		
		
		while(true) {
			visited = new boolean[n][n];
			flag=false;
			search();
			//search에서 만약 arr을 바꾸면 flag=true로 나올것
			if(!flag)
				break;
		}
		System.out.println(count);
		
	}

	public static void search() {
		count++;
		for(int i = 0 ; i< arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					list.add(new graph(i,j));
					bfs();
					
				}
			}
		}
	}
	
	public static void bfs() {
		graph temp;
		int row,col, currentVal, nextVal, diff;
		ArrayList<graph> listtemp = new ArrayList<>();
		
		while(!list.isEmpty()) {
			temp = list.poll();
			listtemp.add(temp);
			
			row = temp.row;
			col = temp.col;
			currentVal = arr[row][col];
			
			for(int i = 0; i <locRow.length; i++) {
				if(row+locRow[i] <0 || row+locRow[i] >= arr.length
						|| col+locCol[i]<0 || col+locCol[i] >= arr[0].length)
					continue;
				if(!visited[row+locRow[i]][col+locCol[i]]) {
					
					nextVal = arr[row+locRow[i]][col+locCol[i]];
					diff = Math.abs(currentVal - nextVal);
					
					if(diff >= l && diff <= r) {
						visited[row+locRow[i]][col+locCol[i]] = true;
						list.add(new graph(row+locRow[i],col+locCol[i]));
					}
					
				}
			}
		}
		int sum =0;
		int size = listtemp.size();
		if(size != 1) {
			flag =true;
			//arr배열 평균
			for(int i = 0; i < listtemp.size(); i++) {
				temp = listtemp.get(i);
				sum += arr[temp.row][temp.col];
			}
			sum /= size;
			for(int i = 0; i < listtemp.size(); i++) {
				temp = listtemp.get(i);
				arr[temp.row][temp.col] = sum;
			}
		}
			
		
		
	}
}
