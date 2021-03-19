import java.util.*;

class game{
	int arr[][];
	int count;
	public game(int arr[][], int count) {
		this.arr = arr;
		this.count = count;
	}
}

public class prob_12100_2048 {
	static int arr[][];
	static int count = 0;
	static int max=-1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        int n = sc.nextInt();
        arr = new int[n][n];
       // max = -1;
        
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n; j++) {
        		arr[i][j] = sc.nextInt();
        		if(max < arr[i][j])	max = arr[i][j];
        	}
        }
        
        solution(arr, 0);
        System.out.println(max);

	}
	
	public static void solution(int arr[][], int count) {
		if(count == 5)	return;
		solution(left(arr), count+1);
		solution(right(arr), count+1);
		solution(up(arr), count+1);
		solution(down(arr), count+1);
	}
	
	public static int[][] left(int arr[][]){
		Stack<Integer> stack = new Stack<>();
		boolean flagPush  = false;
		int result[][] = new int[arr.length][arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(stack.isEmpty()) {
					if(arr[i][j]!=0) {
						stack.add(arr[i][j]);
					
						if(max < arr[i][j])	max = arr[i][j];
						flagPush = false;
					}
				}
				else {
					if(!stack.isEmpty()) {
						if(arr[i][j] == stack.peek()) {
							if(!flagPush) {
								//���� �������� ���� ���̶� ���ٸ�
								stack.pop();
								stack.add(arr[i][j]*2);
								if(max < stack.peek())	max = stack.peek();
								flagPush = true;
							}
							else {
								//�̹� ������ �� �ִ� �ֶ� ���ٸ� �׳� �߰�
								stack.add(arr[i][j]);
								if(max < stack.peek())	max = stack.peek();
								flagPush = false;
							}					
						}
						else {
							//���� �����ʴٸ�
							if(arr[i][j] != 0) {
								stack.add(arr[i][j]);
								if(max < stack.peek())	max = stack.peek();
								flagPush = false;
							}
						}
					}
					
				}
			}
			
			//���ÿ� �ִ� ���� arr���� �ű��.
			if(stack.size() == arr.length) {
				for(int k = arr.length-1; k >= 0; k--)
					result[i][k] = stack.pop();
			}
			else if(stack.size() == 0) {
				for(int k = arr.length-1; k >= 0; k--)
					result[i][k] = 0;
			}
			else {
				for(int k = arr.length-1; k > stack.size()-1; k--)
					result[i][k] = 0;
				for(int k = stack.size()-1; k >= 0; k--)
					result[i][k] = stack.pop();
			}			
		}
		return result;				
	}
	
	public static int[][] right(int arr[][]){
		Stack<Integer> stack = new Stack<>();
		boolean flagPush  = false;
		int result[][] = new int[arr.length][arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = arr[0].length-1; j >= 0; j--) {
				if(stack.isEmpty()) {
					if(arr[i][j]!=0) {
						stack.add(arr[i][j]);
					
						if(max < arr[i][j])	max = arr[i][j];
						flagPush = false;
					}
				}
					//stack.add(arr[i][j]);
				else {
					if(arr[i][j] == stack.peek()) {
						if(!flagPush) {
							//���� �������� ���� ���̶� ���ٸ�
							stack.pop();
							stack.add(arr[i][j]*2);
							flagPush = true;
							if(max < stack.peek())	max = stack.peek();
						}
						else {
							//�̹� ������ �� �ִ� �ֶ� ���ٸ� �׳� �߰�
							stack.add(arr[i][j]);
							flagPush = false;
							if(max < stack.peek())	max = stack.peek();
						}					
					}
					else {
						//���� �����ʴٸ�
						if(arr[i][j] != 0) {
							stack.add(arr[i][j]);
							flagPush = false;
							if(max < stack.peek())	max = stack.peek();
						}
					}
				}
				
			}
			
			//���ÿ� �ִ� ���� arr���� �ű��.
			if(stack.size() == arr.length) {
				for(int k = 0; k < arr.length; k++)
					result[i][k] = stack.pop();
			}
			else if(stack.size() == 0) {
				for(int k = 0; k < arr.length; k++)
					result[i][k] = 0;
			}
			else {
				for(int k = 0; k < arr.length- stack.size(); k++)
					result[i][k] = 0;
				for(int k = arr.length- stack.size(); k < arr.length; k++)
					result[i][k] = stack.pop();
			}			
		}
		return result;				
	}
	
	public static int[][] up(int arr[][]){
		Stack<Integer> stack = new Stack<>();
		boolean flagPush  = false;
		int result[][] = new int[arr.length][arr.length];
		
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if(stack.isEmpty()){
					if(arr[j][i]!=0) {
						stack.add(arr[j][i]);
					
						if(max < arr[j][i])	max = arr[j][i];
						flagPush = false;
					}
				}
				else {
					if(arr[j][i] == stack.peek()) {
						if(!flagPush) {
							//���� �������� ���� ���̶� ���ٸ�
							stack.pop();
							stack.add(arr[j][i]*2);
							flagPush = true;
							if(max < stack.peek())	max = stack.peek();
						}
						else {
							//�̹� ������ �� �ִ� �ֶ� ���ٸ� �׳� �߰�
							stack.add(arr[j][i]);
							flagPush = false;
							if(max < stack.peek())	max = stack.peek();
						}					
					}
					else {
						//���� �����ʴٸ�
						if(arr[j][i] != 0) {
							stack.add(arr[j][i]);
							flagPush = false;
							if(max < stack.peek())	max = stack.peek();
						}
					}
				}
				
			}
			
			//���ÿ� �ִ� ���� arr���� �ű��.
			if(stack.size() == arr.length) {
				for(int k = arr.length-1; k >= 0; k--)
					result[k][i] = stack.pop();
			}
			else if(stack.size() == 0) {
				for(int k = arr.length-1; k >= 0; k--)
					result[k][i] = 0;
			}
			else {
				for(int k = arr.length-1; k > stack.size()-1; k--)
					result[k][i] = 0;
				for(int k = stack.size()-1; k >= 0; k--)
					result[k][i] = stack.pop();
			}			
		}
		return result;				
	}
	
	public static int[][] down(int arr[][]){
		Stack<Integer> stack = new Stack<>();
		boolean flagPush  = false;
		int result[][] = new int[arr.length][arr.length];
		
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = arr.length-1; j >= 0; j--) {
				if(stack.isEmpty()){
					if(arr[j][i]!=0) {
						stack.add(arr[j][i]);
					
						if(max < arr[j][i])	max = arr[j][i];
						flagPush = false;
					}
				}
				else {
					if(arr[j][i] == stack.peek()) {
						if(!flagPush) {
							//���� �������� ���� ���̶� ���ٸ�
							stack.pop();
							stack.add(arr[j][i]*2);
							flagPush = true;
							if(max < stack.peek())	max = stack.peek();
						}
						else {
							//�̹� ������ �� �ִ� �ֶ� ���ٸ� �׳� �߰�
							stack.add(arr[j][i]);
							flagPush = false;
							if(max < stack.peek())	max = stack.peek();
						}					
					}
					else {
						//���� �����ʴٸ�
						if(arr[j][i] != 0) {
							stack.add(arr[j][i]);
							flagPush = false;
							if(max < stack.peek())	max = stack.peek();
						}
					}
				}
				
			}
			
			//���ÿ� �ִ� ���� arr���� �ű��.
			if(stack.size() == arr.length) {
				for(int k = 0; k < arr.length; k++)
					result[k][i] = stack.pop();
			}
			else if(stack.size() == 0) {
				for(int k = 0; k < arr.length; k++)
					result[k][i] = 0;
			}
			else {
				for(int k = 0; k < arr.length- stack.size(); k++)
					result[k][i] = 0;
				for(int k = arr.length- stack.size(); k < arr.length; k++)
					result[k][i] = stack.pop();
			}			
		}
		return result;				
	}
}
