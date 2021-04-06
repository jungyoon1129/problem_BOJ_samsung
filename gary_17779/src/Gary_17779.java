import java.util.*;
import java.io.*;
/*
 * visited�� �Լ��ȿ��� �Ҵ�
 * ��輱�ȿ� ���� �ʰ� ��輱 �ۿ��� �ٰ��;��ϰ� visited�� ��� break�ؾ���
 * (!vistied[i])�� �ϸ� ��輱 ���� ���� ��������.
 * ����
 * https://bcp0109.tistory.com/entry/%EB%B0%B1%EC%A4%80-17779%EB%B2%88-%EA%B2%8C%EB%A6%AC%EB%A7%A8%EB%8D%94%EB%A7%81-2-Java
 */
public class Gary_17779 {
	static int min = Integer.MAX_VALUE;
	static int arr[][];
	static int total=0;
	static boolean visited[][];
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		//visited = new boolean[n][n];
		
		for(int i = 0; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				total += arr[i][j];
			}
		}
		
		
		for(int i =0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				for(int d1 =1; d1 < n; d1++) {
					for(int d2 =1; d2 < n; d2++) {
						//if (i + d1 + d2 >= arr.length) continue;
                       // if (j - d1 < 0 || j + d2 >= arr.length) continue;

						if(i+d2+d1<arr.length && j-d1 >=0 && j+d2 <arr.length 
								&& j -d1+d2 >=0&& j -d1+d2 < arr.length) {
							find(i,j,d1,d2);
						//int temp = find(i,j,d1,d2);
						//if(temp >= 0)
							//min = Math.min(min, temp);
						}
					}
				}
			}
		}
		
		System.out.println(min);
	}
	
	public static void find(int x, int y, int d1, int d2) {
		visited = new boolean[arr.length][arr.length];

			//��輱 true�� ǥ���ϱ�
			for(int i = 0; i <= d1; i++) {
				visited[x+i][y-i] = true;
				visited[x+d2+i][y+d2-i] = true;
			}
			for(int j = 0; j <= d2; j++) {
				visited[x+j][y+j] = true;
				visited[x+d1+j][y-d1+j] = true;
			}
			
			//��輱 ������ ���ϱ�

			int sum[] = new int[5];
			int k = 0;	//sum�迭 �ε��� ��
			for(int i = 0; i < x+d1; i++) {
				for(int j = 0; j <= y; j++) {
					if(visited[i][j])break;
					sum[k]+=  arr[i][j];
				}
				
			}
			k++;
			
			for(int i = 0; i <= x+d2; i++) {				
				for (int j = arr.length - 1; j > y; j--) {
					if(visited[i][j])break;
					sum[k]+=  arr[i][j];
				}
			}
			k++;
			
			for(int i = x+d1; i < arr.length; i++) {			
				for(int j = 0; j < y-d1+d2; j++) {
					if(visited[i][j])break;
						sum[k]+=  arr[i][j];
				}				
			}
			k++;
			
			for(int i = x+d2+1; i < arr.length; i++) {				
				 for (int j = arr.length - 1; j >= y - d1 + d2; j--){
					 if(visited[i][j])break;
						sum[k]+=  arr[i][j];
				}				
			}
			sum[4] =total;
			for(int i = 0; i < 4; i++)
				sum[4] -= sum[i];
			Arrays.sort(sum);
			
			min = Math.min(min, sum[4] - sum[0]);
		
	}

}
