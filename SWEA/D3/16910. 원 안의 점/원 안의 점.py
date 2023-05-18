t = int(input())
for case in range(1, t + 1):
    n = int(input())
    answer = 0
    for x in range((-1) * n, n + 1):
        for y in range((-1) * n, n + 1):
            if (x ** 2) + (y ** 2) <= n ** 2:
                answer += 1
    print(f'#{case} {answer}')