import java.util.*;
import java.io.*;

/*
 * -1을 쓰면 최소값이 갱신이 안됨을 놓침
 * 참고
 * https://leveloper.tistory.com/89
 */
class info{
	int r,c,time;
	public info(int r, int c) {
		this.r = r;
		this.c = c;
		//this.time = time;
	}
}
public class Laboratory_17142 {
	static ArrayList<info> queue = new ArrayList<>();
	static LinkedList<info> list = new LinkedList<>();
	static int locRow[] = {1,0,-1,0};
	static int locCol[] = {0, 1,0,-1};
	static int arr[][];
	static int virusNum;
	static int min = Integer.MAX_VALUE;
	static int n;
	static int count=0;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		virusNum = Integer.parseInt(st.nextToken());
		
		//visited = new boolean[n][n];
		arr = new int[n][n];
		//virusact = new info[virusNum];
		
		for(int i = 0 ; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < arr[0].length; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 0) {
					//빈칸은 0
					count++;
					arr[i][j] = -3;
					//continue;
				}
				else if(temp == 2) {
					//바이러스 처음엔 비활성:3, 활성:2
					arr[i][j] = -1;
					queue.add(new info(i, j));
				}
				else if(temp == 1) {
					//벽
					arr[i][j] = -2;
				}
			}
		}
		if(count == 0)
			min = 0;
		else {
			//m개의 바이러스만 활성화 시킴
			dfs(0,0);
		}
		if(min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
		
	}
	
	public static void dfs(int start,int num) {
		info temp;
		if(num == virusNum) {
			//활성 바이러스 virusNum만큼 다 만들었다.
			//전파시키는데 몇초 걸리는지 체크
			int val = bfs(count);
			//min이 -1이 되면 가장 최소 값으로 돼서 정답이 와도 못알아챔
			min = Math.min(min, val == -1?Integer.MAX_VALUE:val);
			return;
		}
		for(int k = start; k < queue.size(); k++){
			temp = queue.get(k);
			int i = temp.r;
			int j = temp.c;
			
			arr[i][j] = 0;//활성화
			list.addLast(new info(i,j));
			dfs(k+1,num+1);
			list.removeLast();
			arr[i][j] = -1; //다시 비활성
		}
	}
	
	public static int[][] deepcopy(int arr1[][]){
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++)
				arr1[i][j] = arr[i][j];
		}
		return arr1;
	}
	
	public static int bfs(int cnt) {
		//전파시키는데 몇초 걸리는지
		info temp;
		int r,c,newr,newc;
		//time++;
		int arr1[][] = new int[n][n];
		arr1 = deepcopy(arr1);
		int val=-1;
		int max = 0;
		boolean complete = false;
		
		LinkedList<info> list1 = new LinkedList<>();
		Iterator<info> itr = list.iterator();
		
		while(itr.hasNext()) {
			list1.add(itr.next());
		}
		
		//빈칸은 -3, 벽은 -2, 바이러스 비활성 -1,활성 0
		while(!list1.isEmpty()) {
			
			temp = list1.poll();
			
			r = temp.r;
			c = temp.c;
			val = arr1[r][c];
			
			for(int i = 0; i < locRow.length; i++) {
				newr = r+locRow[i];
				newc = c+locCol[i];
				if(newr >= 0 && newr < arr1.length && newc >=0 && newc < arr1[0].length) {
					if(arr1[newr][newc] == -3 ) {
						//빈칸
						cnt--;
						arr1[newr][newc] = val+1;
						list1.add(new info(newr, newc));
					}					
					else if(arr1[newr][newc] == -1) {
						//비활성인 애 만남
						arr1[newr][newc] = val+1;
						list1.add(new info(newr, newc));
					}
					if(cnt == 0) {
						complete = true;
						max = val+1; //현재 cnt를 줄이면서 0이 된거니까!
					
						return max;
					}
					
				}
				
			}
			
			
		}
		
		//전파됐는지 확인
		//boolean complete = check(arr1);
		if(!complete) {//전파불가능한 경우
			max = -1;
			//check(arr1);
		}
		return max;
	}
	public static boolean check(int arr[][]) {
		for(int i = 0; i < arr.length; i++) {
			for(int j =0; j < arr[0].length;j++) {
				if(arr[i][j] == -3)
					return false;
			}
		}
		return true;
	}
	
}
