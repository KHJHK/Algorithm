t = int(input())

for case in range(1, t + 1):
    n, m = map(int, input().split())
    time = (n + m) % 24
    print('#{} {}'.format(case, time))