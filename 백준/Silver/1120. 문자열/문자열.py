a, b = input().split()
answer = len(a)
if len(a) == len(b):
    for i in range(len(a)):
        if a[i] == b[i]:
            answer -= 1
else:
    same = 0
    cnt = 0
    for i in range(len(b)-len(a)+1):
        for j in range(len(a)):
            if a[j] == b[i+j]:
                cnt += 1
        if cnt > same:
            same = cnt
        cnt = 0
    answer -= same

print(answer)