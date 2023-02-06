t = int(input())
for i in range(t):
    start, end = map(int, input().split())
    end = end - start
    start = 0

    if end == 2:
        print(2)
        continue

    n = int(end**(1/2))
    if (n ** 2) + n < end:
        print(n * 2 + 1)
    elif n ** 2 == end:
        print(n * 2 - 1)
    else:
        print(n*2)