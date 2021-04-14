import java.util.*;
import java.io.*;

class graph{
	int r,c,dir;
	public graph(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.dir = d;		
	}
}
public class Main {
	static int locrow[] = {-1,-1,0,1,1,1,0,-1};
	static int loccol[] = {0,-1,-1,-1,0,1,1,1};
	static int arr[][] = new int[4][4];
	static graph fish[] = new graph[17];
	static int sum = 0;
	static int sharkrow, sharkcol, sharkdir;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());
			for(int j = 0; j < arr[0].length; j++) {
				//int a = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				fish[num] = new graph(i, j, dir-1);
			}
		}
		int startr=0, startc=0;
		sum += arr[startr][startc];
		arr[startr][startc] = -1;
		sharkrow = startr;
		sharkcol = startc;
		fish[sum].r = -1;
		sharkdir = fish[sum].dir;
		int r,c,dir;
		int newr, newc;
		graph temp;
		//-1�� �� �ִ� ��
		dfs(arr, fish);
		System.out.println(max);
	}
	
	public static int[][] deeparrcopy(int arr[][]){
		int newarr[][] = new int[4][4];
		
		for(int i = 0; i < arr.length; i++) {
			
			for(int j  =0; j < arr[0].length; j++) {
				newarr[i][j] = arr[i][j];
			}
		}
		return newarr;
	}
	
	public static graph[] deepfishcopy(graph fish[]){
		graph newfish[] = new graph[17];
		graph temp ;
		for(int i = 1; i < fish.length; i++) {
			temp = fish[i];
			newfish[i] = new graph(temp.r,temp.c,temp.dir);
		}
		return newfish;
	}
	
	public static void fishmove(int arr[][], graph fish[]) {
		int r,c,dir;
		int newr, newc;
		graph temp;
				
		for(int i =1; i < fish.length; i++) {
			temp = fish[i];
			//i��° ����⸦ ���� �������� �̵���Ų��.
			//���繰����� ��ġ�� ����
			r = temp.r;
			c = temp.c;
			dir = temp.dir-1;
			
			if(r==-1 || c==-1) {
				//�̹� ��Ƹ���
				continue;
			}
			
			for(int j =1; j <= 8; j++) {
				dir = (dir + 1) % 8;
				
				newr = r + locrow[dir];
				newc = c + loccol[dir];
				
				if(newr >= 0 && newr < arr.length &&
						newc>=0 && newc < arr.length) {
					if(newr == sharkrow && newc == sharkcol) {
						//�� �ִ� �ڸ���� �� �� ����
						continue;
					}
					else {
						if(arr[newr][newc]!=0) {
							//�ٸ� ����Ⱑ �ִٸ� �ڸ��� �ٲ�
							int newn = arr[newr][newc];
							arr[r][c] = newn;
							arr[newr][newc] = i;
							temp.dir = dir;
							temp.r = newr;
							temp.c = newc;
							fish[newn].r = r;
							fish[newn].c = c;
							break;
							
						}
						else {
							//���ڸ����
							arr[r][c] = 0;
							arr[newr][newc] = i;
							temp.dir = dir;
							temp.r = newr;
							temp.c = newc;
							break;
						}
					}
				}
				else
					continue;
				
			}
			
			
		}
	}
	
	
		
	public static void dfs(int arr[][], graph fish[]) {
		int newr = sharkrow;
		int newc = sharkcol;
		//�� ���� �� �ִ� �ֵ��� ����
		int curr = sharkrow;
		int curc = sharkcol;
		int curdir = sharkdir;
		
		fishmove(arr,fish);
		//�ĺ� ���ϱ�
		//�ĺ� ���Ѱſ��� dfsȣ��
		//ArrayList<Integer> fishlist = new ArrayList<>();
		boolean fail = true;
		for(int i=0; i < 4; i++) {
			newr = newr + locrow[sharkdir];
			newc = newc + loccol[sharkdir];
			if(newr >=0 && newr < arr.length &&
					newc >=0 && newc < 4) {
				int num = arr[newr][newc];
				if(num != 0) {
					//fishlist.add(arr[newr][newc]);
					sum += num;
					fail = false;
					arr[sharkrow][sharkcol] = 0;
					arr[newr][newc] = -1;
					sharkrow = newr;
					sharkcol = newc;
					sharkdir = fish[num].dir;
					fish[num].r = -1; 
					dfs(deeparrcopy(arr),deepfishcopy(fish));
					fish[num].r = newr; 
					sharkrow = curr;
					sharkcol = curc;
					sharkdir = curdir;
					arr[sharkrow][sharkcol] = -1;
					arr[newr][newc] = num;
					sum -= num;
				}
				else 
					continue;
			}
			else
				break;
			
	
		}
		if(fail) {
			//�� �̵����� �������
			max = Math.max(sum, max);
		}
		//newr = sharkrow;
		//newc = sharkcol;
		/*
		for(int i = 0; i < fishlist.size(); i++) {
			int num = fishlist.get(i);
			sum += num;
			//�̵�
			newr = newr + locrow[sharkdir];
			newc = newc + loccol[sharkdir];
			
			arr[sharkrow][sharkcol] = 0;
			arr[newr][newc] = -1;
			dfs(deeparrcopy(arr),deepfishcopy(fish));
			arr[sharkrow][sharkcol] = -1;
			arr[newr][newc] = num;
		}
		*/
		//search(fishlist);
	}
}
