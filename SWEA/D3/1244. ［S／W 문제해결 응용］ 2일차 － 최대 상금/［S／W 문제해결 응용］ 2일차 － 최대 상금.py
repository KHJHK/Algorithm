#11:41
#len(num) ** k승 == len(num) ** k - 1승번째까지 연산해야함
from collections import deque
t = int(input())

for x in range(1, t + 1):
    num, k = map(int, input().split())
    num = list(str(num))
    max_num = num[:]
    max_num.sort(reverse=1)
    max_num_int = int(''.join(max_num))
    check_max = False
    answer = ''

    if num == max_num:
        for i in num:
            if num.count(i) >= 2:
                answer = max_num_int
                break

        if answer != max_num_int and k % 2 != 0:
            max_num[-1], max_num[-2] = max_num[-2], max_num[-1]
            max_num_int = int(''.join(max_num))
        answer = max_num_int
    else:
        total_change = k
        if k > len(num) - 1:
            for i in num:
                if num.count(i) >= 2:
                    answer = max_num_int
                    break
            check_max = True
            k = len(num) - 1

        if answer == '':
            cnt = 0
            case = deque([num])
            while cnt != k:
                cnt += 1
                n = len(case)
                for _ in range(n):
                    num = case.popleft()
                    for i in range(len(num) - 1):
                        for j in range(i + 1, len(num)):
                            num_temp = num[:]
                            num_temp[i], num_temp[j] = num_temp[j], num_temp[i]
                            if cnt == k:
                                case.append(int(''.join(num_temp)))
                            else:
                                case.append(num_temp[:])
                if check_max and (total_change - cnt) % 2 == 0:
                    if max_num in case or max_num_int in case:
                        break
            if check_max:
                if (total_change - cnt) % 2 != 0:
                    max_num[-1], max_num[-2] = max_num[-2], max_num[-1]
                    max_num_int = int(''.join(max_num))
                answer = max_num_int
            else:
                answer = max(case)
    print('#{} {}'.format(x, answer))