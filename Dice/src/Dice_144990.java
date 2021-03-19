import java.io.*;
import java.util.*;

public class Dice_144990 {
	public static int arr[][];
	public static int row;
	public static int column;
	public static LinkedList<Integer> ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < arr[0].length; j++) {
				//StringTokenizer st1 = new StringTokenizer(br.readLine());
				arr[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		
		int dice[][] = new int[3][4];
		
		//맨 처음에는 주사위의 모든 면이 0이다.
		//주사위의 맨 아래 면을 dice[1][1]로 설정 십자가 모양의 전개도
		if(arr[x][y] != 0)	
			dice[1][1] = arr[x][y];
		row = x;
		column = y;
				
		ans = new LinkedList<>();
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			dice = solution(dice, Integer.parseInt(st1.nextToken()));
		}
		
		while(!ans.isEmpty()) {
			System.out.println(ans.poll());
		}
	}
	
	public static int[][] solution(int dice[][], int dir){
		switch(dir) {
		case 1:
			return right(dice);
			
		case 2:
			return left(dice);
			
		case 3:
			return up(dice);
			
		case 4:
			return down(dice);
			
		default:
			return dice;
		}
	}
	
	public static int[][] down(int dice[][]){
		int currentDice[][] = new int[dice.length][dice[0].length];
		
		//boolean flag = false;
		if(row + 1 < 0 || row + 1 >= arr.length ) {
			//지도 밖으로 나가려함.
			return dice;
		}
		//지도 좌표 이동
		row = row +1;
		
		//주사위 이동
		currentDice[0][1] = dice[1][1];
		currentDice[1][0] = dice[1][0];
		currentDice[1][1] = dice[2][1];	//아래면
		currentDice[1][2] = dice[1][2];
		currentDice[1][3] = dice[0][1];	//위면
		currentDice[2][1] = dice[1][3];
		
		if(arr[row][column] == 0) {
			//지도가 0라면
			arr[row][column] = currentDice[1][1];			
		}
		else {
			currentDice[1][1] = arr[row][column];
			arr[row][column] = 0;
		}
		ans.add(currentDice[1][3]);
		return currentDice;
	}
	
	public static int[][] up(int dice[][]){
		int currentDice[][] = new int[dice.length][dice[0].length];
		
		//boolean flag = false;
		if(row - 1 < 0 || row - 1 >= arr.length ) {
			//지도 밖으로 나가려함.
			return dice;
		}
		//지도 좌표 이동
		row = row -1;
		
		//주사위 이동
		currentDice[0][1] = dice[1][3];
		currentDice[1][0] = dice[1][0];
		currentDice[1][1] = dice[0][1];	//아래면
		currentDice[1][2] = dice[1][2];
		currentDice[1][3] = dice[2][1];	//윗면
		
		currentDice[2][1] = dice[1][1];
		
		if(arr[row][column] == 0) {
			//지도가 0라면
			arr[row][column] = currentDice[1][1];			
		}
		else {
			currentDice[1][1] = arr[row][column];
			arr[row][column] = 0;
		}
		ans.add(currentDice[1][3]);
		return currentDice;
	}
	
	public static int[][] left(int dice[][]){
		int currentDice[][] = new int[dice.length][dice[0].length];
		
		//boolean flag = false;
		if(column - 1 < 0 || column - 1 >= arr[0].length ) {
			//지도 밖으로 나가려함.
			return dice;
		}
		//지도 좌표 이동
		column = column -1;
		
		//주사위 이동
		currentDice[0][1] = dice[0][1];
		currentDice[1][0] = dice[1][1];
		currentDice[1][1] = dice[1][2];	//아래면
		currentDice[1][2] = dice[1][3];
		currentDice[1][3] = dice[1][0];	//윗면
		
		currentDice[2][1] = dice[2][1];
		
		if(arr[row][column] == 0) {
			//지도가 0라면
			arr[row][column] = currentDice[1][1];			
		}
		else {
			currentDice[1][1] = arr[row][column];
			arr[row][column] = 0;
		}
		ans.add(currentDice[1][3]);
		return currentDice;
	}
	public static int[][] right(int dice[][]){
		int currentDice[][] = new int[dice.length][dice[0].length];
		
		//boolean flag = false;
		if(column + 1 < 0 || column + 1 >= arr[0].length ) {
			//지도 밖으로 나가려함.
			return dice;
		}
		//지도 좌표 이동
		column = column +1;
		
		//주사위 이동
		currentDice[0][1] = dice[0][1];
		currentDice[1][0] = dice[1][3];
		currentDice[1][1] = dice[1][0];	//아래면
		currentDice[1][2] = dice[1][1];
		currentDice[1][3] = dice[1][2];	//윗면
		
		currentDice[2][1] = dice[2][1];
		
		if(arr[row][column] == 0) {
			//지도가 0라면
			arr[row][column] = currentDice[1][1];			
		}
		else {
			currentDice[1][1] = arr[row][column];
			arr[row][column] = 0;
		}
		ans.add(currentDice[1][3]);
		return currentDice;
	}
}
