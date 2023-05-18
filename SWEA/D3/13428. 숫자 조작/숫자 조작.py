t = int(input())
for case in range(1, t + 1):
    num = list(map(int, input()))
    arr_num = num[:]
    arr_num.sort()

    min_num = num[:]
    max_num = num[:]
    if arr_num[0] == 0:
        for idx in range(1, len(arr_num)):
            if arr_num[idx] != 0:
                arr_num[0], arr_num[idx] = arr_num[idx], arr_num[0]
                break

    for i in range(len(num)):
        if num[i] != arr_num[i]:
            min_num[len(num) - num[::-1].index(arr_num[i]) - 1], min_num[i] = min_num[i], min_num[len(num) - num[::-1].index(arr_num[i]) - 1]
            break

    arr_num.sort(reverse=1)
    for i in range(len(num)):
        if num[i] != arr_num[i]:
            max_num[len(num) - num[::-1].index(arr_num[i]) - 1], max_num[i] = max_num[i], max_num[len(num) - num[::-1].index(arr_num[i]) - 1]
            break

    min_answer = ''
    max_answer = ''
    for i, j in zip(min_num, max_num):
        min_answer += str(i)
        max_answer += str(j)
    print(f'#{case} {min_answer} {max_answer}')