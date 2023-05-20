t = int(input())

for case in range(1, t + 1):
    n, k = map(int, input().split())
    answer = 0
    board = []
    for _ in range(n):
        board.append(list(map(int, input().split())))
    for i in range(n):
        v_count = 0
        h_count = 0
        for j in range(n):
            if board[i][j] == 1:
                v_count += 1
            if board[i][j] == 0 or j == n - 1:
                if v_count == k:
                    answer += 1
                v_count = 0

            if board[j][i] == 1:
                h_count += 1
            if board[j][i] == 0 or j == n - 1:
                if h_count == k:
                    answer += 1
                h_count = 0

    print('#{} {}'.format(case, answer))