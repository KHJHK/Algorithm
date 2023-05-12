n = int(input())
stair = [0]
answer = []

for _ in range(n):
    stair.append(int(input()))

#(연속 아님, 연속)으로 나누어서 생각
answer.append((0, 0))
answer.append((stair[1], 0))

for i in range(2, n + 1):
    a = stair[i] + max(answer[i - 2])
    b = stair[i] + answer[i - 1][0]

    answer.append((a, b))

print(max(answer[n]))