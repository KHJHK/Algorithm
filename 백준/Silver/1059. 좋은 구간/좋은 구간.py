s = int(input())
l = list(map(int, input().split()))
l.sort()
n = int(input())

big = 0
small = 0

for i in l:
    if i >= n:
        big = i
        break

for i in range(s-1, -1, -1):
    if l[i] <= n:
        small = l[i]
        break

answer = 0
for a in range(small + 1, big - 1):
    for b in range(a + 1, big):
        if a <= n and b >= n:
            answer += 1
print(answer)