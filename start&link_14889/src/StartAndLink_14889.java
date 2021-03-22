import java.io.*;
import java.util.*;

public class StartAndLink_14889 {
	static int arr[][];
	static int min = Integer.MAX_VALUE;
	static int n;
	static boolean visited[];
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		visited = new boolean[n+1];
		stack = new Stack<>();
		
		for(int i = 1; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(1,0);
		System.out.println(min);
	}
	
	public static void dfs(int start, int count) {
		if(count == n/2) {
			//min 계산하기
			min = Math.min(min, calcDifference());
			return;
		}
		for(int i = start; i <= n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				stack.add(i);
				dfs(i,count+1);
				stack.pop();
				visited[i] = false;
			}
		}
		
	}
	
	public static int calcDifference() {
		int arr1[] = new int[n/2];
		int arr2[] = new int[n/2];
		int j=0, k=0;
		boolean visited[] = new boolean[n+1];
		
		for(int i = 1; i < arr.length; i++) {
			if(stack.contains(i))	arr1[j++] = i;
			else 	arr2[k++] = i;
		}
		int sum1=0,sum2=0;
		for(int i = 0; i < n/2; i++) {
			if(!visited[arr1[i]]) {
				visited[arr1[i]] = true;
				for(int x = 0; x < arr1.length; x++) {
					if(!visited[arr1[x]])	sum1 += arr[arr1[i]][arr1[x]];
				}
				visited[arr1[i]] = false;
			}
			if(!visited[arr2[i]]) {
				visited[arr2[i]] = true;
				for(int x = 0; x < arr2.length; x++) {
					if(!visited[arr2[x]])	sum2 += arr[arr2[i]][arr2[x]];
				}
				visited[arr2[i]] = false;
			}
		}
		return Math.abs(sum1 - sum2);
	}

}
