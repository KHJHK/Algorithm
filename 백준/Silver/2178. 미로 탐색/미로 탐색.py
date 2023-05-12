def bfs(board, n, m):
    cont_x = [-1, 0, 1, 0]
    cont_y = [0, 1, 0, -1]
    queue = []

    queue.append((0, 0))

    while queue:
        x,y = queue.pop(0)
        for i in range(4):
            if 0 <= x + cont_x[i] < n and 0 <= y + cont_y[i] < m and board[x + cont_x[i]][y + cont_y[i]] == 1:
                if x + cont_x[i] == 0 and y + cont_y[i] == 0:
                    continue
                board[x + cont_x[i]][y + cont_y[i]] = board[x][y] + 1
                queue.append((x + cont_x[i], y + cont_y[i]))
    return board


n, m = map(int, input().split())
board = []
for _ in range(n):
    board.append(list(map(int, input())))
print((bfs(board, n, m))[n - 1][m - 1])