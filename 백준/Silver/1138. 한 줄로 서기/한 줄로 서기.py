n = int(input())
provided = list(map(int, input().split()))
answer = [0 for i in range(n)]

answer[provided[0]] = 1
for i in range(1, n):
    cnt = 0
    for j in range(n):
        if answer[j] == 0:
            if cnt == provided[i]:
                answer[j] = i + 1
                break
            else:
                cnt += 1
print(*answer)