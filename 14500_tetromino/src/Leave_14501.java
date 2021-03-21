import java.util.*;
import java.io.*;

/*
 * 시작과 겹치는 시간들은 같이 쓸 수 없으니까 다 따로 dfs를 해줘야함
 * j날 잡힌 상담의 마지막 날이 유효한 날인지 확인하고 방문했었는지 확인한다.
 * j날 잡은 상담이 끝나면 j+arr[j][0]날부터 상담을 시작할 수 있음
	j날 상담까지의 번 돈은 sum + arr[j][1]
	j+arr[j][0]날이 유효한 날인지의 확인은 재귀 dfs를 처음 시작할때 아니면 return 하는데
    그전에 번 돈은 유효하므로 max와 비교해 크면 max값 갱신
				
	dp풀이 참고
	https://yabmoons.tistory.com/519
 */
public class Leave_14501 {
	static int arr[][];
	static boolean visited[];
	static int max =-1;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n+1][2];
		visited = new boolean[n+1];
		
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		dfs(1,0);
		System.out.println(max);
	}
	
	public static void dfs(int start, int sum) {		
		if(max < sum) max= sum;
		
		if(start >= visited.length)	return;	
		//시작과 겹치는게 있는지 확인하고 걔네들을 시작점으로 dfs
		int startDuration = arr[start][0];
			
		for(int j = start; j <= start + startDuration -1; j++) {
			//시작과 겹치는 시간들은 같이 쓸 수 없으니까 다 따로 dfs를 해줘야함
			//j날 잡힌 상담의 마지막 날이 유효한 날인지 확인하고 방문했었는지 확인한다.
			if(j < visited.length && j + arr[j][0] -1< visited.length && !visited[j]) {
				visited[j] = true;				
				dfs(j+arr[j][0], sum + arr[j][1]);
				//j날 잡은 상담이 끝나면 j+arr[j][0]날부터 상담을 시작할 수 있음
				//j날 상담까지의 번 돈은 sum + arr[j][1]
				//j+arr[j][0]날이 유효한 날인지의 확인은 재귀 dfs를 처음 시작할때 아니면 return 하는데
				// 그전에 번 돈은 유효하므로 max와 비교해 크면 max값 갱신
				visited[j] = false;
			}
		}
												
	}

}
