t = int(input())
for case in range(1, t + 1):
    n, a, b = map(int, input().split())
    answer = "Broken"
    if a == 0:
        if b != 100:
            answer = "Possible"
    else:
        for win in range(1, 101):
            if (100 * win) / a == (100 * win) // a and (100 * win) // a <= n:
                if a == b:
                    answer = "Possible"
                    break
                # 확률이 내려갈 경우
                elif a > b:
                    if b != 0:
                        answer = "Possible"
                        break
                # 확률이 올라갈 경우
                elif a < b:
                    if b != 100:
                        answer = "Possible"
                        break

    print('#{} {}'.format(case, answer))