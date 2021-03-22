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
		//북 동 남 서
		
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
				//왼쪽으로 방향 바꿈
				nextDir = (nextDir - 1 + 4) % 4;
				if(arr[row + locRow[nextDir]][col + locCol[nextDir]] == 0) {			
						// 청소 안한 곳인 경우 회전한 곳으로 한칸 이동한다.
						//회전
						dir = nextDir;
						//위치 이동
						row = row + locRow[nextDir];
						col = col + locCol[nextDir];
						//청소 표시
						arr[row][col] = 3;
						//count++;
						//rotation = 0;
						count++;	//청소
						break;
						
				}
				if(i == 3) {
					//4방향 모두 회전했는데 벽이거나 청소완료이면
					if(arr[row + locRow[(dir + 2) % 4 ]][col + locCol[(dir + 2) % 4]] != 1) {
							//뒤가 벽이 아닌경우
							row = row + locRow[(dir + 2) % 4 ];
							col = col + locCol[(dir + 2) % 4];
							//rotation = 0;
					}
					else {
						//뒤가 벽인 경우
						stop = true;
					}
				}
				
			}
			
			if(stop)	break;
		}
		
		System.out.println(count);
	}

}
