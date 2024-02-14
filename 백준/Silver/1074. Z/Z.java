import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
문제: BJ_10870_피보나 수 5
메모리: 11460KB
결과: 맞았습니다!
시간: 72ms
접근방식:
재귀를 사용하여 접근하였다
각 시작위치에 따라 인덱스를 움직이는 방식으로 진행하였다.
처음에 map을 만들었지만  메모리초과로 인해 실패
map대신에 어떻게 순회할까 생각하니 n==1일때 순회하는 것처럼 움직인다고 생각했다.
아니라면 n/2로 4분면으로 나눠서 진행
*/
class Main {

	static int cnt=0;
	public static void z (int x,int y,int n,int r, int c) {
		//n==1이면 =극단적으로 맵을 줄이면 셀이된다 이 방식으로 맵을 순회하는 것처럼 꾸민다.
		if(n==1)
		{
			//행열이 r과 c라면
			if(x==r &&y==c) {
				System.out.println(cnt);	//답 출력하고 반환
			}
			cnt++;	//순회시에 cnt를 증가시킨다.
			return;
		}
		else {
			//아니라면 n/2씩 인덱스를 증가시켜서 4등분으로 나눈다.
			z(x,y,n/2,r,c);
			z(x,y+n/2,n/2,r,c);
			z(x+n/2,y,n/2,r,c);
			z(x+n/2,y+n/2,n/2,r,c);
		}

	}
		
	
	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader (System.in);
		BufferedReader br = new BufferedReader(in);
		StringTokenizer st =new StringTokenizer(br.readLine());
		//n,r,c입력
		int n = Integer.valueOf(st.nextToken());
		int r = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());
		//size는 2*n
		int size = (int)Math.pow(2, n);
		//z(0,0,size, r,c);
		while (size > 0) {
            int temp = (int) Math.pow(2, size - 1);
            int quadrant = 0;

            if (r >= temp) {
                quadrant += 2;
                r -= temp;
            }

            if (c >= temp) {
                quadrant += 1;
                c -= temp;
            }

            cnt += temp * temp * quadrant;
            size--;
        }

        System.out.println(cnt);


	}
}