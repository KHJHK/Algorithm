n = int(input())
cnt = 0
two = [1,2,4,8,16,32,64,128]

while n > 0:
    cnt += 1
    for i in range(len(two)):
        if n < two[i]:
            n -= two[i-1]
            break
print(cnt)