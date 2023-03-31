n, m = map(int, input().split())
row = []
col = []

for i in range(n):
    floor = list(input())
    for j in range(m):
        if floor[j] == 'X':
            row.append(i)
            col.append(j)

row = set(row)
col = set(col)

answer = max(n - len(row), m - len(col))
print(answer)