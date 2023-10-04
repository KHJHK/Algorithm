import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int BOARD_SIZE = 9;
    static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    static boolean[][] visited = new boolean[27][BOARD_SIZE]; //행 9개, 열 9개, 그리드 9개
    static boolean isEnd;

    public static void main(String[] args) throws IOException {
        //숫자 입력 후 visited 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int r = 0; r < BOARD_SIZE; r++) {
            String input = br.readLine();
            for (int c = 0; c < BOARD_SIZE; c++) {
                int num = input.charAt(c) - '0';

                board[r][c] = num;
                if(num != 0){
                    //행 스도쿠 정보
                    visited[r][num - 1] = true;
                    //열 스도쿠 정보
                    visited[c + BOARD_SIZE][num - 1] = true;
                    //그리드 스도쿠 정보
                    visited[3 * (r / 3) + (c / 3) + (BOARD_SIZE * 2)][num - 1] = true;
                }

            }
        }

        makeSudoku(0, 0);
        for (int[] ints : board) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                System.out.print(ints[i]);
            }
            System.out.println();
        }
    }

    private static void makeSudoku(int startR, int startC) {
        for (int row = startR; row < BOARD_SIZE; row++) {
            if(row != startR){
                startC = 0;
            }
            for (int col = startC; col < BOARD_SIZE; col++) {
                if (board[row][col] == 0) {
                    int gridVisitedIdx = 3 * (row / 3) + (col / 3);
                    //숫자 입력
                    for (int i = 0; i < 9; i++) {
                        if (visited[row][i] || visited[col + BOARD_SIZE][i] || visited[gridVisitedIdx + (2 * BOARD_SIZE)][i]) {
                            if (i == 8) {
                                return;
                            }
                            continue;
                        }
                        board[row][col] = i + 1;
                        visited[row][i] = true;
                        visited[col + BOARD_SIZE][i] = true;
                        visited[gridVisitedIdx + (2 * BOARD_SIZE)][i] = true;

                        makeSudoku(row, col);
                        if (isEnd) {
                            return;
                        }
                        board[row][col] = 0;
                        visited[row][i] = false;
                        visited[col + BOARD_SIZE][i] = false;
                        visited[gridVisitedIdx + (2 * BOARD_SIZE)][i] = false;
                    }
                    if(board[row][col] == 0){
                        return;
                    }
                }
            }
        }
        isEnd = true;
    }
    
}