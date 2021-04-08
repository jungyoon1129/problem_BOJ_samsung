import java.io.*;
import java.util.*;

public class CircleRotation_17822 {
	//static ArrayList<Integer> list[];
	static int list[][];
	static int n;
	static int start[];
	static int m;
	static boolean visited[][];
	static boolean success = false;
	static int sum = 0;
	static int count =0;
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		start = new int[n+1];
		list = new int[n+1][m];
		count = m * n;
		
		for(int i = 1; i < list.length; i++) {
			st = new StringTokenizer(br.readLine());
			//list[i] = new ArrayList<>();
			
			for(int j = 0; j <m; j++) {
				list[i][j] = Integer.parseInt(st.nextToken());
				sum += list[i][j];
			}
		}
		//int x[] = new x
		int x,d,k;
		for(int i =1 ; i <= t; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			//d가 0이면 시계방향, 1이면 반시계방향
			//1이고 k가 1이면 시계방향으로 3번
			//1이고 k==2 -> 시계방향으로 2번
			// k==3이면 1번
			
			//x의 배수를 시계방향으로 k만큼 움직인다.
			if(d== 0) {
				//시계방향이면 그냥 회전
				rotation(x,k);
			}
			else {
				//반시계 방향인 경우
				k = m - k;
				rotation(x,k);
			}
			
		}
		
		bw.flush();
		
		System.out.println(sum);
	
	}
	
	public static void rotation(int x, int k) throws IOException {
		
		// x의 배수를 k만큼 회전
		for(int t = 0; t < k; t++) {
			for(int i=x; i <= n; i+=x) {
				int s = start[i];
				s = (s - 1 + m) % m;
				start[i] = s;
				//시작 위치를 바꾼다.		
			}
		}
		
		//print();
		// 같은 수가 있으면 지운다
		// 1<= <=1000
		//visited = new boolean[n+1][m];
		remove();
		
	}
	
	public static void dfs(int i, int s, int index, int standard) {
		boolean localsuccess = false;
		//if(list[i][s]!=0) {
			//위의 if를 넣으면 dfs전에 0으로 초기화하니까 진행 못함!!
		//standard 자체가 0이 아닌 값이므로 아래 조건들에서 다 걸러진다.
			//주변 동료 확인
			//int standard = list[i][s];
			int left = (s-1+m) % m;					
			if(standard == list[i][left]) {
				success = true;
				localsuccess = true;
				count--;
				sum -= list[i][left];
				list[i][left] = 0;
				dfs(i, left, (index-1+m)%m, standard);
			}
			
			int right = (s+1) % m;
			if(standard == list[i][right]) {
				success = true;
				localsuccess = true;
				count--;
				sum -= list[i][right];
				list[i][right] = 0;
				dfs(i, right, (index+1)%m, standard);
			}
			
			//더 큰 원판
			if(i+1 <= n) {
				int up = (start[i+1] + index) % m;
				if(standard == list[i+1][up]) {
					success = true;
					localsuccess = true;
					count--;
					sum -= list[i+1][up];
					list[i+1][up] = 0;
					dfs(i+1, up, index, standard);
				}
			}
			
			//더 작은 원판
			if(i-1 >= 1) {
				int down = (start[i-1] + index) % m;
				if(standard == list[i-1][down]) {
					success = true;
					localsuccess = true;
					count--;
					sum -= list[i-1][down];
					list[i-1][down] = 0;
					dfs(i-1, down, index, standard);
				}
			}
	//	}
		if(localsuccess && list[i][s] !=0) {
			sum -= list[i][s];
			count--;
			list[i][s] = 0;
		}
	}
	
	public static void remove() throws IOException {
		success = false;
		//모두 0인거랑 지울게
		for(int i = 1; i <= n; i++) {
			//1번 원판부터 시작
			int s = start[i];
			for(int j = 0; j < m; j++) {
				
				if(list[i][s] != 0) {
					int standard = list[i][s];
					//list[i][s]이 값과 같은 값을 지우는 dfs
					dfs(i, s, j, standard);
					//if(success)
					
				}
				else {
					
				}
				
				s = (s+1)%m;
			}
			//System.out.println();
		}
		if(!success) {
			double avg = (double)sum / count;
			search(avg);
		}
		//print();
		//System.out.println();
	}
	
	public static void print() throws IOException {
		for(int i =1; i <= n; i++) {
			int s = start[i];
			for(int j = 0; j<m; j++) {
				bw.write(list[i][s] + " ");
				s = (s + 1)%m;
			}
			bw.write("\n");
		}
		bw.write("\n");
	}
	
	public static void search(double avg) {
		for(int i=1; i <= n; i++) {
			for(int j =0; j < m; j++) {
				if(list[i][j] != 0) {
					int temp = list[i][j];
					if(temp < avg) {
						//평균보다 작은수
						list[i][j]++;
						sum++;
					}
					else if(temp > avg) {
						list[i][j]--;
						sum--;
						if(list[i][j] == 0)
							count--;
					}
				}
			}
		}
	}

}
