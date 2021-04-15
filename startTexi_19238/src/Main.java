import java.util.*;
import java.io.*;
/*
 * 문제 조건이 너무 부족했다
 * 손님이 있어도 지나갈 수 있음
 */
class info implements Comparable<info>{
	int r,c;
	long dist;
	public info(int r, int c) {
		this.r = r;
		this.c = c;
		
	}
	public info(int r, int c, long dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
	@Override
	public int compareTo(info o) {
		// TODO Auto-generated method stub
		//if(this.dist == o.dist) {
			if(this.r == o.r)
				return this.c - o.c;
			return this.r - o.r;
		//}//
		//return this.dist - o.dist;
	}
}

public class Main {
	static int arr[][];
	static boolean visited[][];
	static int locrow[] = {-1,1,0,0};
	static int loccol[] = {0,0,-1,1};
	static int texistartrow;
	static int texistartcol;
	static long fuel;
	static LinkedList<info> queue = new LinkedList<>();
	static PriorityQueue<info> ans = new PriorityQueue<>();
	static long answer;
	static info customer[];
	static int m;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Long.parseLong(st.nextToken());
		
		arr= new int[n+1][n+1];
		//0은 빈칸, -1은 벽
		for(int i = 1; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < arr[0].length; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					arr[i][j] = -1;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		texistartrow= Integer.parseInt(st.nextToken());
		texistartcol = Integer.parseInt(st.nextToken());
		
		int r,c,dr,dc;
		//손님 행,열 도착지 행,열
		customer = new info[m+1];
		
		for(int i =1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			r =  Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			dr = Integer.parseInt(st.nextToken());
			dc = Integer.parseInt(st.nextToken());
			
			customer[i] = new info(dr,dc);
			arr[r][c] = i;
		}
		
		
		drive();
		System.out.println(answer);
		
	}
	public static void drive() {
		int count = 0;
		while(true) {
			queue.add(new info(texistartrow, texistartcol, 0));
			visited = new boolean[arr.length][arr[0].length];
			visited[texistartrow][texistartcol] = true;
			info temp;
			if(arr[texistartrow][texistartcol] >=1 &&arr[texistartrow][texistartcol]<=m)
				temp = new info(texistartrow,texistartcol);
			else {
				search();
				//ans얻음
				
				if(ans.size()!= 0)
					temp = ans.poll();
				else {
					answer = -1;
					return;
				}
				if(fuel - temp.dist < 0) {
					answer = -1;
					return;
				}
				fuel -= temp.dist;
				ans.clear();
			}
			
			
			int r= temp.r;
			int c = temp.c;
			int n = arr[r][c];
			arr[r][c] = 0;
			
			int destr = customer[n].r;
			int destc = customer[n].c;
			if(destr == r && destc == c) {
				texistartrow = destr;
				texistartcol = destc;
				count++;
			}
			else {
				long need = go(r,c,destr,destc);
				queue.clear();
				
				if(need == -1) {
					answer = -1;
					return;
					
				}
				else {
					count++;
					fuel += need;
					
				}
			}
			
			if(count == m) {
				answer = fuel;
				return;
			}
		}
		
			
		
	}
	
	public static long go(int r, int c, int destr, int destc) {
		queue.add(new info(r,c,0));
		info temp;
		long dist;
		int curr,curc,curdist,newr,newc;
		visited = new boolean[arr.length][arr[0].length];
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			curr = temp.r;
			curc = temp.c;
			dist = temp.dist;
			
			
			
			
			for(int i = 0; i < locrow.length; i++) {
				newr = curr + locrow[i];
				newc = curc + loccol[i];
				
				if(newr >= 1 && newr<arr.length && newc >= 1 && newc < arr.length) {
					if(arr[newr][newc] == -1 || visited[newr][newc])	//벽이거나 이미 방문했을때
						continue;
					visited[newr][newc] = true;
					if(fuel - dist-1 < 0) {
						continue;
					}
					if(newr == destr && newc == destc) {
						//도착
						texistartrow = destr;
						texistartcol = destc;
						return dist+1;
					}
					if(arr[newr][newc]!=-1)
						queue.add(new info(newr,newc,dist+1));
					//if(dist == 20)
					//	System.out.println();
				}
			}
					
		}
		return -1;
	}
	public static void search() {
		int r,c,newc, newr;
		info temp;
		long dist;
		long mindist=Long.MAX_VALUE;
		//visited = new boolean[arr.length][arr[0].length];
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			
			r = temp.r;
			c = temp.c;
			dist = temp.dist;
			
			if(dist > fuel)
				return;
			if(dist >= mindist)
				continue;
			
			for(int i = 0; i < locrow.length; i++) {
				newr = r + locrow[i];
				newc = c + loccol[i];
				
				if(newr >= 1 && newr<arr.length && newc >= 1 && newc < arr.length) {
					if(arr[newr][newc] == -1 || visited[newr][newc])	//벽이거나 이미 방문했을때
						continue;
					visited[newr][newc] = true;
					if(arr[newr][newc] == 0)
						queue.add(new info(newr,newc,dist+1));
					else {
						//손님 찾았다!!
						mindist = dist+1;
						ans.add(new info(newr,newc,dist+1));
						//queue.add(new info(newr,newc,dist+1));
					}
				}
			}
			
		}
		
	}
	
	

}
