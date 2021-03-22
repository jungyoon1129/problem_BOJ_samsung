package operator_14888;
import java.util.*;
import java.io.*;

public class Operator_14888 {
	static int arr[];
	static int operator[];
	static int visited[];
	static long max = Integer.MIN_VALUE;
	static long min = Integer.MAX_VALUE;
	static int n;
	static long ans;
	//static int current;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		operator = new int[4];
		visited = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());		
		for(int i = 0; i < 4; i++)
			operator[i] = Integer.parseInt(st.nextToken());
		
		//s = new Stack<>();
		//s.add(arr[0]);
		ans = arr[0];
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int count) {
		if(count == n-1) {
			if(max < ans)	max = ans;
			if(min > ans)	min = ans;
			return;
		}
		count++;
		long temp = ans;
		for(int i = 0; i < operator.length; i++) {
			if(visited[i] < operator[i]) {
				//¾ÆÁ÷ ¿¬»êÀÚ¸¦ ´õ ¾µ ¼ö ÀÖ´Ù¸é
				visited[i]++;
				
				switch(i) {
				case 0://µ¡¼À
					ans += arr[count];
					
					break;
				case 1: // »¬¼À
					ans -= arr[count];
					break;
				case 2: // °ö¼À
					ans *= arr[count];
					break;
				case 3: //³ª´°¼À
					ans /= arr[count];
					break;
				}
				dfs(count);
				ans = temp;
				visited[i]--;
			}
		}
	}

}
