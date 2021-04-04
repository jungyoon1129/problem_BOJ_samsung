import java.util.*;
import java.io.*;

class graph{
	int row, col;
	int val;
	public graph(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}
}

public class FineDust_17144 {
	static int arr[][];
	static LinkedList<graph> list = new LinkedList<>();
	static int locRow[] = {-1,0,1,0};
	static int locCol[] = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		arr = new int[r][c];
		int airRow1=-1, airRow2=-1;
		int cnt=0;
		
		for(int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] ==0)	continue;
				if(arr[i][j] == -1) {
					if(cnt==0)
						airRow1 = i;
					else
						airRow2=i;
					cnt++;
				}
				else {
					list.add(new graph(i,j, arr[i][j]));
				}
			}
		}
		
		
		graph temp;
		int row, col;
		int newr,newc;
		int dust;
		int count = 0;
		
		for(int time = 0; time < t; time++) {
			
			while(!list.isEmpty()) {
				temp = list.poll();
				row = temp.row;
				col = temp.col;
				dust = temp.val/5;
				
				//확산
				for(int i = 0; i < locRow.length; i++) {
					newr = row + locRow[i];
					newc = col + locCol[i];
					//if(newr == 3 && newc==)
					if(newr >= 0 && newr < arr.length && newc >= 0 && newc< arr[0].length) {
						if(newc == 0  && ((newr == airRow1 )|| (newr == airRow2)))//공기청정기 위치
							continue;
						else {
							arr[newr][newc] += dust;
							arr[row][col] -= dust;
						}
					}
				}
				
			}
			
			LinkedList<Integer> tmp = new LinkedList<>();
			
			//공기청정기 순환
			//위
			tmp.add(arr[airRow1][1]);
			arr[airRow1][1] = 0;
			for(int j =1; j < arr[0].length-1; j++) {			
				tmp.add(arr[airRow1][j+1]);
				arr[airRow1][j+1] = tmp.poll();					
			}
			for(int i = airRow1; i>0; i--) {
				tmp.add(arr[i-1][arr[0].length-1]);
				arr[i-1][arr[0].length-1] = tmp.poll();
			}
			for(int j =arr[0].length-1; j >0; j--) {			
				tmp.add(arr[0][j-1]);
				arr[0][j-1] = tmp.poll();					
			}
			for(int i = 0; i<airRow1-1; i++) {
				tmp.add(arr[i+1][0]);
				arr[i+1][0] = tmp.poll();
			}
			tmp.clear();
			
			//아래
			tmp.add(arr[airRow2][1]);
			arr[airRow2][1] = 0;
			for(int j =1; j < arr[0].length-1; j++) {			
				tmp.add(arr[airRow2][j+1]);
				arr[airRow2][j+1] = tmp.poll();					
			}
			for(int i = airRow2; i < arr.length-1; i++) {
				tmp.add(arr[i+1][arr[0].length-1]);
				arr[i+1][arr[0].length-1] = tmp.poll();
			}
			for(int j =arr[0].length-1; j >0; j--) {			
				tmp.add(arr[arr.length-1][j-1]);
				arr[arr.length-1][j-1] = tmp.poll();					
			}
			for(int i = arr.length-1; i > airRow2+1; i--) {
				tmp.add(arr[i-1][0]);
				arr[i-1][0] = tmp.poll();
			}
			
			//남은 미세먼지 queue에 넣기
			for(int i = 0; i < arr.length; i++) {			
				for(int j =0; j < arr[0].length; j++) {					
					if(arr[i][j] ==0 || arr[i][j] == -1)	continue;					
					
					list.add(new graph(i,j, arr[i][j]));	
				}
			}
			
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == 0|| arr[i][j]==-1)	continue;
				count+=arr[i][j];
			}
		}
		System.out.println(count);
		
		
		
	}

}
