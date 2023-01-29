answer = 0
for cnt in range(8):
    chess = input()
    if cnt % 2 == 0:
        for j in range(len(chess)):
            if j % 2 == 0 and chess[j] == 'F':
                answer += 1
    else:
        for j in range(len(chess)):
            if j % 2 != 0 and chess[j] == 'F':
                answer += 1
print(answer)