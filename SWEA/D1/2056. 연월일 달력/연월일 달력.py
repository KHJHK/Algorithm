calendar = {'01' : 31, '02' : 28, '03' : 31, '04' : 30, '05' : 31, '06' : 30, '07' : 31, '08' : 31, '09' : 30, '10' : 31, '11' : 30, '12' : 31}

T = int(input())
for case in range(1, T + 1):
    n = input()
    answer = '-1'
    if n[4:6] in calendar:
        if int(n[6:]) <= calendar[n[4:6]]:
            answer = n[:4] + '/' + n[4:6] + '/' + n[6:]
    print(f'#{case} {answer}')