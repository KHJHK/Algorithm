from collections import deque

x_control = [-1, 0, 1, 0]
y_control = [0, -1, 0, 1]

def bfs(board, x, y):
    xqueue = deque([x])
    yqueue = deque([y])
    board[x][y] = False
    while xqueue:
        x2 = xqueue.popleft()
        y2 = yqueue.popleft()
        for i in range(4):
            if 0 <= x2 + x_control[i] < n and 0 <= y2 + y_control[i] < m:
                if board[x2 + x_control[i]][y2 + y_control[i]]:
                    board[x2 + x_control[i]][y2 + y_control[i]] = False
                    xqueue.appendleft(x2 + x_control[i])
                    yqueue.appendleft(y2 + y_control[i])

t = int(input())
for _ in range(t):
    answer = 0
    n, m, k = map(int, input().split())
    board = [[False for i in range(m)] for j in range(n)]
    for _ in range(k):
        x, y = map(int, input().split())
        board[x][y] = True
    for i in range(n):
        for j in range(m):
            if board[i][j]:
                bfs(board, i, j)
                answer += 1
    print(answer)