package fishingKing_17143;
import java.util.*;
import java.io.*;

class info implements Comparable<info>{
	int r,c,v,dir,size;
	public info(int r, int c, int v, int dir, int size) {
		this.r = r;
		this.c = c;
		this.v = v;
		this.dir = dir;
		this.size = size;
	}
	@Override
	public int compareTo(info o) {
		// TODO Auto-generated method stub
		return this.r - o.r;
	}
	
}

public class FishingKing_17143 {
	static PriorityQueue<info> shark[];
	static int fishingLoc =0;	//0번째 열
	static int c;
	static int sum =0;
	static int locRow[] = {0,-1,1,0,0};
	static int locCol[] = {0,0,0,1,-1};
	static int r;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		shark = new PriorityQueue[c+1];
		
		for(int i = 1 ; i <shark.length; i++)
			shark[i] = new PriorityQueue<>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int vel = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			shark[col].add(new info(row,col,vel,dir,size));
		}
		fishing();
		System.out.println(sum);
		
	}
	
	public static void fishing() {
		info temp, temp1;
		while(fishingLoc != c) {
			fishingLoc++;
			if(!shark[fishingLoc].isEmpty()) {
				temp = shark[fishingLoc].poll();
				sum += temp.size;

			}
			
			PriorityQueue<info> shark1[]= new PriorityQueue[c+1];
			for(int i = 1 ; i <shark1.length; i++)
				shark1[i] = new PriorityQueue<>();
			
			//모든 상어 이동
			for(int i =1; i <= c; i++) {
				
				while(!shark[i].isEmpty()) {
					temp = shark[i].poll();
					int row = temp.r;
					int col = temp.c;
					int size = temp.size;
					int dir = temp.dir;
					int vel = temp.v;
					int newr=row, newc=col;
					//arr[row][col] = 0;
					
					newr = row + vel * locRow[dir];
					newc = col + vel * locCol[dir];
					
					while(newr >= r+1 || newr <=0 || newc <= 0||newc >=c+1) {
						if(dir == 1)
							dir =2;
						else if(dir == 2)
							dir = 1;
						else if(dir == 4)
							dir = 3;
						else 
							dir = 4;
						if(newr >= r+1) 
							newr = r-(newr - r);				
						else if(newr <= 0)
							newr = -newr+2;
						else if(newc <= 0)
							newc = -newc +2;
						else
							newc = c-(newc - c);
							
					}

					boolean hasSameValue =false;
					if(shark1[newc].size() == 0)//처음이라면
						shark1[newc].add(new info(newr,newc,vel,dir,size));
					//현재 그 자리에 상어가 있는 지 검사
					else {
						Iterator<info> itr = shark1[newc].iterator();
						
						while(itr.hasNext()) {
							temp1 = itr.next();
							if(temp1.r == newr) {
								hasSameValue = true;
								if(temp1.size < size) {
									shark1[newc].remove(temp1);
									shark1[newc].add(new info(newr,newc,vel,dir,size));
								}
								break;
							}
						}
						if(!hasSameValue)
							shark1[newc].add(new info(newr,newc,vel,dir,size));
					}

				}
				
			}
			shark = shark1;
		}
	}

}
