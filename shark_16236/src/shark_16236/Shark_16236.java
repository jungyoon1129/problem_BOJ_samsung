package shark_16236;
import java.util.*;
import java.io.*;
/*
 * https://devje8.tistory.com/11
 * https://mygumi.tistory.com/339
 */
class graph implements Comparable<graph>{
	int row, col;
	int size;
	int dist;
	public graph(int row, int col, int size, int dist) {
		this.row = row;
		this.col = col;
		this.size  =size;
		this.dist = dist;		
	}
	@Override
	public int compareTo(graph o) {
		if(this.dist != o.dist)
			return this.dist - o.dist;
		else {
			//거리가 같을 경우
			if(this.row != o.row)
				return this.row-o.row;
			else {
				//놓이가 같은 경우
				return this.col-o.col;
			}
				
		}
	}
	
}

public class Shark_16236 {
	static int sharkRow, sharkCol;
	static int arr[][];
	static int size;
	static ArrayList<graph> goal = new ArrayList<>();
	static int min = Integer.MAX_VALUE;
	static boolean visited[][];
	static int locRow[] = {-1,0,0,1};
	static int locCol[] = {0,-1,1,0};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		size = 2;
		int ans = 0;
		int fishnum=0;
		LinkedList<graph> fish = new LinkedList<>();
		
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j =0; j < arr[0].length; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==0)
					continue;
				if(temp == 9) {
					//아기 상어 위치
					sharkRow = i;
					sharkCol = j;
					fish.add(new graph(i,j,size,0));
					arr[i][j] = 0;
					//visited[i][j] =true;
				}
				else {
					fishnum++;
					arr[i][j] = temp;
				}
				
			}
		}
		int eatCount=0;
		graph temp;
		int r=0,c=0;
		int sum=0;
		boolean plus =false;
		//visited = new boolean[n][n];
		//int find = -1;
		while(true) {
			ArrayList<graph> fishlist = new ArrayList<>();
			visited = new boolean[n][n];
			int find=-1;
			while(!fish.isEmpty()) {
				
				temp = fish.poll();
				int row = temp.row;
				int col = temp.col;
				int dist = temp.dist;
				int size =temp.size;
				visited[row][col] =true;
				//int find = -1;
				
				if(find == dist)
					break;
				for(int i = 0; i< locRow.length; i++) {
					r = row+locRow[i];
					c = col+locCol[i];
					
					if(r >= 0 && r < arr.length && c>=0 && c < arr[0].length) {
						int tmp =arr[r][c];
						
						if(visited[r][c])
							continue;
						
						visited[r][c] = true;
						if(tmp > size)
							continue;
						if(tmp==0) {
							fish.add(new graph(r,c,size,dist+1));
							
						}						
						
						else if(tmp==size) {							
								fish.add(new graph(r,c,size,dist+1));
								//visited[r][c] = true;
						}
						else {
							
							find = dist + 1;
							fishlist.add(new graph(r,c,size,dist+1));
							fish.add(new graph(r,c,size,dist+1));
						}																				
					}
				}
				//
			}
			fish.clear();

			int size;
			if(fishlist.isEmpty())
				break;
			else if(fishlist.size() == 1) {
				temp = fishlist.get(0);
				size = temp.size;
				sum += temp.dist;
				ans++;
				eatCount++;
				arr[temp.row][temp.col] =0;
				if(eatCount == temp.size) {
					eatCount = 0;
					size++;
				}
				fish.add(new graph(temp.row, temp.col, size, 0));

			}
			else {
				//여러개 있는 경우
				Collections.sort(fishlist);
				temp = fishlist.get(0);
				size =temp.size;
				
				sum += temp.dist;
				eatCount++;
				ans++;
				arr[temp.row][temp.col] =0;
				if(eatCount == size) {				
					eatCount = 0;
					size++;
				}
				fish.add(new graph(temp.row, temp.col, size, 0));

			}
			
		}
		
		
		System.out.println(sum);
	}
	
	
}
