package snake;
import java.util.*;
import java.io.*;

class body{
	int row;
	int column;
	public body(int row, int column) {
		this.row = row;
		this.column = column;
	}
}

public class Snake {
	static int apple[][];
	static HashMap<Integer, Character> direction;
	static int locRow[] = {0, 1, 0, -1};
	static int locColumn[] = {1, 0, -1, 0};
	static LinkedList<body> bodyqueue;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//sc.nextLine();
		int applenum = Integer.parseInt(br.readLine());
		//sc.nextLine();
		apple = new int[n+1][n+1];
		direction = new HashMap<>();
		
		bodyqueue = new LinkedList<>();
		bodyqueue.add(new body(1,1));
		
		for(int i = 0; i < applenum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			apple[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=1;
			//sc.nextLine();
		}
		
		int directionN = Integer.parseInt(br.readLine());
		//sc.nextLine();
		
		for(int i = 0; i < directionN; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			direction.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
			//System.out.println(direction.keySet());
		}
		
		int currentDirection=0;
		int ans = solution(currentDirection);
		System.out.println(ans);
		
	}
	
	public static int solution(int currentDirection) {
		int count = 0;
		body temp;
		int currentRow = 1, currentColumn=1;
		int nextRow, nextColumn;
		
		while(true) {
			//temp = bodyqueue.get();
			//currentRow = temp.row;
			//currentColumn = temp.column;
			//���� ��ġ���� ���� ��ġ�� �˻�
			
			if(direction.containsKey(count)) {
				char ch = direction.get(count);
				if(ch == 'D') {
					//������
					currentDirection = (currentDirection+1) % 4;
				}
				else {
					//����
					currentDirection = (currentDirection-1+locRow.length) % 4;
				}
				
			}
			nextRow = currentRow + locRow[currentDirection];
			nextColumn = currentColumn + locColumn[currentDirection];
			
			count++;
			if(nextRow <=0 || nextRow >= apple.length || nextColumn <= 0 || nextColumn >= apple.length) {
				return count;
			}
			
			
			
			body temp2;
			//�̵��ϸ� ���� ����� ����� Ȯ��
			Iterator<body> itr = bodyqueue.iterator();
			while(itr.hasNext()) {
				temp2 = itr.next();
				if(temp2.row == nextRow && temp2.column == nextColumn) {
					//��´ٸ�
					return count;
				}
			}
			//next�� ����� �ִ��� Ȯ��
			if(apple[nextRow][nextColumn]!=1) {
				//����� ���ٸ� ���� �����(�̵�)
				bodyqueue.poll();
			}
			else {
				//��� ���ֱ�
				apple[nextRow][nextColumn]=0;
			}
			//���� �ʴ´ٸ� �Ӹ��� ť�� �߰�
			bodyqueue.add(new body(nextRow, nextColumn));
			//count++;
			currentRow = nextRow;
			currentColumn = nextColumn;
		}
		//return count;
	}

}
