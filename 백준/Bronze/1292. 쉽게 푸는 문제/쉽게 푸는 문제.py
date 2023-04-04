a, b = map(int, input().split())
cnt = 1
answer = 0
num = []
while len(num) < 1000:
    for i in range(cnt):
        num.append(cnt)
    cnt += 1

for idx in range(a-1, b):
    answer += num[idx]

print(answer)