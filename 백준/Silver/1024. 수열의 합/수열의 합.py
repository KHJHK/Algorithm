n, l = map(int, input().split())
while l <= 100:
    a = n // l
    b = n % l
    c = l // 2

    nums = [a for i in range(l)]
    for i in range(c):
        nums[i] -= c-i
        nums[l-1-i] += c-i
    for j in range(b):
        nums[j] += 1
    for k in range(l-1):
        if nums[k] + 1 != nums[k + 1] or nums[k] < 0:
            l += 1
            break
        if k == l - 2:
            l = 200
if l != 200:
    print(-1)
else:
    print(*nums)