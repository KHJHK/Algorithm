t = int(input())
googoo = []
for i in range(1, 10):
    for j in range(1, 10):
        if i * j not in googoo:
            googoo.append(i * j)
for case in range(1, t + 1):
    answer = 'No'
    n = int(input())
    if n in googoo:
        answer = 'Yes'

    print('#{} {}'.format(case, answer))