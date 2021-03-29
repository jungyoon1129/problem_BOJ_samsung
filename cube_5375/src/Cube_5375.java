import java.util.*;
import java.io.*;
/*
 * 완전 노가다..
 * 꼼꼼함 필요
 * 시계반대방향은 시계방향으로 세번 회전한거!
 */
public class Cube_5375 {
	static char arr[][][] = {{{'w','w','w'},{'w','w','w'},{'w','w','w'}},
			{{'g','g','g'},{'g','g','g'},{'g','g','g'}},{{'r','r','r'},{'r','r','r'},{'r','r','r'}},
			{{'b','b','b'},{'b','b','b'},{'b','b','b'}},{{'o','o','o'},{'o','o','o'},{'o','o','o'}},
			{{'y','y','y'},{'y','y','y'},{'y','y','y'}}
			};;
	
	static ArrayList<Integer> list[] = new ArrayList[5];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String temp;
		for(int i =1; i < 5; i++) {
			list[i] = new ArrayList<>();
			if(i == 1) {
				list[i].add(4);
				list[i].add(0);
				list[i].add(2);
				list[i].add(5);
			}
		}
		
		for(int i = 0; i < test; i++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; j++) {
				temp = st.nextToken();
				int dir = (temp.charAt(1)=='+') ? 1:-1;
				
				switch(temp.charAt(0)) {
				case 'U':
					up(dir);
					rotateCurrent(dir,0);
					break;
				case 'D':
					down(dir);
					rotateCurrent(dir,5);
					break;
				case 'F':
					rotateFront(dir);
					rotateCurrent(dir,2);
					break;
				case 'L':
					rotateLeft(dir);
					rotateCurrent(dir,1);
					break;
				case 'B':
					rotateBack(dir);
					rotateCurrent(dir,4);
					break;
				case 'R':
					rotateRight(dir);
					rotateCurrent(dir,3);
					break;
				}
			}
			//printCube();
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 3; y++) {
					bw.write(arr[0][x][y]);
				}
				bw.write("\n");
			}
			resetCube();
			
		}
		bw.flush();
		bw.close();
	}
	
	public static void rotateCurrent(int dir, int n) {
		if(dir == 1) {
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			char temp3[] = new char[3];
			char temp4[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[n][0][2-i];
				temp2[i] = arr[n][2-i][2];
				temp3[i] = arr[n][2][i];
				temp4[i] = arr[n][i][0];
			}
			for(int i = 0; i < 3; i++) {
				arr[n][0][2-i] = temp4[i];
				arr[n][2-i][2] = temp1[i];
				arr[n][2][i] = temp2[i];
				arr[n][i][0] = temp3[i];
			}
			
		}
		else {
			for(int j = 0; j < 3; j++) {
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				char temp3[] = new char[3];
				char temp4[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[n][0][2-i];
					temp2[i] = arr[n][2-i][2];
					temp3[i] = arr[n][2][i];
					temp4[i] = arr[n][i][0];
				}
				for(int i = 0; i < 3; i++) {
					arr[n][0][2-i] = temp4[i];
					arr[n][2-i][2] = temp1[i];
					arr[n][2][i] = temp2[i];
					arr[n][i][0] = temp3[i];
				}
			}
		}
		
	}
	public static void resetCube() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				arr[0][i][j] = 'w';
				arr[1][i][j] = 'g';
				arr[2][i][j] = 'r';
				arr[3][i][j] = 'b';
				arr[4][i][j] = 'o';
				arr[5][i][j] = 'y';
			}
			
		}
	}
