from collections import Counter
s1, s2, s3 = map(int, input().split())
nums = []

for i in range(s1):
    for j in range(s2):
        for k in range(s3):
            nums.append(3 + i + j + k)

cnt = Counter(nums)
print(cnt.most_common()[0][0])