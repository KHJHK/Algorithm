n = int(input())
height = list(map(int, input().split()))
check = [0 for i in range(n)]

#오른쪽 방향 기울기 검사
for i in range(n-1):
    gradient = 0
    for j in range(i+1, n):
        if j == i+1:
            gradient = (height[j] - height[i]) / (j - i)
            check[i] += 1
        else:
            if gradient < (height[j] - height[i]) / (j - i):
                gradient = (height[j] - height[i]) / (j - i)
                check[i] += 1

#왼쪽 방향 기울기 검사
for i in range(n-1, 0, -1):
    gradient = 0
    for j in range(i-1, -1, -1):
        if j == i-1:
            gradient = (height[j] - height[i]) / (j - i)
            check[i] += 1
        else:
            if j-i == 0:
                print(j, i, j - i)
            if gradient > (height[j] - height[i]) / (j - i):
                gradient = (height[j] - height[i]) / (j - i)
                check[i] += 1
print(max(check))