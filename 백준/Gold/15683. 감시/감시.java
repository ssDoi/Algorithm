import java.io.*;
import java.util.*;

public class Main {
	static int map[][];
	static ArrayList<int[]> cctvList;
	static int n, m;
	static int zeroMin = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cctvList = new ArrayList<>();
		map =new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new int[] { map[i][j], i, j });
				}
			}
		}

		int[] arr = new int[cctvList.size()];
		loop(map,arr,0);
		/*for(int[] row : tmp) {
			for(int num : row) {
				System.out.print(num+" ");
			}
			System.out.println();
		}*/

		System.out.println(zeroMin);
	}

	public static void loop(int[][] map, int[] arr, int depth) {
		if(depth ==  cctvList.size()) {
			int tmp[][] = new int[n][m];
			for(int i=0; i<n;i++) {
				tmp[i] = map[i].clone();
			}
			for(int i=0; i<cctvList.size(); i++) {
				switch(cctvList.get(i)[0]) {
				case 1:
					tmp=cameraLay(arr[i], cctvList.get(i), tmp);
					break;
				case 2:
					tmp= secondCameraLay(tmp, cctvList.get(i), arr[i]);
					break;
				case 3:
					tmp= thirdCameraLay(tmp, cctvList.get(i), arr[i]);
					break;
				case 4:
					tmp= forthCameraLay(tmp, cctvList.get(i), arr[i]);
					break;
				case 5:
					tmp = cameraLay(0,cctvList.get(i),tmp);
					tmp = cameraLay(2,cctvList.get(i),tmp);
					tmp = cameraLay(3,cctvList.get(i),tmp);
					tmp = cameraLay(1,cctvList.get(i),tmp);
					break;
				}
			}
			int cnt=0;
			for(int i=0;i<n;i++) {
				for(int j=0; j<m;j++) {
					if(tmp[i][j] ==0)
						cnt++;
				}
			}
			if(zeroMin> cnt) {
				zeroMin = cnt;
			}
			zeroMin = Math.min(zeroMin, cnt);
			
			return;
		}
		int v =cctvList.get(depth)[0];
		int range=0;
		if(v ==2) {
			range=2;
		}else if(v==5) {
			range=1;
		}else {
			range =4;
		}
		for(int i=0; i<range ; i++ ) {
			arr[depth] =i;
			loop(map,arr,depth+1);
		}
	}
	public static int[][] forthCameraLay(int[][] map, int cctv[], int d){
		if(d==0) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(2,cctv,map);
			map = cameraLay(3,cctv,map);
		}else if(d==1) {
			map = cameraLay(2,cctv,map);
			map = cameraLay(1,cctv,map);
			map = cameraLay(0,cctv,map);
		}else if(d==2) {
			map = cameraLay(3,cctv,map);
			map = cameraLay(1,cctv,map);
			map = cameraLay(2,cctv,map);
		}else if(d==3) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(1,cctv,map);
			map = cameraLay(3,cctv,map);
		}
		return map;
	}
	public static int forthCameraSum(int[][] map, int cctv[], int d) {
		int sum=0;
		if(d==0) {
			sum += oneCameraSum(map,cctv,0);
			sum += oneCameraSum(map,cctv,2);
			sum += oneCameraSum(map,cctv,3);
		}else if(d==1) {
			sum += oneCameraSum(map,cctv,2);
			sum += oneCameraSum(map,cctv,1);
			sum += oneCameraSum(map,cctv,0);
		}else if(d==2) {
			sum += oneCameraSum(map,cctv,3);
			sum += oneCameraSum(map,cctv,1);
			sum += oneCameraSum(map,cctv,2);
		}else if(d==3) {
			sum += oneCameraSum(map,cctv,0);
			sum += oneCameraSum(map,cctv,1);
			sum += oneCameraSum(map,cctv,3);
		}
		return sum;
	}
	public static int[][] thirdCameraLay(int[][] map, int cctv[], int d) {
		if(d==0) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(2,cctv,map);
		}else if(d==1) {
			map = cameraLay(2,cctv,map);
			map = cameraLay(1,cctv,map);
		}else if(d==2) {
			map = cameraLay(3,cctv,map);
			map = cameraLay(1,cctv,map);
		}else if(d==3) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(3,cctv,map);
		}
		return map;
	}
	public static int thirdCameraSum(int[][] map, int cctv[], int d) {
		int sum=0;
		if(d==0) {
			sum += oneCameraSum(map,cctv,0);
			sum += oneCameraSum(map,cctv,2);
		}else if(d==1) {
			sum += oneCameraSum(map,cctv,2);
			sum += oneCameraSum(map,cctv,1);
		}else if(d==2) {
			sum += oneCameraSum(map,cctv,3);
			sum += oneCameraSum(map,cctv,1);
		}else if(d==3) {
			sum += oneCameraSum(map,cctv,0);
			sum += oneCameraSum(map,cctv,3);
		}
		return sum;
	}

	public static int secondCameraSum(int[][] map, int cctv[],int d) {
		int sum=0;
		if(d==0) {
			sum += oneCameraSum(map,cctv,0);
			sum += oneCameraSum(map,cctv,1);
		}else if(d==1) {
			sum += oneCameraSum(map,cctv,2);
			sum += oneCameraSum(map,cctv,3);
		}
		return sum;
	}
	public static int[][] secondCameraLay(int[][] map, int cctv[],int d) {
		if(d==0) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(1,cctv,map);
		}else if(d==1) {
			map = cameraLay(2,cctv,map);
			map = cameraLay(3,cctv,map);
		}
		return map;
	}
	public static int oneCameraSum(int[][] map, int cctv[], int d) {
		int sum = 0;
		if (d == 0) {
			int u = cctv[1] + 1;
			while (u < n) {
				if (map[u][cctv[2]] == 0) {
					sum++;
				} else if (map[u][cctv[2]] == 6) {
					break;
				}
				u++;
			}
		} else if (d == 1) {
			int down = cctv[1] - 1;
			while (down >= 0) {
				if (map[down][cctv[2]] == 0) {
					sum++;
				} else if (map[down][cctv[2]] == 6) {
					break;
				}
				down--;
			}
		} else if (d == 2) {
			int r = cctv[2] + 1;
			while (r < m) {
				if (map[cctv[1]][r] == 0) {
					sum++;
				} else if (map[cctv[1]][r] == 6) {
					break;
				}
				r++;
			}
		} else {
			int l = cctv[2] - 1;
			while (l >= 0) {
				if (map[cctv[1]][l] == 0) {
					sum++;
				} else if (map[cctv[1]][l] == 6) {
					break;
				}
				l--;
			}
		}

		return sum;
	}
	public static int[][] cameraLay(int d,int[] cctv, int map[][]) {
		if (d == 0) {
			int u = cctv[1] + 1;
			while (u < n) {
				if (map[u][cctv[2]] == 0) {
					map[u][cctv[2]] = 7;
				} else if (map[u][cctv[2]] == 6) {
					break;
				}
				u++;
			}
		} else if (d == 1) {
			int down = cctv[1] - 1;
			while (down >= 0) {
				if (map[down][cctv[2]] == 0) {
					map[down][cctv[2]] = 7;
				} else if (map[down][cctv[2]] == 6) {
					break;
				}
				down--;
			}
		} else if (d == 2) {
			int r = cctv[2] + 1;
			while (r < m) {
				if (map[cctv[1]][r] == 0) {
					map[cctv[1]][r] =7;
				} else if (map[cctv[1]][r] == 6) {
					break;
				}
				r++;
			}
		} else {
			int l = cctv[2] - 1;
			while (l >= 0) {
				if (map[cctv[1]][l] == 0) {
					map[cctv[1]][l] =7;
				} else if (map[cctv[1]][l] == 6) {
					break;
				}
				l--;
			}
		}
		return map;
	}
}