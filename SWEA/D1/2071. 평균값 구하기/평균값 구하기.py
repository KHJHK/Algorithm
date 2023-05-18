T = int(input())
for case in range(1, T + 1):
    answer = round(sum(map(int, input().split())) / 10)
    print(f'#{case} {answer}')