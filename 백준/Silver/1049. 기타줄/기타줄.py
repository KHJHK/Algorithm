n,m = map(int, input().split())
one_min, six_min = 1000, 1000
answer = 0

for i in range(m):
    six, one = map(int, input().split())
    if one < one_min:
        one_min = one
    if six < six_min:
        six_min = six

if one_min * 6 < six_min:
    six_min = one_min * 6

answer = (n // 6) * six_min
answer += min(one_min * (n % 6), six_min)

print(answer)