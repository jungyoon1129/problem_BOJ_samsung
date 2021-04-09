package dice_17825;
import java.util.*;
/*
 * 나는 모든 경로가 도착점까지 가는 걸로 구현했으므로
 * 25부터 40까지는 다 visit을 여러 경로에서 확인해야함
 */
public class Dice_17825 {
	static int arr[][];
	static ArrayList<Integer> blue = new ArrayList<>();
	static int horse[][];
	static int max = -1;
	static boolean visit[][];
	static int d[] = new int[10];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		arr = new int[5][];
		horse = new int[4][2];
		visit = new boolean[5][];
		visit[0] = new boolean[21];
		visit[1] = new boolean[8];
		visit[2] = new boolean[7];
		visit[3] = new boolean[8];
		
		arr[0] = new int[21];
		arr[1] = new int[8];
		arr[2] = new int[7];
		arr[3] = new int[8];
		//arr[4] = new int[9];
				
		for(int i =1; i < 21; i++) {
			//시작은 0 도착은 -1
			arr[0][i] = i*2;						
		}
		//arr[0][21] = -1;
		
		arr[1][0] = 10;
		arr[1][1] = 13;
		arr[1][2] = 16;
		arr[1][3] = 19;
		arr[1][4] = 25;
		arr[1][5] = 30;
		arr[1][6] = 35;
		arr[1][7] = 40;
		//arr[1][8] = -1;
		
		arr[2][0] = 20;
		arr[2][1] = 22;
		arr[2][2] = 24;
		arr[2][3] = 25;
		arr[2][4] = 30;
		arr[2][5] = 35;
		arr[2][6] = 40;
		//arr[1][7] = -1;

		arr[3][0] = 30;
		arr[3][1] = 28;
		arr[3][2] = 27;
		arr[3][3] = 26;
		arr[3][4] = 25;
		arr[3][5] = 30;
		arr[3][6] = 35;
		arr[3][7] = 40;
		//arr[1][8] = -1;
		
		//arr[4][1] = 25;
		//arr[4][2] = 30;
		//arr[4][3] = 35;
		//arr[4][3] = 40;		
		
		blue.add(5);
		blue.add(10);
		blue.add(15);		
			
		for(int i = 0; i < 10; i++)
			d[i] = sc.nextInt();
		
		dfs(0,0);
		System.out.println(max);
	}
	
	public static void dfs(int cnt,  int sum) {
		//5->1,10->2,15->3
		//num번째 주사위의 값은 d[cnt]
		if(cnt == 10) {
			max = Math.max(sum, max);
			return;
		}
		
		for(int j =0; j < horse.length; j++) {
			//j번째 말에 대해서 이동
			int num = horse[j][0];
			int index = horse[j][1];
			int go = index + d[cnt];
			//도착칸에 있는 말인지 확인
			int gonum;
			int goindex;
			if(num == 0 && index == 21) {
				//이미 끝
				continue;
			}
			if(num == 0 && blue.contains(go)){
				//옮길 위치
				gonum = go / 5;
				goindex = 0;
				//go = index;
			}
			else {
				gonum = num;
				goindex = go;
			}
			
			//범위가 넘어가지 않는 곳에 위치한다면
			if(goindex < arr[gonum].length) {
				//갈 곳에 다른 말이 있는 지 확인
				if(find(gonum,goindex)) {
					//아무도 없는 경우 내꺼
					horse[j][0] = gonum;
					horse[j][1] = goindex;
					visit[num][index] = false;
					visit[gonum][goindex] = true;
					dfs(cnt+1, sum + arr[gonum][goindex]);
					visit[gonum][goindex] = false;
					visit[num][index] = true;
					horse[j][0] = num;
					horse[j][1] = index;
				}
				else {
					//있는 경우 
					continue;
					
				}
			}
			else {
				//도착!
				visit[num][index] = false;
				horse[j][0] = 0;
				horse[j][1] = 21;
				dfs(cnt+1, sum);
				horse[j][0] = num;
				horse[j][1] = index;
				visit[num][index] = true;
			}
			if(cnt ==0)
				break;
		}		
	}
	
	public static boolean find(int gonum, int goindex) {
		int ch[][] = {{1, 4}, {2, 3}, {3, 4}};
		int ch1[][] = {{0, 20}, {1, 7}, {2, 6}, {3, 7}};
		//30
		int ch2[][] = {{1,5},{2,4},{3,5}};
		int ch3[][] = {{1,6},{2,5},{3,6}};
		if((gonum == 1 && goindex == 4 )|| 
				(gonum == 2 && goindex == 3 )|| 
				(gonum == 3 && goindex == 4 )) {
			for(int i =0 ; i < ch.length; i++) {
				if(visit[ch[i][0]][ch[i][1]]) {
					return false;
				}
			}
			return true;
		}
		else if((gonum == 0 && goindex == 20 )||
				(gonum == 1 && goindex == 7 )|| 
				(gonum == 2 && goindex == 6)|| 
				(gonum == 3 && goindex == 7 )) {
			for(int i =0 ; i < ch1.length; i++) {
				if(visit[ch1[i][0]][ch1[i][1]]) {
					return false;
				}
			}
			return true;
		}
		else if((gonum == 1 && goindex == 5 )||
				 
				(gonum == 2 && goindex == 4)|| 
				(gonum == 3 && goindex == 5 )) {
			for(int i =0 ; i < ch2.length; i++) {
				if(visit[ch2[i][0]][ch2[i][1]]) {
					return false;
				}
			}
			return true;
		}
		else if((gonum == 1 && goindex == 6 )||
				 
				(gonum == 2 && goindex == 5)|| 
				(gonum == 3 && goindex == 6 )) {
			for(int i =0 ; i < ch3.length; i++) {
				if(visit[ch3[i][0]][ch3[i][1]]) {
					return false;
				}
			}
			return true;
		}
		else {
			return !visit[gonum][goindex];
		}
	}
	

}
