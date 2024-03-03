import java.io.*;
import java.util.*;
public class Main {

	static String st;
	static ArrayList<Character> operations;
	static ArrayList<Integer> nums;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		operations = new ArrayList<>();
		nums = new ArrayList<>();
		char[] st = br.readLine().toCharArray();
		for(int i=0;i<st.length;i++) {
			if(st[i] >='0' && st[i] <='9') {
				nums.add(st[i] -'0');
			}else {
				operations.add(st[i]);
			}
		}
		int[] braces = new int[operations.size() +2];
		System.out.println(brace(0,operations.size(),braces));
	}
	public static int brace(int idx, int end, int braces[]) {
		if(!(idx <end)) { 	// 인덱스 끝에 다다랐다면, 끝까지 탐색했다면
			return calculate(end,braces);
		}
	
	//괄호를 안함
	int[] b1 = Arrays.copyOf(braces, braces.length);
	int r1 = brace(idx+1,end,b1);
	//괄호를 함
	int[] b2 = Arrays.copyOf(braces, braces.length);
	b2[idx] = 1;
	b2[idx+1] =0;
	int r2 = brace(idx+2,end,b2);
	
	return Math.max(r1, r2);
	}
	public static int calculate(int end, int[] braces) {
		ArrayList<Character> ops = new ArrayList<>();
		ArrayList<Integer> ns = new ArrayList<>();
		
		ns.add(nums.get(0));
		for(int i=0;i< end; i++) {
			if(braces[i] ==1) {
				int a = ns.get(ns.size() -1);
				int b = nums.get(i+1);
				int r = cal(a,b,operations.get(i));
				
				ns.remove(ns.size() -1);
				ns.add(r);
			}else {
				ops.add(operations.get(i));
				ns.add(nums.get(i+1));
			}
		}
		int v = ns.get(0);
		ns.remove(0);
		for(int i=0; i<ops.size();i++) {
			int b = ns.get(i);
			char op = ops.get(i);
			v = cal(v,b,op);
		}
		return v;
	}
	public static int cal(int a, int b, char op) {
        int r = 0;

        switch (op) {
            case '+':
                r = a + b;
                break;

            case '-':
                r = a - b;
                break;

            case '*':
                r = a * b;
                break;
        }
        return r;
	}
}