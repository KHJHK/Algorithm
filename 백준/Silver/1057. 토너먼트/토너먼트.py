n, k, l = map(int, input().split())
answer = 1

while 1:
    if k >= l:
        temp = k
        k = l
        l = temp
        # answer = -1
        # break

    if k % 2 == 1 and k + 1 == l:
        break
    if n % 2 == 1 and l == n:
        n += 1
    else:
        answer += 1
        if k % 2 == 1:
            k += 1
        if l % 2 == 1:
            l += 1
        n = n // 2
        k = k // 2
        l = l // 2

print(answer)