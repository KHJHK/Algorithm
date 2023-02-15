n, m = map(int, input().split())
k = min(n,m)
board = []

for _ in range(n):
    board.append(list(map(int, input())))

answer = 0
while answer == 0:
    for i in range(n):
        if answer != 0:
            break
        for j in range(m):
            if i + k <= n and j + k <= m:
                if board[i][j] == board[i+k-1][j] == board[i][j+k-1] == board[i+k-1][j+k-1]:
                    answer = k ** 2
                    break
    k -= 1
print(answer)