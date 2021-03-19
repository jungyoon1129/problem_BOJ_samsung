package supervisor;
import java.util.*;

public class TestSupervisor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =  new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];
		int supervisor[] = new int[2];
		
		for(int i = 0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		supervisor[0] = sc.nextInt();
		supervisor[1] = sc.nextInt();
		long ans = n;
		
		for(int i = 0; i<n; i++) {
			arr[i] -= supervisor[0];
			//if(arr[i] <=0)	
				//ans++;
			if(arr[i] > 0) {
				if(arr[i]%supervisor[1]==0)
					ans += arr[i]/supervisor[1];
				else
					ans += arr[i]/supervisor[1]+1;
			}
		}
		System.out.println(ans);
		
		
	}

}
