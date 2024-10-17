import java.util.*;
import java.io.*;

public class Main {
	static class Ball implements Comparable<Ball>{
		int color;
		int size;
		int idx;
		
		public Ball(int color, int size, int idx) {
			this.color = color;
			this.size = size;
			this.idx = idx;
		}
		
		public int compareTo(Ball o) {
			return Integer.compare(this.size, o.size);
		}
	}
	
	static int N;
	static Ball[] balls;
	static int[] answers;
	static int[][] colorBallSum; //int[color][] => color = 확인하는 공 색깔 / 0 = 이전 공 크기 , 1 = 누적합, 2 = 실제로 사용될 누적합
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		balls = new Ball[N];
		answers= new int[N];
		colorBallSum = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken()) - 1;
			int size = Integer.parseInt(st.nextToken());
			balls[i] = new Ball(color, size, i);
		}
		
		Arrays.sort(balls);
		
		int sum = 0;
		int answerSum = 0;
		int beforeSize = 0;
		for(Ball ball : balls) {
			//1. 누적합 구하기
			if(beforeSize < ball.size) {
				beforeSize = ball.size;
				answerSum = sum; //현재 공의 사이즈가 이전 공보다 더 커지면, 이전 공들의 크기까지 합친 누적합을 answer에 넣어줌
			}
			sum += ball.size;
			
			//2. 색깔별 누적합 구하기(바로 위 누적합 구하기와 로직은 동일)
			if(colorBallSum[ball.color][0] < ball.size) {
				colorBallSum[ball.color][0] = ball.size;
				colorBallSum[ball.color][2] = colorBallSum[ball.color][1];
			}
			colorBallSum[ball.color][1] += ball.size;
			
			//3. 현재 공에 대한 정답 구하기
			answers[ball.idx] = answerSum - colorBallSum[ball.color][2];
		}
		
		for(int answer : answers) sb.append(answer).append("\n");
		System.out.println(sb);
		
	}
	
}
