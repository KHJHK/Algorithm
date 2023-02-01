n, m = input().split()
x = 0
answer = 0
for i in n:
    x += int(i)
for j in m:
    answer += int(j) * x
print(answer)