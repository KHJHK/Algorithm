n, k = map(int, input().split())

nums = [i + 1 for i in range(n)]
answer = []
cnt = k

while nums:
    while cnt > len(nums):
        cnt -= len(nums)
    cnt -= 1
    answer.append(nums.pop(cnt))
    cnt += k
print(f'<{str(answer)[1:-1]}>')