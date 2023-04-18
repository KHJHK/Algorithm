n = int(input())
k = list(map(int, input().split()))
l = int(input())
answer = 0

for i in k:
    if i == 0:
        continue
    else:
        a = i // l
        if i % l == 0:
            answer += l * a
        else:
            answer += l * (a+1)
print(answer)