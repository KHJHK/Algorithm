import sys
import math

T = int(sys.stdin.readline())
for i in range(T):
    answer = 0
    start = [0,0]
    end = [0,0]
    cList = []
    start[0], start[1], end[0], end[1] = map(int, sys.stdin.readline().split())
    cCnt = int(sys.stdin.readline())
    for j in range(cCnt):
        cList.append(list(map(int, sys.stdin.readline().split())))

    for k in cList:
        startToCircle = math.sqrt((k[0] - start[0])**2 + (k[1] - start[1])**2)
        endToCircle = math.sqrt((k[0] - end[0]) ** 2 + (k[1] - end[1]) ** 2)

        if startToCircle < k[2]:
            if endToCircle < k[2]:
                pass
            else:
                answer += 1

        if endToCircle < k[2]:
            if startToCircle < k[2]:
                pass
            else:
                answer += 1

    print(answer)