public static void rotateBack(int dir) {
		
		if(dir == 1 ) {
			 //3->0
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[0][0][i];
				arr[0][0][i] = arr[3][i][2];
			}
			//0->1
			for(int i = 0; i < 3; i++) {
				temp2[i] = arr[1][2-i][0];
				arr[1][2-i][0] = temp1[i];
			}
			
			//1->5
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[5][2][2-i];
				arr[5][2][2-i] = temp2[i];
			}
			//5->3
			for(int i = 0; i < 3; i++) {
				//temp2[i] = arr[3][0][i];
				arr[3][i][2] = temp1[i];
			}
		}
		else {
			for(int k = 0; k<3; k++) {
				 //3->0
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[0][0][i];
					arr[0][0][i] = arr[3][i][2];
				}
				//0->1
				for(int i = 0; i < 3; i++) {
					temp2[i] = arr[1][2-i][0];
					arr[1][2-i][0] = temp1[i];
				}
				
				//1->5
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[5][2][2-i];
					arr[5][2][2-i] = temp2[i];
				}
				//5->3
				for(int i = 0; i < 3; i++) {
					//temp2[i] = arr[3][0][i];
					arr[3][i][2] = temp1[i];
				}
			}
		}
	}
	
	public static void rotateRight(int dir) {
		
		if(dir == 1 ) {
			 //2->0
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[0][i][2];
				arr[0][i][2] = arr[2][i][2];
			}
			//0->4
			for(int i = 0; i < 3; i++) {
				temp2[i] = arr[4][2-i][0];
				arr[4][2-i][0] = temp1[i];
			}
			
			//4->5
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[5][i][2];
				arr[5][i][2] = temp2[i];
			}
			//5->2
			for(int i = 0; i < 3; i++) {
				//temp2[i] = arr[3][0][i];
				arr[2][i][2] = temp1[i];
			}
		}
		else {
			for(int k = 0; k<3; k++) {
				 //2->0
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[0][i][2];
					arr[0][i][2] = arr[2][i][2];
				}
				//0->4
				for(int i = 0; i < 3; i++) {
					temp2[i] = arr[4][2-i][0];
					arr[4][2-i][0] = temp1[i];
				}
				
				//4->5
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[5][i][2];
					arr[5][i][2] = temp2[i];
				}
				//5->2
				for(int i = 0; i < 3; i++) {
					//temp2[i] = arr[3][0][i];
					arr[2][i][2] = temp1[i];
				}
			}
		}
	}
	public static void rotateLeft(int dir) {
		
		if(dir == 1 ) {
			 //4->0
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[0][2-i][0];
				arr[0][2-i][0] = arr[4][i][2];
			}
			//0->2
			for(int i = 0; i < 3; i++) {
				temp2[i] = arr[2][2-i][0];
				arr[2][2-i][0] = temp1[i];
			}
			
			//2->5
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[5][2-i][0];
				arr[5][2-i][0] = temp2[i];
			}
			//5->4
			for(int i = 0; i < 3; i++) {
				//temp2[i] = arr[3][0][i];
				arr[4][i][2] = temp1[i];
			}
		}
		else {
			for(int k = 0; k<3; k++) {
				 //4->0
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[0][2-i][0];
					arr[0][2-i][0] = arr[4][i][2];
				}
				//0->2
				for(int i = 0; i < 3; i++) {
					temp2[i] = arr[2][2-i][0];
					arr[2][2-i][0] = temp1[i];
				}
				
				//2->5
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[5][2-i][0];
					arr[5][2-i][0] = temp2[i];
				}
				//5->4
				for(int i = 0; i < 3; i++) {
					//temp2[i] = arr[3][0][i];
					arr[4][i][2] = temp1[i];
				}
			}
		}
	}
	
	public static void rotateFront(int dir) {
		
		if(dir == 1 ) {
			 //1->0
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[0][2][2-i];
				arr[0][2][2-i] = arr[1][i][2];
			}
			//0->3
			for(int i = 0; i < 3; i++) {
				temp2[i] = arr[3][2-i][0];
				arr[3][2-i][0] = temp1[i];
			}
			
			//3->5
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[5][0][i];
				arr[5][0][i] = temp2[i];
			}
			//5->1
			for(int i = 0; i < 3; i++) {
				//temp2[i] = arr[3][0][i];
				arr[1][i][2] = temp1[i];
			}
		}
		else {
			for(int k = 0; k<3; k++) {
				 //1->0
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[0][2][2-i];
					arr[0][2][2-i] = arr[1][i][2];
				}
				//0->3
				for(int i = 0; i < 3; i++) {
					temp2[i] = arr[3][2-i][0];
					arr[3][2-i][0] = temp1[i];
				}
				
				//3->5
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[5][0][i];
					arr[5][0][i] = temp2[i];
				}
				//5->1
				for(int i = 0; i < 3; i++) {
					//temp2[i] = arr[3][0][i];
					arr[1][i][2] = temp1[i];
				}
			}
		}
	}
	
	public static void down(int dir) {
		//up이면 0으로 설정 down 2로 설정
		if(dir == 1)  {
			 //1->2
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[2][2][i];
				arr[2][2][i] = arr[1][2][i];
			}
			//2->3
			for(int i = 0; i < 3; i++) {
				temp2[i] = arr[3][2][i];
				arr[3][2][i] = temp1[i];
			}
			
			//3->4
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[4][2][i];
				arr[4][2][i] = temp2[i];
			}
			//4->1
			for(int i = 0; i < 3; i++) {
				//temp2[i] = arr[3][0][i];
				arr[1][2][i] = temp1[i];
			}
		}
		else {
			
			for(int j = 0; j < 3; j++) {
				 //1->2
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[2][2][i];
					arr[2][2][i] = arr[1][2][i];
				}
				//2->3
				for(int i = 0; i < 3; i++) {
					temp2[i] = arr[3][2][i];
					arr[3][2][i] = temp1[i];
				}
				
				//3->4
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[4][2][i];
					arr[4][2][i] = temp2[i];
				}
				//4->1
				for(int i = 0; i < 3; i++) {
					//temp2[i] = arr[3][0][i];
					arr[1][2][i] = temp1[i];
				}
			}
		}
	}
	
	public static void up(int dir) {
		//up이면 0으로 설정 down 2로 설정
		if(dir == 1)  {
			 //1->4
			char temp1[] = new char[3];
			char temp2[] = new char[3];
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[4][0][i];
				arr[4][0][i] = arr[1][0][i];
			}
			//4->3
			for(int i = 0; i < 3; i++) {
				temp2[i] = arr[3][0][i];
				arr[3][0][i] = temp1[i];
			}
			
			//3->2
			for(int i = 0; i < 3; i++) {
				temp1[i] = arr[2][0][i];
				arr[2][0][i] = temp2[i];
			}
			//2->1
			for(int i = 0; i < 3; i++) {
				//temp2[i] = arr[3][0][i];
				arr[1][0][i] = temp1[i];
			}
		}
		else {
			
			for(int j = 0; j < 3; j++) {
				 //1->4
				char temp1[] = new char[3];
				char temp2[] = new char[3];
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[4][0][i];
					arr[4][0][i] = arr[1][0][i];
				}
				//4->3
				for(int i = 0; i < 3; i++) {
					temp2[i] = arr[3][0][i];
					arr[3][0][i] = temp1[i];
				}
				
				//3->2
				for(int i = 0; i < 3; i++) {
					temp1[i] = arr[2][0][i];
					arr[2][0][i] = temp2[i];
				}
				//2->1
				for(int i = 0; i < 3; i++) {
					//temp2[i] = arr[3][0][i];
					arr[1][0][i] = temp1[i];
				}
			}
		}
	}

}
