n = int(input())
nums = list(map(int, input().split()))
nums.reverse()

dp = [0 for i in range(1001)]
dp[nums[0]] = 1
answer = []

for i in range(1, n):
    if dp[nums[i]] == 0:
        a = 0
        for j in range(i):
            if nums[i] > nums[j] and dp[nums[j]] > a:
                a = dp[nums[j]]
        dp[nums[i]] = a + 1
    else:
        dp[nums[i]] = max(dp[:nums[i]]) + 1
print(max(dp))