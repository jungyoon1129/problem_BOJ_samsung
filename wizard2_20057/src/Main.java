import java.util.*;
import java.io.*;

class info{
	int r,c,per;
	public info(int r, int c, int per) {
		this.r = r;
		this.c = c;
		this.per = per;
	}
	public info(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
public class Main {
	static int arr[][];
	static int row;
	static int col;
	static int outside = 0;
	//left는 0, right는 1 , down 2, up 3
	//1,1,2,2,7,7,10,10,5 알파
	static int locrow[][] = 
		{{ -1, 1, -1, 1, -1, 1, -2, 2, 0, 0 },{ -1, 1, -1, 1, -1, 1, -2, 2, 0, 0 },
            { 0, 0, 1, 1, 2, 2, 1, 1, 3, 2}, { 0, 0, -1, -1, -2, -2, -1, -1, -3, -2}};
	
	static int loccol[][] = 
		{{ 0, 0, 1, 1, 2, 2, 1, 1, 3, 2}, { 0, 0, -1, -1, -2, -2, -1, -1, -3, -2},
            { -1, 1, -1, 1, -1, 1, -2, 2, 0, 0}, {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0}
		};
	static int percent[] = { 1, 1, 7, 7, 10, 10, 2, 2, 5 };
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n+1][n+1];
		
		for(int i =1; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j < arr[0].length; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		row = n/2+1;
		col = n/2+1;
		
		
		for(int i =1; i <= n; i++) {
			if(i % 2==1) {
				//왼쪽, 아래
				for(int j =1; j <=i; j++) {
					solve(1);
					
				}
				if(i != n) {
					for(int j =1; j <=i; j++) {
						solve(2);
						
					}
				}
				
			}
			else {
				//오른쪽, 위
				for(int j =1; j <=i; j++) {
					solve(0);
					
				}
				for(int j =1; j <=i; j++) {
					solve(3);
					
				}
			}
			
		}
		
		System.out.println(outside);
	}
	public static void solve(int direction) {
		int use = 0;
		int newr,newc,amount;
		int total = 0;
		
		switch(direction) {
		case 0: 
			if(col+1 >= arr[0].length)	return;
			total = arr[row][col+1];
			break;
		case 1:
			if(col-1 < 1)	return;
			total = arr[row][col-1];
			break;
			
		case 2:	
			
			if(row+1 >= arr.length)	return;
			total = arr[row+1][col];
			break;
		case 3:
			if(row-1 < 1)	return;
			total = arr[row-1][col];
			break;
		}
		if(total!=0) {
			for(int i =0 ;i < locrow[direction].length; i++) {
				newr = locrow[direction][i] + row;
				newc = loccol[direction][i] + col;
				
				if(i != locrow[direction].length-1)
					amount = total * percent[i] / 100;
				else
					amount = total-use;
				
				if(newr >=1 && newr < arr.length && newc >=1 && newc < arr[0].length) {
					
					use += amount;
					arr[newr][newc] += amount;
								
				}
				else {
					//바깥에 버림
					use += amount;
					outside += amount;
				}
			}
		}
		
		
		
		switch(direction) {
		case 0: 
			//col = col-1;
			//row = row;
			col = col+1;
			//total = arr[row][col];
			break;
		case 1:
			//col = col+1;
			//row = row;
			col = col-1;
			//total = arr[row][col];
			break;
		case 2:
			row = row+1;
			break;
		case 3:
			row = row-1;
			break;
		}
		arr[row][col] = 0;
	}

}
