n = int(input())

dp = [0] * (n+1)

for i in range(1, n):
    #1을 뺸다
    if dp[i + 1] == 0:
        dp[i + 1] = dp[i] + 1
    else:
        dp[i + 1] = min(dp[i] + 1, dp[i + 1])
    #2로 나눈다
    if i * 2 <= n:
        if dp[i * 2] == 0:
            dp[i * 2] = dp[i] + 1
        else:
            dp[i * 2] = min(dp[i] + 1, dp[i * 2])
    #3으로 나눈다
    if i * 3 <= n:
        if dp[i * 3] == 0:
            dp[i * 3] = dp[i] + 1
        else:
            dp[i * 3] = min(dp[i] + 1, dp[i * 3])

print(dp[n])