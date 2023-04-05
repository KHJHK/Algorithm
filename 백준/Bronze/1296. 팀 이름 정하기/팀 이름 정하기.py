name = input()
l, o, v, e = 0, 0, 0, 0
for i in name:
    if i == 'L':
        l += 1
    elif i == 'O':
        o += 1
    elif i == 'V':
        v += 1
    elif i == 'E':
        e += 1

n = int(input())
answer = ['', -1]

for j in range(n):
    l2, o2, v2, e2 = l, o, v, e
    team = input()
    for k in team:
        if k == 'L':
            l2 += 1
        elif k == 'O':
            o2 += 1
        elif k == 'V':
            v2 += 1
        elif k == 'E':
            e2 += 1
    win_rate = ((l2 + o2) * (l2 + v2) * (l2 + e2) * (o2 + v2) * (o2 + e2) * (v2 + e2)) % 100

    if win_rate >= answer[1]:
        if win_rate == answer[1]:
            answer[0] = min(answer[0], team)
        else:
            answer[0] = team
            answer[1] = win_rate

print(answer[0])