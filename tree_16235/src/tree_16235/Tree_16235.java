package tree_16235;
import java.util.*;
import java.io.*;

class Tree implements Comparable<Tree>{
	int row;
	int col;
	int age;
	boolean die = false;
	
	public Tree(int r, int c, int age) {
		row = r;
		col = c;
		this.age = age;				
	}
	
	@Override
	public int compareTo(Tree o) {
		// TODO Auto-generated method stub
		return this.age - o.age;
	}
}
public class Tree_16235 {
	static ArrayList<Tree> tree = new ArrayList<>();
	static LinkedList<Tree> die = new LinkedList<>();
	static int k;
	static int arr[][];
	static int map[][];
	//static PriorityQueue <Tree> pq;
	static int locRow[] = {1,1,1,0,0,-1,-1,-1};
	static int locCol[] = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n =Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr= new int[n+1][n+1];
		map = new int[n+1][n+1];
		
		for(int i = 1; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		PriorityQueue<Tree> pq = new PriorityQueue<>();;
		for(int i =0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			pq.add(new Tree(x,y,age));
		}
		
		for(int i =0; i < k; i++) {
			
			pq = bfs(pq, i);
		}
		System.out.println(pq.size());
	}
	
	public static PriorityQueue<Tree> bfs(PriorityQueue<Tree> pq ,int year) {
		
		PriorityQueue<Tree> newpq = new PriorityQueue<>();
		Tree temp;
		int row, col, treeage;
		//봄,가을
		//Iterator<Tree> itr = pq.iterator();
		while(!pq.isEmpty()) {
			temp = pq.poll();
			row = temp.row;
			col = temp.col;
			treeage = temp.age;
			
			if(map[row][col] >= treeage) {
				map[row][col] -= treeage;
				//temp.age++;
				newpq.add(new Tree(row, col, treeage+1));
				
				if((treeage+1) % 5 ==0) {
					//인접한 곳에 나무 나이1인 나무들 번식
					for(int j = 0; j < locRow.length; j++) {
						if(row+locRow[j] >=1 && row+locRow[j] < map.length &&
								col+locCol[j] >=1 && col+locCol[j] <map[0].length) {
							newpq.add(new Tree(row+locRow[j],col+locCol[j], 1));
						}
					}
				}
			}
			else {
				die.add(temp);
				
			}
		}
		
		//itr= pq.iterator();
		//여름
		while(!die.isEmpty()) {
			temp = die.poll();
			row = temp.row;
			col = temp.col;
			treeage = temp.age;
			
			map[row][col] += treeage/2;
		}
		
		//겨울
		//양분 추가
		for(int i = 1; i< arr.length; i++) {
			for(int j = 1; j < arr[0].length; j++)
				map[i][j] += arr[i][j];
		}
		return newpq;
	}

}
