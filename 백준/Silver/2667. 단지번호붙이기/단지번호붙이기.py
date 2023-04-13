def bfs(board, x, y, answer):
    size = 0
    control_x = [-1, 0, 1, 0]
    control_y = [0, -1, 0, 1]
    que = [(x,y)]
    board[x][y] = '0'

    while que:
        x, y = que.pop(0)
        size += 1
        for i in range(4):
            x2 = x + control_x[i]
            y2 = y + control_y[i]
            if 0 <= x2 < len(board) and 0 <= y2 < len(board):
                if board[x2][y2] == '1':
                    que.append((x2, y2))
                    board[x2][y2] = '0'

    answer[0] += 1
    answer.append(size)
    return board, answer

n = int(input())
board = []
answer = [0] # idx 0 = 단지 수, 그 뒤 idx들은 단지 크기
for _ in range(n):
    r = list(input())
    board.append(r)

for x in range(n):
    for y in range(n):
        if board[x][y] == '1':
            board, answer = bfs(board, x, y, answer)
cnt = answer.pop(0)
answer.sort()

print(cnt)
for i in range(cnt):
    print(answer[i])