import java.util.*;
import java.io.*;

class info{
	int r,c;
	public info(int r, int c) {
		this.r = r;
		this.c=c;
	}
}

public class Main {
	static int arr[][];
	static int magic[];
	static int locrow[] = {-1,0,1,0};
	static int loccol[] = {0,1,0,-1};
	static int sum = 0;
	static int count = 0;
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		int size =(int) Math.pow(2, n);
		arr = new int[size][size];
		
		for(int i = 0;i < size; i++) {
			st = new StringTokenizer(br.readLine());
			//int n = Integer.parseInt(st.nextToken());
			for(int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				sum += arr[i][j];
			}
		}
		
		magic = new int[num];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < magic.length; i++)
			magic[i] = Integer.parseInt(st.nextToken());
		
		solve();
		
		System.out.println(sum);
		System.out.println(count);
	}
	public static void rotation(int r, int c, int len) {
		int newarr[][] = new int[len][len];
		
		for(int i =r; i < r+len; i++) {
			for(int j =c; j < c+len; j++) {
				newarr[i-r][j-c] = arr[i][j];
			}
		}
		
		for(int j =c+len-1; j >= c; j--) {
			for(int i =r; i < r+len; i++) {
				arr[i][j] = newarr[c+len-1-j][i-r];
			}
		}
		
	}
	public static void solve() {
		int len;
		for(int i =0; i< magic.length; i++) {
			len = (int)Math.pow(2,magic[i]);
			
			//if(len != 1) {
				//int cnt = arr.length /len;
				for(int j =0; j <arr.length ; j+=len) {
					for(int k=0; k <arr.length ; k+=len) {
						rotation(j,k,len);
					}
				}
			//}
			//얼음칸이 2개이하로 인접시 -1
			boolean temp[][] = new boolean[arr.length][arr.length];
			
			for(int k = 0; k < arr.length; k++) {
				for(int j = 0 ; j < arr[0].length; j++) {
					int count = 0;
					int newr,newc;
					
					if(arr[k][j] <= 0)
						continue;
					
					for(int l = 0; l < 4;l++) {
						newr = k+locrow[l];
						newc = j+loccol[l];
						if(newr >=0 && newr < arr.length && newc >=0 && newc < arr[0].length) {
							if(arr[newr][newc]>0)
								count++;
						}
					}
					
					if(count <= 2) {
						temp[k][j] = true;
						//sum--;
					}
				}
			}
			
			for(int k = 0; k < arr.length; k++) {
				for(int j = 0 ; j < arr[0].length; j++) {
					if(temp[k][j]) {
						sum--;
						arr[k][j]--;
					}
				}
			}
				
		}
		
		//남아있는 덩어리의 수
		count();
	}
	public static void bfs(int r, int c) {
		int cnt =0;
		LinkedList<info> queue = new LinkedList<>();
		queue.add(new info(r,c));
		
		info temp;
		int row,col;
		int newr, newc;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			row = temp.r;
			col = temp.c;
			
			for(int i = 0; i < 4; i++) {
				newr = row + locrow[i];
				newc = col + loccol[i];
				
				if(newr >=0 && newr < arr.length && newc >=0 && newc < arr[0].length) {
					if(!visited[newr][newc] && arr[newr][newc]!=0) {
						visited[newr][newc] = true;
						cnt++;
						queue.add(new info(newr, newc));
					}
						
				}
			}
			
					
		}
		count = Math.max(count, cnt);
	}
	public static void count() {
		visited = new boolean[arr.length][arr[0].length];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(!visited[i][j] && arr[i][j] != 0) {
					bfs(i,j);
				}
			}
		}
	}
}
