package chickenDelivery_15686;
import java.util.*;
import java.io.*;

class Graph{
	int row;
	int col;
	public Graph(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class ChickenDelivery_15686 {
	static ArrayList<Graph> home = new ArrayList<>();
	static LinkedList<Graph> chicken = new LinkedList<>();
	static boolean visited[][];
	static int n, m;
	static LinkedList <Graph> select;
	//static int sum;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int arr[][] = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];
		
		for(int i =1; i <arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j =1; j < arr[0].length; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp==0)	continue;
				else if(temp==1)	
					home.add(new Graph(i,j));
				else 
					chicken.add(new Graph(i, j));
			}
		}
		
		
		if(chicken.size() == m)
			calcCityDistance(chicken);
		else {
			select = new LinkedList<>();
			dfs(0,0);
		}
		System.out.println(ans);
	}
	public static void calcCityDistance(LinkedList<Graph> select) {
		//select의 치킨 집과 home의 집 간의 최소거리 구하기
		Graph temp1, temp2;
		int sum = 0;
		for(int i = 0 ; i < home.size(); i++) {
			int min = Integer.MAX_VALUE;
			Iterator<Graph> itr = select.iterator();
			temp2 = home.get(i);
			
			while(itr.hasNext()) {
				temp1 = itr.next();
				min = Math.min(min,calcDis(temp1, temp2));
			}
			sum+=min;
		}
		ans = Math.min(ans, sum);
	}
	
	public static int calcDis(Graph g1, Graph g2) {
		return Math.abs(g1.row-g2.row)+Math.abs(g1.col-g2.col);
		
	}
	public static void dfs(int start, int count) {
		if(count == m) {
			//도시의 치킨 거리 세기
			calcCityDistance(select);
			return;
		}
			
		
		Graph temp;
		for(int i = start; i < chicken.size(); i++) {
			temp = chicken.get(i);
			int row = temp.row;
			int col = temp.col;
			
			if(visited[row][col])	continue;
			
			select.addLast(temp);
			visited[row][col] = true;
			
			dfs(i+1, count+1);
			
			select.removeLast();
			visited[row][col] =false;
		}
	}

}
