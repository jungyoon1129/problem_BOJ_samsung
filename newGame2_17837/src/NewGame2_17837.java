import java.util.*;
import java.io.*;
/*
 * locRow의 인덱스가 1부터 시작함에 주의
 */
class info{
	int r, c, dir;
	public info(int r, int c, int dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
}
public class NewGame2_17837 {
	static int arr[][];
	static int locRow[] = {0,0,0,-1,1};
	static int locCol[] = {0,1,-1,0,0};
	static ArrayList<info> horseLoc[][];
	static info horse[] ;
	static boolean end = false;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr= new int[n+1][n+1];
		//horseLoc = new int[n+1][n+1];
		horseLoc = new ArrayList[n+1][n+1];
		
		horse = new info[k+1];
		//queue = new LinkedList[n+1];
		
		//0은 흰색, 1:빨간색, 2:파란색
		
		for(int i = 1 ; i < arr.length; i++) {
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < arr[0].length; j++) {
				horseLoc[i][j] = new ArrayList<>();
				
				int temp = Integer.parseInt(st.nextToken());
				arr[i][j] = temp;
			}
		}
		
		for(int i = 1; i <= k; i++) {
			
			st = new StringTokenizer(br.readLine());
			int r  = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			info newh = new info(r,c,dir);
			horse[i] = newh;
			horseLoc[r][c].add(newh);	//자기 자신을 넣어둔다.
		}
		
		
		info temp;
		int time = 0;
		boolean fail = false;
		boolean complete = false;
		
		while(time < 1000) {
			time++;
			for(int i = 1; i <= k; i++) {
				if(solution(i)) {
					System.out.println(time);
					return;
				}
			}
			
		}
		System.out.println(-1);
		
	}
	public static boolean solution(int i) {
		info temp;
		temp =horse[i];
		//i번째 말의 위치와 방향 알아내기
		int r = temp.r;
		int c = temp.c;
		int dir = temp.dir;
		
		//r,c에 있는 말 중 k번째 이상에 있는 말들을 다 newr, newc로 옮기자
		int newr = r + locRow[dir];
		int newc = c+ locCol[dir];
		int index = horseLoc[r][c].indexOf(temp);
		
		if(newr >=1 && newr < arr.length && newc >=1 && newc < arr.length) {
			//범위를 벗어나지 않는다면
			int tmp = arr[newr][newc];
			//해당위치의 색깔을 보자
			//0은 흰색, 1:빨간색, 2:파란색
			switch(tmp) {
			case 0:
				//이동한다.
				white(r,c,newr,newc, index);			
				break;
			case 1:
				red(r,c,newr,newc, index);
				break;
			case 2:
				//r,c에서 dir을 반대로
				if(dir == 1 || dir ==3) {
					temp.dir++;
					dir++;
				}
				else {
					temp.dir--;
					dir--;
				}
				
				int tempr = r + locRow[dir];
				int tempc = c + locCol[dir];
				if(tempr <1 || tempr >= arr.length || tempc <1 || tempc >= arr.length) {
					//파란색이라 이동하려했는데 체스판 밖으로 나가는 경우 그냥 그자리에 있음
					newr = r;
					newc = c;
				}
				else if(arr[tempr][tempc] == 0) {
					newr = tempr;
					newc = tempc;
					white(r,c,tempr,tempc, index);
				}
				else if(arr[tempr][tempc] == 1) {
					newr = tempr;
					newc = tempc;
					red(r,c,tempr,tempc, index);
				}
				else {
					//파란색인 경우는 이동하지 않고 그 자리에 있음
					newr = r;
					newc = c;
				}
				//blue(i, newr, newc);
				break;
			}
			
		}
		else {
			//체스판을 벗어나는 경우는 파란색인 경우와 같은경우임
			//r,c에서 dir을 반대로
			if(dir == 1 || dir ==3) {
				temp.dir++;
				dir++;
			}
			else {
				temp.dir--;
				dir--;
			}
			
			int tempr = r + locRow[dir];
			int tempc = c + locCol[dir];
			if(tempr <1 || tempr >= arr.length || tempc <1 || tempc >= arr.length) {
				newr = r;
				newc = c;
			}
			else if(arr[tempr][tempc] == 0) {
				newr = tempr;
				newc = tempc;
				white(r,c,tempr,tempc, index);
			}
			else if(arr[tempr][tempc] == 1) {
				newr = tempr;
				newc = tempc;
				red(r,c,tempr,tempc, index);
			}
			else {
				//파란색인 경우는 이동하지 않고 그 자리에 있음
				newr = r;
				newc = c;
			}
		}
		//newr, newc로 이동한 거니까 거기의 size확인
		if(horseLoc[newr][newc].size() >=4 ) {
			//System.out.println(time);
			return true;
		}
		return false;
	}
	public static void red(int r, int c, int newr, int newc, int index) {
		// r,c위치에 있는 말들 중 index 이상에 있는 말들을 newr, newc로 이동
		info temp;
		int size = horseLoc[r][c].size();
		//말들의 위치 정보 변경하고 해당위치에 말을 위치시킴					
			for(int i =size-1; i >=index; i--) {
				temp = horseLoc[r][c].get(i);
				temp.r = newr;
				temp.c = newc;
				horseLoc[r][c].remove(i);
				horseLoc[newr][newc].add(temp);
			}
			
					
	}
	public static void white(int r, int c, int newr, int newc, int index) {
		// r,c위치에 있는 말들 중 index 이상에 있는 말들을 newr, newc로 이동
		info temp;
		//말들의 위치 정보 변경하고 해당위치에 말을 위치시킴
		int size = horseLoc[r][c].size();
			for(int i =index; i < size; i++) {
				temp = horseLoc[r][c].get(i);
				//horseLoc[r][c].remove(index);
				temp.r = newr;
				temp.c = newc;
				
				horseLoc[newr][newc].add(temp);
			}	
			for(int i =size-1; i >=index; i--) {
				
				horseLoc[r][c].remove(i);
				
			}
					
	}

}
