import java.util.*;
import java.io.*;

/**
 * - 문제
 * N개의 주유소
 * 각 주유소마다 채우는 기름 정해짐
 * 시작 기름양과 목적지까지 거리 주어짐
 * 목적지 까지 경유하는 주유소 최소 수 구하기
 * 목적지 도착이 불가능할경우 -1
 * 
 * - 풀이
 * 1. 잔여 기름만큼 이동
 * 2. 이동 거리 내에서 가장 기름량이 많은 주유소 선택하여 방문
 * 3. 방문한 주유소 거리만큼이동
 * 4. 목적지 도착시 종료, 모든 주유소 거쳐서 도착 실패시 -1
 */
public class Main {
	static class GasStation implements Comparable<GasStation>{
		int distance;
		int oil;
		
		public GasStation(int distance, int oil) {
			this.distance = distance;
			this.oil = oil;
		}
		
		public int compareTo(GasStation o) {
			if(this.oil != o.oil) return Integer.compare(o.oil, this.oil);
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static int N, nowLoc, oil, end, cnt;
	static PriorityQueue<GasStation> pq = new PriorityQueue<>();
	static Queue<GasStation> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int distance = Integer.parseInt(st.nextToken());
			int stationOil = Integer.parseInt(st.nextToken());
			pq.offer(new GasStation(distance, stationOil));
		}
		
		st = new StringTokenizer(br.readLine());
		end = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());
		
		nowLoc += oil; //현재 기름만큼 이동
		if(nowLoc >= end) {
			System.out.println(0);
			return;
		}
		
		while(nowLoc < end) {
			oil = -1; //기름 초기화
			while(!pq.isEmpty()) { //주유소 탐색
				GasStation gasStation = pq.poll();
				
				if(gasStation.distance > nowLoc) { //현재 온 거리 안에 없는 주유소면 넘어가기
					queue.offer(gasStation);
					continue;
				}
				
				oil = gasStation.oil; //주유소 있으면 기름 넣기(pq 기름량 순서대로 정렬이기 때문에 현재 뽑은 기름이 가장 양아 많은 기름)
				cnt++; //방문 횟수 추가
				while(!queue.isEmpty()) pq.offer(queue.poll());
				break;
			}
			
			if(oil == -1) {
				cnt = -1;
				break;
			}
			
			nowLoc += oil; //차량 이동
		}
		
		System.out.println(cnt);
		
	}

}