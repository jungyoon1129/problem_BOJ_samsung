import java.util.*;
import java.io.*;

class smell{
	int n, time;
	public smell(int n, int time) {
		this.n = n;
		this.time = time;
	}
}
class info{
	int r,c,dir;
	public info(int r, int c, int dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
}
public class Main {
	static smell arr[][];
	static info shark[];
	static int ans = 0;
	static int sharklocrow[][][];
	static int sharkloccol[][][];
	static int k;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new smell[n][n];
		shark = new info[m+1];
		sharklocrow = new int[m+1][4][4];
		sharkloccol = new int[m+1][4][4];
		
		for(int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < arr[0].length; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 0)
					continue;
				shark[temp] = new info(i,j,0);
				//냄새 남기기
				arr[i][j] = new smell(temp, k);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i =1; i <= m; i++) {
			int x = Integer.parseInt(st.nextToken());
			shark[i].dir = x-1;
		}
		
		for(int i = 1; i <=m; i++) {
			//i번째 상어가 j방향일때 우선순위
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int l = 0; l < 4; l++) {
					int x = Integer.parseInt(st.nextToken());
					
					switch(x-1) {
					case 0:
						sharklocrow[i][j][l] = -1;
						break;
					case 1:
						sharklocrow[i][j][l] = 1;
						break;
					case 2:
						sharkloccol[i][j][l] = -1;
						break;
					case 3:
						sharkloccol[i][j][l] = 1;
						break;
					}
				}
					
			}
		}
		sharkMove();
		System.out.println(ans);
	}
	
	public static int direction(int r, int c) {
		if(r == -1 && c == 0) {
			return 0;
		}
		else if(r == 1 && c==0)
			return 1;
		else if(r ==0 && c==-1)
			return 2;
		else 
			return 3;
	}
	
	public static int directionRow(int dir) {
		switch(dir) {
		case 2:
		case 3:
			return 0;
		case 1: 
			return 1;
		case 0:
			return -1;
			
		}
		return dir;
	}
	
	public static int directionCol(int dir) {
		switch(dir) {
		case 0:
		case 1:
			return 0;
		case 2: 
			return -1;
		case 3:
			return 1;
			
		}
		return dir;
	}
	
	public static void sharkMove() {
		int time=0;
		int count = 0;
		
		while(true) {
			if(time >= 1000) {
				ans = -1;
				break;
			}
			//냄새 -1씩하기
			smell s;
			smell newarr[][] = new smell[arr.length][arr[0].length];
			for(int i  =0; i < arr.length; i++) {
				for(int j = 0; j < arr[0].length; j++) {
					if(arr[i][j] != null) {
						s = arr[i][j];
						if(s.time == 1) {
							s.time--;
						}
						else {
							s.time--;
							newarr[i][j] = s;
						}
					}
				}
			}
			
			//이동 + 냄새 남기기	
			int dir,r,c;
			int newr,newc,newdir,tempdir;
			
			int mysmellrow=-1, mysmellcol=-1 ,mysmelldir = 0;
			
			for(int i = 1; i < shark.length; i++) {
				dir = shark[i].dir;
				r = shark[i].r;
				c = shark[i].c;
				
				boolean success = false;
				//int mysmellrow = -1;
				//int mysmellcol = -1;
				if(r == -1)
					continue;
				
				boolean mysmellalready = false;
				
				for(int j = 0; j < 4; j++) {
					tempdir = direction(sharklocrow[i][dir][j],sharkloccol[i][dir][j]);
					newr = r+ sharklocrow[i][dir][j];
					newc = c+ sharkloccol[i][dir][j];
									
					
					if(newr >=0 && newr < arr.length && newc >=0 && newc < arr.length) {
						if(arr[newr][newc]==null) {
							//아무 냄새도 없는 경우
							success = true;
							if(newarr[newr][newc] == null) {
								//겹치치 않는다면
								shark[i].r = newr;
								shark[i].c = newc;
								shark[i].dir = tempdir;
								
								newarr[newr][newc] = new smell(i,k);
								break;
							}
							else {
								//겹친다면
								smell temp = newarr[newr][newc];
								if(temp.n < i) {
									//상어 소멸
									shark[i].r = -1;
									count++;
								}
								else {
									temp.n = i;
									shark[temp.n].r = -1;
									shark[i].r = newr;
									shark[i].c = newc;
									shark[i].dir = tempdir;
									count++;
								}
								break;
							}
						}
						else if(arr[newr][newc].n == i) {
							//내 냄새라면 여기가 일순위
							if(mysmellalready)
								continue;
							
							mysmellalready = true;
							mysmellrow = newr;
							mysmellcol = newc;
							mysmelldir = tempdir;
							
						}
						else {
							//남의 냄새 
							continue;
						}
					}
					
				}
				if(!success) {
					newarr[mysmellrow][mysmellcol] = new smell(i, k);
					shark[i].r = mysmellrow;
					shark[i].c = mysmellcol;
					shark[i].dir = mysmelldir;
				
				}
			}
			
			
			time++;
			//1번상어만 남았다면 끝
			
			if(count == shark.length-2) {
				ans = time;
				break;
			}
			arr = newarr;
		}
		 
	}

}
