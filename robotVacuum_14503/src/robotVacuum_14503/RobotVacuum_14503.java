package robotVacuum_14503;
import java.util.*;
import java.io.*;

public class RobotVacuum_14503 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		int locRow[] = {-1,0,1,0};
		int locCol[] = {0, 1, 0, -1};
		//�� �� �� ��
		
		int arr[][] = new int[n][m];
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		int count = 1;
		int rotation = 0;
		boolean stop = false;
		int nextDir = dir;
		arr[row][col] = 3;
		while(true) {
			
			
			for(int i = 0; i < 4; i++) {
				//�������� ���� �ٲ�
				nextDir = (nextDir - 1 + 4) % 4;
				if(arr[row + locRow[nextDir]][col + locCol[nextDir]] == 0) {			
						// û�� ���� ���� ��� ȸ���� ������ ��ĭ �̵��Ѵ�.
						//ȸ��
						dir = nextDir;
						//��ġ �̵�
						row = row + locRow[nextDir];
						col = col + locCol[nextDir];
						//û�� ǥ��
						arr[row][col] = 3;
						//count++;
						//rotation = 0;
						count++;	//û��
						break;
						
				}
				if(i == 3) {
					//4���� ��� ȸ���ߴµ� ���̰ų� û�ҿϷ��̸�
					if(arr[row + locRow[(dir + 2) % 4 ]][col + locCol[(dir + 2) % 4]] != 1) {
							//�ڰ� ���� �ƴѰ��
							row = row + locRow[(dir + 2) % 4 ];
							col = col + locCol[(dir + 2) % 4];
							//rotation = 0;
					}
					else {
						//�ڰ� ���� ���
						stop = true;
					}
				}
				
			}
			
			if(stop)	break;
		}
		
		System.out.println(count);
	}

}
