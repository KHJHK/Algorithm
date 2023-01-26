n = int(input())
a = list(map(int, input().split()))
b = sorted(a)
answer = [-1 for i in range(n)]

for i in range(n):
    for j in range(n):
        if a[i] == b[j]:
            if answer.count(j) != 0:
                continue
            else:
                answer[i] = j
                break
print(*answer)