import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception{
		
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<>(n, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1.intValue() + o2.intValue() ==0 ) {
					return o1.intValue()<0?-1:1;
				}
				return Math.abs(o1.intValue())> Math.abs(o2.intValue()) ? 1:-1;
			}
		});
		for(int i=0; i<n;i++) {
			int order = Integer.parseInt(br.readLine());
			if(order ==0) {
				if(!q.isEmpty()) {
					System.out.println(q.poll());
				}else {
					System.out.println(0);
				}
			}else {
				q.offer(order);
			}
			
		}
	}

}