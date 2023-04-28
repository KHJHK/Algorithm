a, b = map(int, input().split())

prime = [True for _ in range(b + 1)]
prime[0] = False
prime[1] = False
for i in range(2, int(b ** 0.5) + 1):
    cnt = 2
    if prime[i]:
        while cnt * i <= b:
            prime[cnt * i] = False
            cnt += 1

nums = [0 for i in range(b+1)]
for idx in range(2, len(nums)):
    if prime[idx]:
        nums[idx] = 1
    pidx = 2
    while idx * pidx <= b:
        if prime[pidx]:
            nums[idx * pidx] = nums[idx] + 1
        pidx += 1

answer = 0
for cnt in nums[a : b + 1]:
    if prime[cnt]:
        answer += 1
print(answer)