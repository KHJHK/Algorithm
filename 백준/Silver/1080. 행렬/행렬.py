row, col = map(int, input().split())
board = []
board2 = []
answer = 0
reverse_board = False
complete_board = False

for _ in range(row):
    board.append(list(input()))
for _ in range(row):
    board2.append(list(input()))

else:
    for r in range(row - 2):
        #보드 완성시 break
        if complete_board:
            break
        for c in range(col - 2):
            #보드 완성 체크
            if board == board2:
                complete_board = True
                break
            #뒤집을지 체크
            if board[r][c] != board2[r][c]:
                reverse_board = True

            #뒤집기
            if reverse_board:
                reverse_board = False
                answer += 1
                for i in range(3):
                    for j in range(3):
                        if board[r + i][c + j] == '1':
                            board[r + i][c + j] = '0'
                        else:
                            board[r + i][c + j] = '1'

    if board == board2:
        print(answer)
    else:
        print(-1)