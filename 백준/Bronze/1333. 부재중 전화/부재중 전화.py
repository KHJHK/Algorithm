answer = -1
x = 1
n, l, d = map(int, input().split())
for i in range(n):
    end = l * (i+1) - 1 + (5 * i)
    next_start = l * (i+1) + (5 * (i+1))

    for j in range(end + 1, next_start):
        if j % d == 0:
            answer = j
            break
    if answer != -1:
        break

x = 0
if answer == -1:
    while next_start > d * x:
        x += 1
    print(d * x)
else:
    print(answer)