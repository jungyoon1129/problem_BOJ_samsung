import java.util.*;
import java.io.*;
class info{
	int r, c, m,dir, v;
	public info(int r, int c, int m, int v, int dir) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.dir = dir;
		this.v = v;
	}
}
public class Main {
	static LinkedList<info> list[][];
	static int locrow[] = {-1,-1,0,1,1,1,0,-1};
	static int loccol[] = {0,1,1,1,0,-1,-1,-1};
	static LinkedList<info> queue;
	static int n;
	static int k;
	static LinkedList<info> newqueue;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		 k = Integer.parseInt(st.nextToken());
		
		list = new LinkedList[n][n];
		
		for(int i = 0; i < list.length; i++) {
			for(int j =0; j < list[0].length; j++){
				list[i][j] = new LinkedList<>();
			}
		}
		int r,c,mass,dir,v;
		queue = new LinkedList<>();
		
		for(int i = 1; i <=m; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			mass = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			info in = new info(r-1,c-1,mass,v,dir);
			queue.add(in);
			list[r-1][c-1].add(in);
		}
		
		int ans = solve();
		System.out.println(ans);
	}
	
	public static int solve() {
		for(int i =0; i < k; i++) {
			move();
			divide();
		}
		int ans = summ();
		return ans;
	}
	public static int summ() {
		int sum = 0;
		while(!queue.isEmpty()) {
			sum += queue.poll().m;
		}
		return sum;
	}
	public static void divide() {
		info temp;
		int r,c, dir,mass,s;
		newqueue = new LinkedList<>();
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			r = temp.r;
			c = temp.c;
			dir = temp.dir;
			mass = temp.m;
			s = temp.v;
			

			if(list[r][c].size()>=2) {
				int count = list[r][c].size();
				int summ = 0;
				int sumv = 0;
				boolean first = true;
				boolean firstdirodd = true;
				boolean success = true;
			//int index;
				while(!list[r][c].isEmpty()) {
					temp = list[r][c].poll();
					summ += temp.m;
					sumv += temp.v;
					//index = queue.indexOf(temp);
					queue.remove(temp);
					
					if(first) {
						first = false;
						if(temp.dir % 2 ==1)
							firstdirodd = true;
						else
							firstdirodd = false;
					}
					else {
						if(firstdirodd) {
							if(temp.dir % 2 ==1)
								continue;
							else
								success = false;
						}
						else {
							if(temp.dir % 2 ==0)
								continue;
							else
								success = false;
						}
					}
				}
				int m = summ / 5;
				int v = sumv / count;
				//info newinfo[] = new info[4];
				if(m!=0) {
					for(int k = 0; k < 4; k++) {
						if(success) {
							temp = new info(r,c,m,v,k*2);
							//newinfo[k] = temp;
							newqueue.add(temp);
							list[r][c].add(temp);
						}
						else {
							temp = new info(r,c,m,v,k*2+1);
							//newinfo[k] = new info(i,j,m,v,k*2+1);
							newqueue.add(temp);
							list[r][c].add(temp);
						}
					}
				}
				
				
				
			}
			else {
				newqueue.add(temp);
			}
		}
	
		queue = newqueue;
	}
	
	public static void move() {
		info temp;
		int togor,togoc;
		//int arr[][] = new int[n][n];
		 newqueue = new LinkedList<>();
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			togor = (temp.r + locrow[temp.dir]*temp.v +n*250) % list.length;
			togoc = (temp.c + loccol[temp.dir]*temp.v +n*250) % list[0].length;
			
			list[temp.r][temp.c].poll();
			list[togor][togoc].add(temp);
			temp.r =togor;
			temp.c = togoc;
			
			newqueue.add(temp);
		}
		queue = newqueue;
	}
}
