package slope_14890;
import java.util.*;
import java.io.*;

public class Slope_14890 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int arr[][] = new int[n][n];
		
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			
			for(int j =0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		int postValue=-1, curValue;
		boolean fail = false;
		int cnt = 0;
		int num = 1;	//연속된 값의 개수
		//int count=0;
		boolean visited1[][] = new boolean[n][n];
		//옆으로 길이 있는지 확인
		for(int i = 0; i < arr.length; i++) {
			
			fail = false;
			for(int j =0; j < arr[0].length; j++) {				
				
				if(j ==0 ) {
					postValue = arr[i][j];
					continue;
				}
				
				curValue = arr[i][j];
				
				if(postValue == curValue) {
					//cnt++;
				}					
				else if(postValue > curValue) {
					//이전 값이 더 큰 경우
					if(postValue - curValue == 1) {
						//높이가 1 차이날 경우
						//visited1[i][j] = true;
						int count = 0;
						while(true) {
							
							if(j < arr[0].length) {
								if(curValue == arr[i][j]) {
									count++;
									visited1[i][j] = true;
								}
								else {
									fail = true;
									count = 0;
									break;
								}
								if(count >= l) {
									curValue = arr[i][j];
									count = 0;
									break;
								}
								else {
									j++;
								}
							}
							else {
								count = 0;
								fail = true;
								break;
							}
						}
					}
					else {
						//2이상 차이
						fail = true;
						break;
					}
				}
				else {
					if(curValue - postValue == 1) {
						//높이가 1 차이날 경우
						int tempJ=j;
						int count = 0;
						while(true) {
							if(tempJ-1 >=0) {
								
								if(!visited1[i][tempJ-1] && postValue == arr[i][--tempJ]) {
									count++;
									visited1[i][tempJ] =true;
								}
								else {
									fail = true;
									count = 0;
									break;
								}
								if(count >= l) {
									//curValue = arr[i][j];
									count = 0;
									break;
								}
								
							}
							else {
								//범위 안맞으면
								fail = true;
								count = 0;
								break;
							}
						}
					}
					else {
						//2이상 차이
						fail = true;
						break;
					}
				}
				postValue = curValue;
				if(fail)	break;
			}
			if(!fail)	cnt++;
			
		
		}
		
		
		boolean visited2[][] = new boolean[n][n];
		//위에서 아래로 길이 있는지 확인
		
		for(int i = 0; i < arr[0].length; i++) {
			
			fail = false;
			for(int j =0; j < arr.length; j++) {				
				
				if(j ==0 ) {
					postValue = arr[j][i];
					continue;
				}
				
				curValue = arr[j][i];
				
				if(postValue == curValue) {
					//cnt++;
				}					
				else if(postValue > curValue) {
					//이전 값이 더 큰 경우
					if(postValue - curValue == 1) {
						//높이가 1 차이날 경우
						//visited2[j][i] = true;
						int count = 0;
						while(true) {
							
							if(j < arr.length) {
								if(curValue == arr[j][i]) {
									count++;
									visited2[j][i] = true;
								}
								else {
									fail = true;
									break;
								}
								if(count >= l) {
									curValue = arr[j][i];
									count = 0;
									break;
								}
								else {
									j++;
								}
								
							}
							else {
								fail = true;
								break;
							}
						}
					}
					else {
						//2이상 차이
						fail = true;
						break;
					}
				}
				else {
					if(curValue - postValue == 1) {
						//높이가 1 차이날 경우
						int tempJ = j;
						int count =0 ;
						while(true) {
							
							if(tempJ-1 >=0) {
								if(!visited2[tempJ-1][i] && postValue == arr[--tempJ][i]) {
									count++;
									visited2[tempJ][i]=true;
								}
								else {
									fail = true;
									break;
								}
								if(count >= l) {
									//curValue = arr[j][i];
									count = 0;
									break;
								}
								
							}
							else {
								//범위 안맞으면
								fail = true;
								break;
							}
						}
					}
					else {
						//2이상 차이
						fail = true;
						break;
					}
				}
				postValue = curValue;
				
			}
			
			if(!fail)	cnt++;
		
		}
		
		System.out.println(cnt);
		
		
	}

}
