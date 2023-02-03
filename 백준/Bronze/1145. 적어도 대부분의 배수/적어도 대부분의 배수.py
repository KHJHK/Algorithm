nums = list(map(int, input().split()))
answer = max(nums) ** 3
for i in range(3):
    for j in range(i+1, 4):
        for k in range(j+1, 5):
            for n in range(min(nums[i], nums[j], nums[k]), (nums[i] * nums[j] * nums[k]) + 1):
                if n % nums[i] == 0 and n % nums[j] == 0 and n % nums[k] == 0:
                    answer = min(answer, n)
                    break
print(answer)