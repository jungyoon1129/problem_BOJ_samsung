import java.util.*;
import java.io.*;

/*
 * ���۰� ��ġ�� �ð����� ���� �� �� �����ϱ� �� ���� dfs�� �������
 * j�� ���� ����� ������ ���� ��ȿ�� ������ Ȯ���ϰ� �湮�߾����� Ȯ���Ѵ�.
 * j�� ���� ����� ������ j+arr[j][0]������ ����� ������ �� ����
	j�� �������� �� ���� sum + arr[j][1]
	j+arr[j][0]���� ��ȿ�� �������� Ȯ���� ��� dfs�� ó�� �����Ҷ� �ƴϸ� return �ϴµ�
    ������ �� ���� ��ȿ�ϹǷ� max�� ���� ũ�� max�� ����
				
	dpǮ�� ����
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
		//���۰� ��ġ�°� �ִ��� Ȯ���ϰ� �³׵��� ���������� dfs
		int startDuration = arr[start][0];
			
		for(int j = start; j <= start + startDuration -1; j++) {
			//���۰� ��ġ�� �ð����� ���� �� �� �����ϱ� �� ���� dfs�� �������
			//j�� ���� ����� ������ ���� ��ȿ�� ������ Ȯ���ϰ� �湮�߾����� Ȯ���Ѵ�.
			if(j < visited.length && j + arr[j][0] -1< visited.length && !visited[j]) {
				visited[j] = true;				
				dfs(j+arr[j][0], sum + arr[j][1]);
				//j�� ���� ����� ������ j+arr[j][0]������ ����� ������ �� ����
				//j�� �������� �� ���� sum + arr[j][1]
				//j+arr[j][0]���� ��ȿ�� �������� Ȯ���� ��� dfs�� ó�� �����Ҷ� �ƴϸ� return �ϴµ�
				// ������ �� ���� ��ȿ�ϹǷ� max�� ���� ũ�� max�� ����
				visited[j] = false;
			}
		}
												
	}

}
