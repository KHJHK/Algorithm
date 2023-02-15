n = int(input())
board = []
friends = []
answer = 0
for _ in range(n):
    board.append(input())

for i in range(n):
    cnt = 0
    friends.clear()

    for j in range(n):
        if i == j:
            continue
        if board[i][j] == 'Y':
            friends.append(j)
            cnt += 1

    for j in range(n):
        if i == j:
            continue
        if board[i][j] == 'N':
            for k in friends:
                if board[j][k] == 'Y':
                    cnt += 1
                    break

    answer = max(answer, cnt)
print(answer)